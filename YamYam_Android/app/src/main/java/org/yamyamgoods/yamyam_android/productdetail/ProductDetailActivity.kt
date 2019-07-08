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
import android.support.constraint.ConstraintLayout
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.productdetail.adapter.ProductDetailImageFragmentPagerAdapter
import org.yamyamgoods.yamyam_android.productdetail.adapter.ProductDetailReviewRVAdatper
import org.yamyamgoods.yamyam_android.storeweb.StoreWebActivity
import org.yamyamgoods.yamyam_android.util.TempData
import org.yamyamgoods.yamyam_android.util.dp2px
import org.yamyamgoods.yamyam_android.util.getScreenWidth
import java.lang.Exception


/**
 * Created By Yun Hyeok
 * on 7월 01, 2019
 */

class ProductDetailActivity : AppCompatActivity() {

    private var originalDetailImageHeight = 0
    private var foldedDetailImageHeight = 0

    private var originalReviewInfoYOffset = 0
    private var currentReviewInfoYOffset = 0

    private var isDetailZone = true
    private var isReviewZone = false
    private var isScrolled = false

    private val blurredImages: MutableMap<String, BitmapDrawable> = mutableMapOf()
    private lateinit var mainImageUrls: List<String>
    private lateinit var thumbnailImages: List<ImageView>

    private lateinit var currentIndicatorPosition: ConstraintLayout
    private lateinit var thumbnailFrame: List<ConstraintLayout>

    private val appbarListener = AppBarLayout.OnOffsetChangedListener { _, offset ->
        val isCollapsed = (-900 == offset)

        if (isCollapsed) {
            cl_product_detail_act_tabzone.visibility = View.VISIBLE
            return@OnOffsetChangedListener
        }
        cl_product_detail_act_tabzone.visibility = View.INVISIBLE
    }

    private val nestedScrollChangeListener = NestedScrollView.OnScrollChangeListener { _, _, newY, _, oldY ->
        isScrolled = true
        if (newY > oldY) {
            cl_product_detail_act_bottom_bar.visibility = View.INVISIBLE
        }
        if (newY < oldY) {
            cl_product_detail_act_bottom_bar.visibility = View.VISIBLE
        }
        if (newY <= currentReviewInfoYOffset && !isDetailZone) {
            tl_product_detail_act_tab.getTabAt(0)!!.select()
            isDetailZone = true
            isReviewZone = false
        }
        if (newY >= currentReviewInfoYOffset && !isReviewZone) {
            tl_product_detail_act_tab.getTabAt(1)!!.select()
            isDetailZone = false
            isReviewZone = true
        }
        isScrolled = false
    }

    private val mainImagesPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(position: Int) {
        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(position: Int) {
            setContentScrimImage(position)
            selectIndicatorAt(position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        mainImageUrls = TempData.imageUrls3()
        getBlurredImageList(mainImageUrls)

        setStatusBarTransparent()

        viewInit()
    }

    override fun onBackPressed() {
        val panelState = slide_product_detail_act_panel.panelState
        if (panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            slide_product_detail_act_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            return
        }
        super.onBackPressed()
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

    private fun getBlurredImageList(imageUrls: List<String>) {
        for (imageUrl in imageUrls) {
            Glide
                    .with(this)
                    .asBitmap()
                    .load(imageUrl)
                    .centerCrop()
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onLoadCleared(placeholder: Drawable?) {
                        }

                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            val blurredImage = createBlurredImage(resource, 25)
                            val bitmapDrawable = BitmapDrawable(resources, blurredImage)
                            blurredImages[imageUrl] = bitmapDrawable
                            setContentScrimImage(0)
                        }
                    })
        }
    }

    private fun setContentScrimImage(position: Int) {
        val imageUrl = mainImageUrls[position]
        collapsing_toolbar_product_detail_act.contentScrim = blurredImages[imageUrl]
    }

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

        mainImagesZoneInit()

        detailImageZoneInit()

        reviewZoneInit()

        setTabBarClickListener()

