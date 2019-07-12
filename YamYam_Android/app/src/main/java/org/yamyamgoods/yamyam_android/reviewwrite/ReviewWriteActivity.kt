package org.yamyamgoods.yamyam_android.reviewwrite

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_review_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.ctx
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.NetworkServiceGoods
import org.yamyamgoods.yamyam_android.network.get.GetReviewWritePageResponse
import org.yamyamgoods.yamyam_android.network.get.GetReviewWritePageResponseData
import org.yamyamgoods.yamyam_android.network.post.PostReviewWriteResponse
import org.yamyamgoods.yamyam_android.productdetail.dto.ProductDetailShortDTO
import org.yamyamgoods.yamyam_android.reviewwrite.adapter.ReviewWriteUploadImagesRVAdapter
import org.yamyamgoods.yamyam_android.reviewwrite.dialog.DialogReviewWriteSave
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class ReviewWriteActivity : AppCompatActivity() {
    companion object {
        var reviewWriteCtx: ReviewWriteActivity? = null
    }

    val token: String =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJpYXQiOjE1NjIzMTUzNjYsImV4cCI6MTU2MzYyOTM2Nn0.ZkDGasoDPHTrGvy7yFOT9cPjTQ7gnnUOqekY_zYrAuc"

    val networkService: NetworkServiceGoods by lazy {
        ApplicationController.networkServiceGoods
    }

    var goodsIdx: Int = -1
    lateinit var goodsImg: String
    lateinit var storeName: String
    lateinit var goodsName: String
    lateinit var goodsPrice: String
    var goodsRating: Float? = 0f

    var images = ArrayList<MultipartBody.Part>()
    lateinit var getReviewWritePageResponseData: GetReviewWritePageResponseData
    var options: RequestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(10))
    private var PICTURE_REQUEST_CODE: Int = 100

    lateinit var clipData: ClipData
    val uploadImageList = ArrayList<ReviewWriteUploadImagesItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reviewWriteCtx = this@ReviewWriteActivity

        setContentView(R.layout.activity_review_write)

        btn_review_write_save.isEnabled = false

        getVariables()

        configureGoodsTab()

        // 사진넣기 버튼 눌렀을 때
        btn_review_write_upload_image.setOnClickListener {
            getPermission()
            configureRecyclerView()
        }

        view_review_write_edit.setOnClickListener {
            activateKeyboard(view_review_write_edit)
        }

        btn_review_write_save.setOnClickListener {
            saveReview(goodsIdx, images)
        }
    }

    override fun onResume() {
        super.onResume()
        configureRecyclerView()
        getReviewWritePage(102) // 리뷰 작성 페이지
        configureSaveButton()
    }

    private fun getVariables(){
        var rgDTO : ProductDetailShortDTO = intent.getParcelableExtra("reviewGoods")
        goodsIdx = rgDTO.goods_idx
        goodsImg = rgDTO.goods_img
        storeName = rgDTO.store_name
        goodsName = rgDTO.goods_name
        goodsPrice = rgDTO.goods_price
        goodsRating = rgDTO.goods_rating
        Log.v("현주", goodsPrice.toString())
    }

    private fun configureGoodsTab(){
        var productStarRate: List<ImageView> = listOf(
            findViewById(R.id.iv_review_write_product_star1),
            findViewById(R.id.iv_review_write_product_star2),
            findViewById(R.id.iv_review_write_product_star3),
            findViewById(R.id.iv_review_write_product_star4),
            findViewById(R.id.iv_review_write_product_star5)
        )

        Glide.with(this@ReviewWriteActivity)
            .load(goodsImg)
            .apply(options)
            .into(iv_review_write_product)
        tv_review_write_store_name.text = storeName
        tv_review_write_product_name.text = goodsName
        tv_review_write_product_price.text = goodsPrice

        if (goodsPrice == "0"){
            setInvisible(tv_review_write_price_won)
        }

        tv_review_write_products_star_rating.text = roundString(goodsRating)
        var productStarCount = goodsRating
        var intStarCount: Int = productStarCount!!.toInt()
        var remainder: Float = goodsRating!! - intStarCount.toFloat()

        for (i in 0 until (intStarCount)) {
            productStarRate[i].setImageResource(R.drawable.img_goods_star)
            if (0.5 > remainder && remainder >= 0)
                continue
            if (1 > remainder && remainder >= 0.5)
                productStarRate[i + 1].setImageResource(R.drawable.img_goods_star_half)
        }
    }

    fun getPermission() {
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                // 권한 요청 성공
                openGallery()
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
                // 권한 요청 실패
                Toast.makeText(this@ReviewWriteActivity, "갤러리 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show()
            }
        }
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setRationaleMessage(getString(R.string.txt_permission2))
            .setDeniedMessage(getString(R.string.txt_permission1))
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
            .check()
    }

    fun openGallery() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "얌얌굿즈 : 리뷰에 업로드할 사진을 선택해주세요.!"), PICTURE_REQUEST_CODE)
        Toast.makeText(this@ReviewWriteActivity, "사진을 꾹 눌러서 여러장을 선택해보세요~", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == PICTURE_REQUEST_CODE && resultCode == RESULT_OK && null != data) {

                val uri = data?.data
                if (uri == null) {
                    clipData = data?.clipData
                    if (data.clipData.itemCount > 9)
                        Toast.makeText(this, "사진은 9장까지 선택 가능합니다.", Toast.LENGTH_LONG).show()

                    if (clipData != null) {
                        for (i in 0 until data.clipData.itemCount) {
                            var item: ClipData.Item = data.clipData.getItemAt(i)

                            var selectedPictureUri: Uri = item.uri
                            val options = BitmapFactory.Options()
                            val inputStream: InputStream = contentResolver!!.openInputStream(selectedPictureUri)
                            val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                            val byteArrayOutputStream = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                            val photoBody =
                                RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())


                            // 클립데이터의 uri을 리사이클러뷰 데이터 클래스에 추가하기.
                            uploadImageList.add(ReviewWriteUploadImagesItem(1, item.uri.toString()))
                            images.add(
                                MultipartBody.Part.createFormData(
                                    "img",
                                    File(selectedPictureUri.toString()).name, photoBody
                                )
                            )//여기의 image는 키값의 이름하고 같아야함
                        }
                    }
                } else {
                    var selectedPictureUri: Uri = uri
                    val options = BitmapFactory.Options()
                    val inputStream: InputStream = contentResolver!!.openInputStream(selectedPictureUri)
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                    val photoBody =
                        RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())

                    // 클립데이터의 uri을 리사이클러뷰 데이터 클래스에 추가하기.
                    uploadImageList.add(ReviewWriteUploadImagesItem(1, uri.toString()))
                    images.add(
                        MultipartBody.Part.createFormData(
                            "img",
                            File(selectedPictureUri.toString()).name,
                            photoBody
                        )
                    )//여기의 image는 키값의 이름하고 같아야함
                }
            }
        } catch (e: Exception) {
        }
    }

    //사진 데이터에서 close 버튼 눌렀을 때 삭제하기
    fun deleteClipDataItem(idx: Int) {
        val item: ReviewWriteUploadImagesItem = uploadImageList[idx]
        uploadImageList.remove(item)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        configureRecyclerView()
    }

    //리사이클러 뷰 적용하기
    fun configureRecyclerView() {
        setVisible(cl_rv_review_write_image)
        rv_review_write_image_list.apply {
            adapter = ReviewWriteUploadImagesRVAdapter(this@ReviewWriteActivity, uploadImageList, images)
            layoutManager = LinearLayoutManager(this@ReviewWriteActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    fun activateKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    fun configureSaveButton() {
        edt_review_write.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                var edtString = edt_review_write.text
                if (edtString.length > 0)
                    btn_review_write_save.isEnabled = true
                else
                    btn_review_write_save.isEnabled = false
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    // 리뷰 작성 서버 통신
    fun getReviewWritePage(goodsIdx: Int) {       //goodsIdx : 리뷰를 작성할 굿즈의 Idx
        networkService.getReviewWritePageRequest("application/json", token, goodsIdx)
            .enqueue(object : Callback<GetReviewWritePageResponse> {
                override fun onFailure(call: Call<GetReviewWritePageResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<GetReviewWritePageResponse>,
                    response: Response<GetReviewWritePageResponse>
                ) {
                    if (response.isSuccessful) { //통신 성공 시
                        getReviewWritePageResponseData = response.body()!!.data

                        //굿즈의 옵션 받아옴
                        var option = getReviewWritePageResponseData.goods_option_name;
                        var optionLength = option.size;
                        var optionString = ""

                        for (i in 0 until optionLength) {
                            if (i == optionLength - 1) {
                                optionString += option[i]
                            } else {
                                optionString += option[i] + ", "
                            }
                        }

                        // 리뷰작성부분의 hint 글자 바꿔주는 곳
                        edt_review_write.hint = "구매하신 굿즈의 " + optionString + "를 함께 입력해주세요:)"
                    } else {
                    }
                }
            })
    }

    fun saveReview(goodsIdx: Int, img: ArrayList<MultipartBody.Part>) {
        //서버랑 통신.
        // 별점, 리뷰 스트링, 사진 저장
        var rating_data: Int = rb_review_write_star_rate.rating.toInt()
        var content_data: String = edt_review_write.text.toString()

        var content = RequestBody.create(MediaType.parse("text/plain"), content_data)

        networkService.postReviewWriteRequest(
            token, goodsIdx, content, rating_data, img
        ).enqueue(object : Callback<PostReviewWriteResponse> {
            override fun onFailure(call: Call<PostReviewWriteResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<PostReviewWriteResponse>, response: Response<PostReviewWriteResponse>) {
                if (response.isSuccessful) {
                    //포인트 쌓기 다이얼로그 띄우기
                    btn_review_write_save.setOnClickListener {
                        var imageBtnDialog = DialogReviewWriteSave(this@ReviewWriteActivity)
                        imageBtnDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        imageBtnDialog.setCanceledOnTouchOutside(false)
                        imageBtnDialog.show()
                        Log.v("현주", "다이얼로그 띄우기")
                    }
                } else {
                }
            }
        })
    }

    // 소수점 첫째자리까지만 표현하기
    fun roundString(value: Float?): String {
        var strFloat: String = String.format("%.1f ", value)
        return strFloat
    }

    private fun setVisible(view: View) {
        view.visibility = View.VISIBLE
    }

    private fun setInvisible(view: View) {
        view.visibility = View.INVISIBLE
    }
}

