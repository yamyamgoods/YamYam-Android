package org.yamyamgoods.yamyam_android.mypage

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_mypage.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.mypage.adapter.MypageProductRVAdapter
import org.yamyamgoods.yamyam_android.mypage.alarm.adapter.MypageAlarmRVAdapter
import org.yamyamgoods.yamyam_android.mypage.dialog.DialogMypageChangeProfileImage
import org.yamyamgoods.yamyam_android.mypage.recent.RecentlyViewedProductsActivity
import org.yamyamgoods.yamyam_android.reviewwrite.ReviewWriteActivity
import org.yamyamgoods.yamyam_android.util.TempData

class MypageActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST: Int = 1
    //public lateinit var mypageCtx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        configureTitleBar()
        editUserNickName()
        configureComments()
        configureAlarmDrawer()
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

        // ReviewWriteActivity에서 호출 가능하도록
        //mypageCtx = this
    }

    private fun configureTitleBar() {
        btn_mypage_close.setOnClickListener {
            finish()
        }
    }

    private fun changeProfileImage() {
        // 프로필 이미지 변경 다이얼로그 띄우기
        btn_mypage_user_image_edit.setOnClickListener{
            var imageBtnDialog = DialogMypageChangeProfileImage(this)
            imageBtnDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            imageBtnDialog.setCanceledOnTouchOutside(false)
            imageBtnDialog.show()
        }
    }

    fun setProfileImageDefault(){
        iv_mypage_user_image.setImageResource(R.drawable.img_myprofile)
    }

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
        intent.setType("image/*")
        startActivityForResult(Intent.createChooser(intent, "얌얌굿즈 : 프로필 사진을 선택해주세요!"), PICK_IMAGE_REQUEST)
    }

    private fun configureComments() {
        rv_mypage_recently_viewed_product_list.apply {
            adapter = MypageProductRVAdapter(this@MypageActivity, TempData.mypageProducts())
            layoutManager = LinearLayoutManager(this@MypageActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun openRecentActivity() {
        btn_mypage_recently_viewed_product.setOnClickListener {
            val intent = Intent(this, RecentlyViewedProductsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openAlarmDrawer() {
        val drawer: DrawerLayout = findViewById(R.id.drawer_mypage_alarm)
        if (!drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.openDrawer(Gravity.RIGHT)
        }
    }

    private fun configureAlarmDrawer() {
        rv_mypage_alarm_list.apply {
            adapter = MypageAlarmRVAdapter(this@MypageActivity, TempData.mypageAlarms())
            layoutManager = LinearLayoutManager(this@MypageActivity)
        }
    }

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
                //바뀐 닉네임 통신.
            } catch (e: Exception) {
            }
        }
    }

    private fun setInvisible(view: View) {
        view.visibility = View.INVISIBLE
    }

    private fun setVisible(view: View) {
        view.visibility = View.VISIBLE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                //data에서 절대경로로 이미지를 가져옴
                var uri: Uri = data.data!!

                Glide.with(this)
                        .load(uri)
                        .circleCrop()
                        .into(iv_mypage_user_image)

            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "사진 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show()
            e.printStackTrace();
        }
    }
}