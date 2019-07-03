package org.yamyamgoods.yamyam_android.productdetail

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.support.design.widget.AppBarLayout
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.yamyamgoods.yamyam_android.R


/**
 * Created By Yun Hyeok
 * on 7월 01, 2019
 */

class ProductDetailActivity : AppCompatActivity() {

    private var originalDetailImageHeight: Int = 0

    private val appbarListener = AppBarLayout.OnOffsetChangedListener { _, offset ->
        val isCollapsed = (-900 == offset)

        if (isCollapsed) {
            cl_product_detail_act_tabzone.visibility = View.VISIBLE
            return@OnOffsetChangedListener
        }
        cl_product_detail_act_tabzone.visibility = View.INVISIBLE
    }

    private val nestedScrollChangeListener = NestedScrollView.OnScrollChangeListener { _, _, newY, _, oldY ->
        if (newY > oldY) {
            cl_product_detail_act_bottom_bar.visibility = View.INVISIBLE
        }
        if (newY < oldY) {
            cl_product_detail_act_bottom_bar.visibility = View.VISIBLE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        setStatusBarTransparent()
        setContentScrimImage()

        viewInit()

    }

    private fun setContentScrimImage() = Glide
            .with(this)
            .asBitmap()
            .load(R.drawable.img_topthumbnail)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) { /* Nothing */
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val blurredImage = createBlurredImage(resource, 25)
                    collapsing_toolbar_product_detail_act.contentScrim = BitmapDrawable(resources, blurredImage)
                }
            })

    private fun createBlurredImage(originalBitmap: Bitmap, radius: Int): Bitmap {
        // Create another bitmap that will hold the results of the filter.
        val blurredBitmap = Bitmap.createBitmap(originalBitmap)

        // Create the Renderscript instance that will do the work.
        val rs = RenderScript.create(this)

        // Allocate memory for Renderscript to work with
        val input = Allocation.createFromBitmap(
                rs, originalBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SCRIPT)
        val output = Allocation.createTyped(rs, input.type)

        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)).apply {
            setInput(input)
            setRadius(radius.toFloat()) // Set the blur radius
            forEach(output) // Start the ScriptIntrinisicBlur
        }

        // Copy the output to the blurred bitmap
        output.copyTo(blurredBitmap)

        return blurredBitmap
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
            return
        }
        winParams.flags = winParams.flags and bits.inv()
        win.attributes = winParams
    }

    private fun viewInit() {
        setMainImageHeight()

        setPreDrawListener2DetailImage()

        moreDetailImageButtonConfig()

        appbar_product_detail_act.addOnOffsetChangedListener(appbarListener)

        scroll_product_detail_act.setOnScrollChangeListener(nestedScrollChangeListener)
    }

    private fun setMainImageHeight() {
        iv_product_detail_act_main_image.layoutParams.height = getDynamicImageHeight()
    }

    private fun getDynamicImageHeight(): Int {
        val displayMetrics = resources.displayMetrics
        val phoneWidth = displayMetrics.widthPixels
        return (phoneWidth * 321 / 360)
    }

    private fun setPreDrawListener2DetailImage() {
        val vto = iv_product_detail_act_detail_image.viewTreeObserver
        vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                iv_product_detail_act_detail_image.viewTreeObserver.removeOnPreDrawListener(this)
                val finalHeight = iv_product_detail_act_detail_image.measuredHeight
                detailImageConfig(finalHeight)
                Log.v("Malibin Debug", "onPreDraw() called")
                return true
            }
        })
    }

    private fun detailImageConfig(height: Int) {
        originalDetailImageHeight = height

        val lp = cl_product_detail_act_detail_zone.layoutParams
        lp.height = 600
        cl_product_detail_act_detail_zone.layoutParams = lp
        Log.v("Malibin Debug", "detailImageConfig() called")
    }

    private fun moreDetailImageButtonConfig() {
        btn_product_detail_act_more_detail.setOnClickListener {
            cl_product_detail_act_detail_zone.layoutParams.height = originalDetailImageHeight
            btn_product_detail_act_more_detail.visibility = View.INVISIBLE
            Log.v("Malibin Debug", "moreDetailImageButtonConfig() called")
        }
    }

}
