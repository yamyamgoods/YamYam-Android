package org.yamyamgoods.yamyam_android.mypage

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_mypage.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.mypage.adapter.MypageProductRVAdapter
import org.yamyamgoods.yamyam_android.mypage.alarm.adapter.MypageAlarmRVAdapter
import org.yamyamgoods.yamyam_android.mypage.dialog.DialogMypageChangeProfileImage
import org.yamyamgoods.yamyam_android.mypage.recent.RecentlyViewedProductsActivity
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.NetworkServiceUser
import org.yamyamgoods.yamyam_android.network.get.*
import org.yamyamgoods.yamyam_android.network.put.PutMypageEditNicknameRequest
import org.yamyamgoods.yamyam_android.network.put.PostMypageEditProfileImageRequest
import org.yamyamgoods.yamyam_android.reviewdetail.ReviewDetailActivity
import org.yamyamgoods.yamyam_android.reviewwrite.ReviewWriteActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.lang.reflect.Type

class MypageActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST: Int = 1
    var flag = 0
    val token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJpYXQiOjE1NjIzMTUzNjYsImV4cCI6MTU2MzYyOTM2Nn0.ZkDGasoDPHTrGvy7yFOT9cPjTQ7gnnUOqekY_zYrAuc"

    val networkService: NetworkServiceUser by lazy {
        ApplicationController.networkServiceUser
    }

    lateinit var mypageProductRVAdapter : MypageProductRVAdapter
    lateinit var mypageAlarmRVAdapter: MypageAlarmRVAdapter
    lateinit var input_profile_img: MultipartBody.Part

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        getUserInfoResponse()

        configureTitleBar()
        editUserNickName()
        getMypageRecentlyReviewedProductsRequest()
        getAlarmListResponse()
        openRecentActivity()
        btn_mypage_alarm.setOnClickListener {
            openAlarmDrawer()
        }
        btn_mypage_user_image_edit.setOnClickListener {
            changeProfileImage()
        }

        // 리뷰 작성창 확인하기 위한 임시코드
        // 나중에 지우기
        btn_mypage_notice.setOnClickListener {
            var intent = Intent(this@MypageActivity, ReviewWriteActivity::class.java)
            startActivity(intent)
        }
    }


    // 유저 정보 서버 통신
    fun getUserInfoResponse() {
        networkService.getUserInfoResponse(
            "application/json",
                token)
                .enqueue(object : Callback<GetUserInfoResponse> {
                    override fun onFailure(call: Call<GetUserInfoResponse>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<GetUserInfoResponse>, response: Response<GetUserInfoResponse>) {
                        Log.v("현주", "마이페이지 서버 통신 성공  response : ${response.body()}")
                        if (response.code() == 200) {
                            response.body()?.let {
                                // 닉네임
                                tv_mypage_user_name.setText(it.data!!.user_name)

                                //  포인트
                                var strPoint: String = addComma(it.data!!.user_point)
                                tv_mypage_point.setText(strPoint)

                                // 이미지
                                Glide.with(this@MypageActivity)
                                        .load(it.data!!.user_img)
                                        .circleCrop()
                                        .into(iv_mypage_user_image)

                                // 알람 표시
                                if (it.data!!.alarm_flag == 0)
                                    setInvisible(iv_mypage_redddot)
                                if (it.data!!.alarm_flag == 1)
                                    setVisible(iv_mypage_redddot)
                            }
                        }

                        response.errorBody()?.let {
                            val type: Type = object : TypeToken<GetUserInfoResponse>() {}.type
                            val gson: Gson = GsonBuilder().create()
                            val responseJson: GetUserInfoResponse = gson.fromJson(it.string().toString(), type)

                            if (response.code() == 401) {
                                if (responseJson.message == "jwt must be provided")
                                    toast("로그인을 해주세요.")
                                if (responseJson.message == "jwt expired")
                                    toast("로그인이 만료되었습니다.")
                            }
                        }
                    }
                })
    }

    private fun configureTitleBar() {
        btn_mypage_close.setOnClickListener {
            finish()
        }
    }

    private fun changeProfileImage() {
        // 프로필 이미지 변경 다이얼로그 띄우기
        btn_mypage_user_image_edit.setOnClickListener {
            var imageBtnDialog = DialogMypageChangeProfileImage(this)
            imageBtnDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            imageBtnDialog.setCanceledOnTouchOutside(false)
            imageBtnDialog.show()
        }
    }

    // 권한 요청
    fun getPermission() {
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                // 권한 요청 성공
                openGallery()
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
                // 권한 요청 실패
                Toast.makeText(this@MypageActivity, "갤러리 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show()
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
        var intent = Intent(Intent.ACTION_GET_CONTENT)
        //intent.setType("image/*")
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, PICK_IMAGE_REQUEST)//(Intent.createChooser(intent, "얌얌굿즈 : 프로필 사진을 선택해주세요!"), PICK_IMAGE_REQUEST)
    }

    // 프로필 사진 변경 서버 통신
    private fun postMypageEditProfileImageRequest(img: MultipartBody.Part){
        Log.v("현주", "put 함수에 들어옴")
        if (img !=null) {
            networkService.postMypageEditProfileImageRequest( token, img!!).
                enqueue(object: Callback<PostMypageEditProfileImageRequest>{
                override fun onFailure(call: Call<PostMypageEditProfileImageRequest>, t: Throwable) {
                }
                override fun onResponse(call: Call<PostMypageEditProfileImageRequest>, response: Response<PostMypageEditProfileImageRequest>) {
                  if (response.isSuccessful)
                      Log.v("현주", response.body()!!.toString())
                    else{
                      if(response.code()== 401)
                          Log.v("현주", "실패")
                      else
                          Log.v("현주", "401이 아닌 실패")
                  }
                }
            })
        }
    }

    fun setProfileImageDefault() {
        iv_mypage_user_image.setImageResource(R.drawable.img_myprofile)
    }

    // 최근 본 상품 서버 통신
    private fun getMypageRecentlyReviewedProductsRequest(){
        networkService.getMypageRecentlyViewedProductsResponse(
                "application/json",
                token,
                -1
        ).enqueue(object:  Callback<GetMypageRecentlyViewedProductsResponse>{
            override fun onFailure(call: Call<GetMypageRecentlyViewedProductsResponse>, t: Throwable) {
                Log.e("현주", t.toString())
            }

            override fun onResponse(call: Call<GetMypageRecentlyViewedProductsResponse>, response: Response<GetMypageRecentlyViewedProductsResponse>) {
                if (response.isSuccessful){
                    Log.v("현주", "최근 본 상품 response : ${response.body()}")
                    response.body()?.let{
                        var tmp: ArrayList<RecentlyViewedProducts> = response.body()!!.data!!
                        mypageProductRVAdapter = MypageProductRVAdapter(this@MypageActivity!!, tmp)
                        rv_mypage_recently_viewed_product_list.apply {
                            adapter = MypageProductRVAdapter (this@MypageActivity, tmp)
                            layoutManager = LinearLayoutManager(this@MypageActivity, LinearLayoutManager.HORIZONTAL, false)
                        }
                        //mypageProductRVAdapter.dataList = tmp
                        mypageProductRVAdapter.notifyDataSetChanged()
                    }
                }

                response.errorBody()?.let{
                    val type: Type = object : TypeToken<GetMypageRecentlyViewedProductsResponse>() {}.type
                    val gson: Gson = GsonBuilder().create()
                    val responseJson: GetMypageRecentlyViewedProductsResponse = gson.fromJson(it.string().toString(), type)

                    if (response.code() == 401) {
                        if (responseJson.message == "jwt must be provided")
                            toast("로그인을 해주세요.")
                        if (responseJson.message == "jwt expired")
                            toast("로그인이 만료되었습니다.")
                    }
                }
            }
        })
    }

    // 최근 본 상품 액티비티 열기
    private fun openRecentActivity() {
        btn_mypage_recently_viewed_product.setOnClickListener {
            val intent = Intent(this, RecentlyViewedProductsActivity::class.java)
            startActivity(intent)
        }
    }

    // 알람 드로어 열기
    private fun openAlarmDrawer() {
        val drawer: DrawerLayout = findViewById(R.id.drawer_mypage_alarm)
        if (!drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.openDrawer(Gravity.RIGHT)
        }
    }

    // 알람 목록 서버 통신
    private fun getAlarmListResponse(){
      networkService.getAlarmListResponse("application/json",
              token,
              -1) .enqueue(object: Callback<GetAlarmListResponse>{
          override fun onFailure(call: Call<GetAlarmListResponse>, t: Throwable) {
              Log.e("현주", t.toString())
          }

          override fun onResponse(call: Call<GetAlarmListResponse>, response: Response<GetAlarmListResponse>) {
              if (response.isSuccessful){
                  Log.v("현주", "알람 목록 response: ${response.body()}")
                  response.body()?.let{
                      var tmp: ArrayList<AlarmListData> = response.body()!!.data!!
                      rv_mypage_alarm_list.apply{
                          adapter = MypageAlarmRVAdapter(this@MypageActivity, tmp)
                          layoutManager = LinearLayoutManager(this@MypageActivity)
                      }
                      mypageAlarmRVAdapter = MypageAlarmRVAdapter(this@MypageActivity, tmp)
                      //mypageProductRVAdapter.notifyDataSetChanged()
                  }
              }

              response.errorBody()?.let{
                  Log.v("현주", "알람 목록에서 에러났어요")
                  val type:Type = object: TypeToken<GetAlarmListResponse>() {}.type
                  val gson: Gson = GsonBuilder().create()
                  val responseJson: GetMypageRecentlyViewedProductsResponse = gson.fromJson(it.string().toString(), type)

                  if (response.code() == 401) {
                      if (responseJson.message == "jwt must be provided")
                          toast("로그인을 해주세요.")
                      if (responseJson.message == "jwt expired")
                          toast("로그인이 만료되었습니다.")
                  }
              }
          }
      })
    }

    // 알람 목록에서 리뷰 상세보기
    fun getAlarmReviewDetailResponse(){
        networkService.getAlarmReviewDetail("application/json", token,
            -1, -1).enqueue(object: Callback<GetReviewDetailResponse>{
            override fun onFailure(call: Call<GetReviewDetailResponse>, t: Throwable) {
                Log.e("현주", t.toString())
            }

            override fun onResponse(
                call: Call<GetReviewDetailResponse>,
                response: Response<GetReviewDetailResponse>) {
                if (response.isSuccessful){
                    Log.v("현주", "알람 목록 response: ${response.body()}")
                    response.body()?.let{
                        val intent = Intent(this@MypageActivity, ReviewDetailActivity::class.java)
                        startActivity(intent)
                        // 어떻게 그 리뷰 아이디로 받아오지????....????...???
                    }
                }
            }
        })
    }

    // 닉네임 변경
    private fun editUserNickName() {
        btn_mypage_user_name_edt.setOnClickListener {
            try {
                setInvisible(btn_mypage_user_name_edt)
                setVisible(edt_mypage_user_name)
                setVisible(btn_mypage_user_name_check)
                var mypageNickname: String = tv_mypage_user_name.text.toString()
                edt_mypage_user_name.setText(mypageNickname)

                // 키보드 포커스 주기
                edt_mypage_user_name.requestFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(edt_mypage_user_name, 0)
            } catch (e: Exception) {
            }
        }
        btn_mypage_user_name_check.setOnClickListener {
            try {
                setVisible(btn_mypage_user_name_edt)
                setInvisible(edt_mypage_user_name)
                setInvisible(btn_mypage_user_name_check)
                var changedName: String = edt_mypage_user_name.text.toString()
                tv_mypage_user_name.setText(changedName)
                putMypageEditNicknameResponse(changedName)
                Log.v("현주-바뀐 닉네임",changedName)
            }
            catch (e: Exception) {
            }
        }
    }

    // 닉네임 변경 서버 통신
    private fun putMypageEditNicknameResponse(changedName: String) {
        networkService.putMypageEditNicknameRequest(
                "application/json", token,
            PutMypageEditNicknameRequest(changedName))
                .enqueue(object : Callback<PutMypageEditNicknameRequest> {
                    override fun onFailure(call: Call<PutMypageEditNicknameRequest>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<PutMypageEditNicknameRequest>, response: Response<PutMypageEditNicknameRequest>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                Log.v("현주", "닉네임 변경 통신 성공  response : ${response.body()}")
                            }
                        }
                    }
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                //data에서 절대경로로 이미지를 가져옴
                var selectedPictureUri: Uri = data.data!!
                val options = BitmapFactory.Options()
                val inputStream: InputStream = contentResolver!!.openInputStream(selectedPictureUri)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                val photoBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())

                input_profile_img = MultipartBody.Part.createFormData(
                        "img",
                        File(selectedPictureUri.toString()).name + ".jpg",
                        photoBody
                )

                Log.v("현주: 사진 주소 확인", selectedPictureUri.toString())

                Glide.with(this@MypageActivity)
                        .load(selectedPictureUri)
                        .circleCrop()
                        .into(iv_mypage_user_image)

                postMypageEditProfileImageRequest(input_profile_img!!)
            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "사진 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    fun addComma(number: Int):String{
        var str: String = number.toString()
        if (str.length > 3){
            var strAfter: String = str.substring(str.length-3, str.length)
            var strBefore: String = str.substring(0, str.length -3)
            return strBefore.plus(",").plus(strAfter)
        }
        else
            return str
    }

    private fun setInvisible(view: View) {
        view.visibility = View.INVISIBLE
    }

    private fun setVisible(view: View) {
        view.visibility = View.VISIBLE
    }
}