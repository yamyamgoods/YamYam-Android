package org.yamyamgoods.yamyam_android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.yamyamgoods.yamyam_android.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    val SPLASH_TIME_OUT: Long = 3000 //3초간 보여 주고 넘어 간다.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}
