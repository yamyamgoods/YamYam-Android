package org.yamyamgoods.yamyam_android.reviewwrite

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_review_write.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.reviewwrite.adapter.ReviewWriteUploadImagesRVAdapter
import org.yamyamgoods.yamyam_android.reviewwrite.dialog.DialogReviewWriteSave
import java.io.InputStream

class ReviewWriteActivity : AppCompatActivity() {
    companion object {
        var reviewWriteCtx: ReviewWriteActivity? = null
    }

    private var PICTURE_REQUEST_CODE: Int = 100

    lateinit var clipData: ClipData
    val uploadImageList = ArrayList<ReviewWriteUploadImagesItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reviewWriteCtx = this@ReviewWriteActivity

        setContentView(R.layout.activity_review_write)

        btn_review_write_save.isEnabled = false

        // 사진넣기 버튼 눌렀을 때
        btn_review_write_upload_image.setOnClickListener {
            getPermission()
            configureRecyclerView()
        }

        view_review_write_edit.setOnClickListener {
            activateKeyboard(view_review_write_edit)
        }

        btn_review_write_save.setOnClickListener {
            saveReview()
        }
    }

    override fun onResume() {
        super.onResume()
        configureRecyclerView()
        configureSaveButton()
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

                            // 클립데이터의 uri을 리사이클러뷰 데이터 클래스에 추가하기.
                            uploadImageList.add(ReviewWriteUploadImagesItem(1, item.uri.toString()))
                        }
                    }
                } else {
                    uploadImageList.add(ReviewWriteUploadImagesItem(1, uri.toString()))
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
            adapter = ReviewWriteUploadImagesRVAdapter(this@ReviewWriteActivity, uploadImageList)
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

    fun saveReview() {
        //서버랑 통신.
        // 별점, 리뷰 스트링, 사진 저장

        // 포인트 쌓기 다이얼로그 띄우기
        btn_review_write_save.setOnClickListener {
            var imageBtnDialog = DialogReviewWriteSave(this)
            imageBtnDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            imageBtnDialog.setCanceledOnTouchOutside(false)
            imageBtnDialog.show()
        }
    }
}

private fun setVisible(view: View) {
    view.visibility = View.VISIBLE
}