        scroll_product_detail_act.setOnScrollChangeListener(nestedScrollChangeListener)

        bottomBarInit()

        slideUpPanelLayoutConfig()

    }

    private fun mainImagesZoneInit() {

        appbar_product_detail_act.addOnOffsetChangedListener(appbarListener)

        setMainImagesHeight()

        mainImagesViewPagerInit()

        thumbnailImageViewBinding()
        thumbnailFrameBinding()

        thumbnailIndicatorInit()
        setThumbnailIndicatorClickListener()
    }

    private fun detailImageZoneInit() {

        setPreDrawListener2DetailImageForHeight()

        moreDetailImageButtonConfig()

    }

    private fun reviewZoneInit() {
        rv_product_detail_act_review_list.apply {
            adapter = ProductDetailReviewRVAdatper(this@ProductDetailActivity, TempData.ReviewAll())
            layoutManager = LinearLayoutManager(this@ProductDetailActivity)
        }
    }

    private fun setMainImagesHeight() {
        cl_product_detail_act_main_image_frame.layoutParams.height = getDynamicImageHeight()
        vp_product_detail_act_main_image.layoutParams.height = getDynamicImageHeight()
    }

    private fun getDynamicImageHeight(): Int {
        val phoneWidth = getScreenWidth(this)
        return (phoneWidth * 321 / 360)
    }

    private fun setPreDrawListener2DetailImageForHeight() {
        val vto = iv_product_detail_act_detail_image.viewTreeObserver
        vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                iv_product_detail_act_detail_image.viewTreeObserver.removeOnPreDrawListener(this)
                val finalHeight = iv_product_detail_act_detail_image.measuredHeight
                detailImageConfig(finalHeight)
                return true
            }
        })
    }

    private fun detailImageConfig(height: Int) {
        originalDetailImageHeight = height
        foldedDetailImageHeight = dp2px(288f, applicationContext)
        detailImageTopCrop()
        cl_product_detail_act_detail_zone.apply {
            val layoutParams = this.layoutParams
            layoutParams.height = foldedDetailImageHeight
            this.layoutParams = layoutParams
        }
        setPreDrawListener2InfoZoneForHeight()
    }

    private fun setPreDrawListener2InfoZoneForHeight() {
        val vto = cl_product_detail_act_info_zone.viewTreeObserver
        vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                cl_product_detail_act_info_zone.viewTreeObserver.removeOnPreDrawListener(this)
                val finalHeight = cl_product_detail_act_info_zone.measuredHeight
                initReviewInfoYOffset(finalHeight)
                return true
            }
        })
    }

    private fun initReviewInfoYOffset(infoZoneHeight: Int) {
        currentReviewInfoYOffset = infoZoneHeight - dp2px(48f, this)
        val imageHeightDiff = originalDetailImageHeight - foldedDetailImageHeight
        originalReviewInfoYOffset = currentReviewInfoYOffset + imageHeightDiff
    }

    private fun detailImageTopCrop() {
        val phoneWidth = getScreenWidth(this)
        val imageWidth = iv_product_detail_act_detail_image.drawable.intrinsicWidth
        val transX = ((phoneWidth - imageWidth) / 2 - dp2px(16f, this)).toFloat()

        val matrix = iv_product_detail_act_detail_image.imageMatrix
        matrix.postTranslate(transX, 0f)
        iv_product_detail_act_detail_image.imageMatrix = matrix
    }

    private fun expandDetailImage() {
        cl_product_detail_act_detail_zone.apply {
            val layoutParams = this.layoutParams
            layoutParams.height = originalDetailImageHeight
            this.layoutParams = layoutParams
        }
        iv_product_detail_act_detail_image.scaleType = ImageView.ScaleType.CENTER_CROP
    }

    private fun moreDetailImageButtonConfig() {
        btn_product_detail_act_more_detail.setOnClickListener {
            expandDetailImage()
            currentReviewInfoYOffset = originalReviewInfoYOffset
            it.visibility = View.INVISIBLE
        }
    }

    private fun setTabBarClickListener() {
        val tabLayout = tl_product_detail_act_tab
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (!isScrolled) {
                    when (tab) {
                        tabLayout.getTabAt(0) ->
                            scroll_product_detail_act.scrollTo(0, 0)
                        tabLayout.getTabAt(1) ->
                            scroll_product_detail_act.scrollTo(0, currentReviewInfoYOffset)
                    }
                }
            }
        })
    }

    private fun bottomBarInit() {
        btn_product_detail_act_visit_store.setOnClickListener {
            startActivity<StoreWebActivity>(
                    "storeUrl" to "https://nightmare73.blog.me",
                    "storeName" to "스토어이름"
            )
        }

        btn_product_detail_act_estimate.setOnClickListener {
            slide_product_detail_act_panel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
        }
    }

    private fun slideUpPanelLayoutConfig() {
        cl_product_detail_act_slide_panel.setOnClickListener { return@setOnClickListener }

        btn_product_detail_act_slide_close.setOnClickListener {
            slide_product_detail_act_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }

        et_product_detail_act_slide_amount.setOnClickListener {

        }

        btn_product_detail_act_slide_bookmark.setOnClickListener {
        }
    }

    private fun mainImagesViewPagerInit() {
        vp_product_detail_act_main_image.apply {
            adapter = ProductDetailImageFragmentPagerAdapter(supportFragmentManager).apply {
                addImages(mainImageUrls)
                addOnPageChangeListener(mainImagesPageChangeListener)
                offscreenPageLimit = mainImageUrls.size
            }
        }
    }

    private fun thumbnailImageViewBinding() {
        thumbnailImages = listOf(
                findViewById(R.id.iv_product_detail_act_thumbnail1),
                findViewById(R.id.iv_product_detail_act_thumbnail2),
                findViewById(R.id.iv_product_detail_act_thumbnail3),
                findViewById(R.id.iv_product_detail_act_thumbnail4),
                findViewById(R.id.iv_product_detail_act_thumbnail5),
                findViewById(R.id.iv_product_detail_act_thumbnail6),
                findViewById(R.id.iv_product_detail_act_thumbnail7),
                findViewById(R.id.iv_product_detail_act_thumbnail8),
                findViewById(R.id.iv_product_detail_act_thumbnail9)
        )
    }

    private fun thumbnailFrameBinding() {
        thumbnailFrame = listOf(
                findViewById(R.id.cl_product_detail_act_thumbnail1),
                findViewById(R.id.cl_product_detail_act_thumbnail2),
                findViewById(R.id.cl_product_detail_act_thumbnail3),
                findViewById(R.id.cl_product_detail_act_thumbnail4),
                findViewById(R.id.cl_product_detail_act_thumbnail5),
                findViewById(R.id.cl_product_detail_act_thumbnail6),
                findViewById(R.id.cl_product_detail_act_thumbnail7),
                findViewById(R.id.cl_product_detail_act_thumbnail8),
                findViewById(R.id.cl_product_detail_act_thumbnail9)
        )
    }

    private fun setThumbnailIndicatorClickListener() {

        currentIndicatorPosition = thumbnailFrame[0].apply {
            isSelected = true
        }
        thumbnailFrame.forEach {
            it.setOnClickListener { indicator ->
                val position = thumbnailFrame.indexOf(indicator)
                selectViewPagerAt(position)
                selectIndicatorAt(position)
            }
        }
    }

    private fun thumbnailIndicatorInit() {
        for (i in 0 until 9) {
            try {
                Glide
                        .with(this)
                        .load(mainImageUrls[i])
                        .into(thumbnailImages[i])

            } catch (e: Exception) {
                thumbnailFrame[i].visibility = View.GONE
            }
        }
    }

    private fun selectIndicatorAt(position: Int) {
        currentIndicatorPosition.isSelected = false
        currentIndicatorPosition = thumbnailFrame[position]
        currentIndicatorPosition.isSelected = true
    }

    private fun selectViewPagerAt(position: Int){
        vp_product_detail_act_main_image.currentItem = position
    }


}
