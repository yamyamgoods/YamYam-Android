package org.yamyamgoods.yamyam_android.productdetail

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.util.px2dp

/**
 * Created By Yun Hyeok
 * on 7월 01, 2019
 */

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        setStatusBarTransparent()

        viewInit()

        test()
    }

    private fun setStatusBarTransparent() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

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

    private fun viewInit() {
        setImageCollapsingToolBar()
    }

    private fun setImageCollapsingToolBar() {
        collapsing_toolbar_product_detail_act.apply {
            contentScrim

        }

        val lp = iv_product_detail_act_image.layoutParams
        lp.height = getDynamicImageHeight()

        iv_product_detail_act_image.layoutParams = lp
    }

    private fun getDynamicImageHeight(): Int {
        val displayMetrics = resources.displayMetrics
        val phoneWidth = displayMetrics.widthPixels
        return (phoneWidth * 321 / 360)
    }

    private fun test() {
        val displayMetrics = resources.displayMetrics
        val phoneWidth = displayMetrics.widthPixels
        val phoneHeight = displayMetrics.heightPixels
        Log.v("Malibin Debug", "widthpx : $phoneWidth, heightPx: $phoneHeight")

        val phoneWidthDp = px2dp(phoneWidth, this)
        val phoneHeightDp = px2dp(phoneHeight, this)
        Log.v("Malibin Debug", "widthDp : $phoneWidthDp, heightDp: $phoneHeightDp")
    }
}
