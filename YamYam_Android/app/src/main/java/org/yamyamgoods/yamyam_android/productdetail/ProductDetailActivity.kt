package org.yamyamgoods.yamyam_android.productdetail

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import org.yamyamgoods.yamyam_android.R



/**
 * Created By Yun Hyeok
 * on 7월 01, 2019
 */

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        configureStatusBar()
    }

    private fun configureStatusBar() {

        // 그냥 상태바 색만 흰색이 된다. 레이아웃이 겹치지않음

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        //window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //이걸 쓰면 내가 원하는 대로 되지만 시계나 그런 텍스트가 표시되지 않음.
        //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        if (Build.VERSION.SDK_INT >= 21) {
//            window.statusBarColor = Color.WHITE
//        }
//
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        if (Build.VERSION.SDK_INT >= 21) {
            window.statusBarColor = Color.TRANSPARENT
        }



    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
