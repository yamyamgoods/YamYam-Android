package org.yamyamgoods.yamyam_android

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import kotlinx.android.synthetic.main.activity_login.*
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.kakao.auth.AuthType
import com.kakao.auth.Session
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.home.HomeActivity
import org.yamyamgoods.yamyam_android.util.SessionCallback
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setOnClickListener()
    }

    private fun setOnClickListener(){

        getHashkey()

        btn_login_act_kakao_login.setOnClickListener {
            val session = Session.getCurrentSession()
            Log.e("TAG","에베베베베")
            session.addCallback(SessionCallback(this))
            session.open(AuthType.KAKAO_ACCOUNT, this)
        }

        btn_login_act_twitter_login.setOnClickListener {
            Toast.makeText(this, "트위터 로그인 서비스 불가능", Toast.LENGTH_LONG).show()
        }

        btn_login_act_without_login.setOnClickListener {
            //로그인 없이
            //그냥 바로 HomeActivity로 가도록
            startActivity<HomeActivity>()
        }
    }

    fun getHashkey(){
        try{
            val info = getPackageManager().getPackageInfo("org.yamyamgoods.twitterlogin", PackageManager.GET_SIGNATURES)

            for(signature in info.signatures){
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("TAG: ","key_hash: "+ Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }

        } catch (e : PackageManager.NameNotFoundException){
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException){
            e.printStackTrace()
        }
    }
}
