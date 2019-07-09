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
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.mypage.adapter.MypageProductRVAdapter
import org.yamyamgoods.yamyam_android.mypage.alarm.adapter.MypageAlarmRVAdapter
import org.yamyamgoods.yamyam_android.mypage.dialog.DialogMypageChangeProfileImage
import org.yamyamgoods.yamyam_android.mypage.recent.RecentlyViewedProductsActivity
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.NetworkServiceUser
import org.yamyamgoods.yamyam_android.network.get.GetUserInfoResponse
import org.yamyamgoods.yamyam_android.network.put.PutMypageEditNicknameRequest
import org.yamyamgoods.yamyam_android.reviewwrite.ReviewWriteActivity
import org.yamyamgoods.yamyam_android.util.TempData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class MypageActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST: Int = 1
    //public lateinit var mypageCtx: Context

    val networkService: NetworkServiceUser by lazy {
        ApplicationController.networkServiceUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        getUserInfoResponse()

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
    }

    fun getUserInfoResponse() {
        networkService.getUserInfoResponse(
                "application/json",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjozLCJpYXQiOjE1NjI2NTY3MTMsImV4cCI6MTU5NDE5MjcxM30.nfcJqImHl5XPMPigkka-wF09v8_ji67Vt4b0nOSX4KY")
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
                                var strPoint: String = it.data!!.user_point.toString()
                                if (strPoint.length > 3) {
                                    var strAfter: String = strPoint.substring(strPoint.length - 3, strPoint.length)
                                    var strBefore: String = strPoint.substring(0, strPoint.length - 3)
                                    tv_mypage_point.setText(strBefore.plus(",").plus(strAfter))
                                    Log.v("현주", strBefore.plus(",").plus(strAfter).toString())
                                } else
                                    tv_mypage_point.setText(strPoint.toString())

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

    fun setProfileImageDefault() {
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
                putMypageEditNickNameResponse(changedName)
            } catch (e: Exception) {
            }
        }
    }

    fun putMypageEditNickNameResponse(changedName: String) {
        networkService.putMypageEditNicknameRequest(
                "application/json",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjozLCJpYXQiOjE1NjI2NTY3MTMsImV4cCI6MTU5NDE5MjcxM30.nfcJqImHl5XPMPigkka-wF09v8_ji67Vt4b0nOSX4KY",
                PutMypageEditNicknameRequest(changedName))
                .enqueue(object : Callback<PutMypageEditNicknameRequest> {
                    override fun onFailure(call: Call<PutMypageEditNicknameRequest>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<PutMypageEditNicknameRequest>, response: Response<PutMypageEditNicknameRequest>) {

                        if (response.isSuccessful) {
                            response.body()?.let {
                                Log.v("현주", "마이페이지 서버 통신 성공  response : ${response.body()}")
                            }
                        }
                    }
                })
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