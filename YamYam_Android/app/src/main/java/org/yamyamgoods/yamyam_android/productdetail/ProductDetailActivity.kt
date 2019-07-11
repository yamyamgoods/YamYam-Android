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
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.ProductOption
import org.yamyamgoods.yamyam_android.dataclass.SelectedOption
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.GoodsDetail
import org.yamyamgoods.yamyam_android.network.get.ProductDetailData
import org.yamyamgoods.yamyam_android.productdetail.adapter.ProductDetailImageFragmentPagerAdapter
import org.yamyamgoods.yamyam_android.productdetail.adapter.ProductDetailReviewRVAdatper
import org.yamyamgoods.yamyam_android.productdetail.adapter.ProductOptionsRVAdapter
import org.yamyamgoods.yamyam_android.review.ReviewActivity
import org.yamyamgoods.yamyam_android.dataclass.ReviewData
import org.yamyamgoods.yamyam_android.network.post.PostBookmarkRequestDTO
import org.yamyamgoods.yamyam_android.productdetail.dialog.BookmarkCheckDialog
import org.yamyamgoods.yamyam_android.productdetail.dialog.LoginRequestDialog
import org.yamyamgoods.yamyam_android.reviewwrite.ReviewWriteActivity
import org.yamyamgoods.yamyam_android.storeweb.StoreWebActivity
import org.yamyamgoods.yamyam_android.util.dp2px
import org.yamyamgoods.yamyam_android.util.getScreenWidth
import java.lang.Exception
import java.text.NumberFormat
import java.util.*

/**
 * Created By Yun Hyeok
 * on 7월 01, 2019
 *
 * StartActivity 로 호출 시
 * storeIdx 를 intent 로 반드시 넘겨야 합니다.
 */

class ProductDetailActivity : AppCompatActivity(), ProductDetailContract.View {

    override lateinit var presenter: ProductDetailContract.Presenter

    private var originalDetailImageHeight = 0
    private var foldedDetailImageHeight = 0

    private var originalReviewInfoYOffset = 0
    private var currentReviewInfoYOffset = 0

    private var isDetailZone = true
    private var isReviewZone = false
    private var isScrolled = false
    private var isBookmarked = false

    private var goodsIdx = -1
    private var oneTotalPrice = -1
    private var productQuantity = 1
    private var selectedOptions: List<SelectedOption>? = null

    private val blurredImages: MutableMap<String, BitmapDrawable> = mutableMapOf()
    private lateinit var mainImageUrls: List<String>
    private lateinit var thumbnailImages: List<ImageView>
    private lateinit var starImages: List<ImageView>
    private lateinit var currentIndicatorPosition: ConstraintLayout
    private lateinit var thumbnailFrame: List<ConstraintLayout>

    private lateinit var integrateData: ProductDetailData

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

    private val detailImageRequestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            iv_product_detail_act_detail_image.setImageDrawable(resource)
            setPreDrawListener2DetailImageForHeight()
            moreDetailImageButtonConfig()
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        progressBarOn()

        presenterInit()
        getServerData()

        setStatusBarTransparent()
    }

    override fun onBackPressed() {
        val panelState = slide_product_detail_act_panel.panelState
        if (panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            slide_product_detail_act_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            return
        }
        super.onBackPressed()
    }

    override fun showServerFailToast(message: String, t: Throwable) {
        toast(message)
        Log.v("Malibin Debug", "t : ${t.message}, stack : ${TextUtils.join("\n", t.stackTrace)}")
    }

    override fun setProductDetailData(response: ProductDetailData) {
        integrateData = response
        mainImageUrls = integrateData.goods.goods_img
        getBlurredImageList(mainImageUrls)
        viewInit()
    }

    override fun setProductOptionData(response: List<ProductOption>) {
        rv_product_detail_act_slide_option_list.apply {
            adapter = ProductOptionsRVAdapter(this@ProductDetailActivity, response).apply {
                basePrice = integrateData.goods.goods_price.replace(",", "").toInt()
                totalPrice = basePrice
            }
            layoutManager = LinearLayoutManager(this@ProductDetailActivity)
        }
        Log.v("Malibin Debug", response.toString())
    }

    override fun showBookmarkSuccessDialog() {
        slide_product_detail_act_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        isBookmarked = true
        iv_product_detail_act_bookmark.isSelected = true
        BookmarkCheckDialog(this).show()
    }

    override fun showBookmarkCancelToast() {

    }

    override fun showLoginRequiredDialog() {
        LoginRequestDialog(this).show()
    }

    override fun showAlreadySameOptionsBookmarkToast() {
        toast("해당 굿즈에 이미 같은 견적이 있습니다.")
    }

    override fun showAlreadySameLabelBookmarkToast() {
        toast("해당 굿즈에 이미 같은 라벨이 있습니다.")
    }

    private fun progressBarOn() {
        val color = ContextCompat.getColor(this, R.color.MainYellow)
        progressBar_product_detail_act.visibility = View.VISIBLE
        progressBar_product_detail_act.indeterminateDrawable
            .setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)
    }

    private fun progressBarOff() {
        progressBar_product_detail_act.visibility = View.GONE
        slide_product_detail_act_panel.visibility = View.VISIBLE
    }

    private fun presenterInit() {
        presenter = ProductDetailPresenter().apply {
            view = this@ProductDetailActivity
            goodsRepository = ApplicationController.networkServiceGoods
            userToken =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJpYXQiOjE1NjIzMTUzNjYsImV4cCI6MTU2MzYyOTM2Nn0.ZkDGasoDPHTrGvy7yFOT9cPjTQ7gnnUOqekY_zYrAuc"
        }
    }

    private fun getServerData() {
        goodsIdx = intent.getIntExtra("goodsIdx", -1)
        presenter.getProductDetailData(goodsIdx)
        //presenter.getProductOptionData(goodsIdx)
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
            rs, originalBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SCRIPT
        )
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

        infoZoneInit(integrateData.goods)

        reviewZoneInit(integrateData.reviews)

        setTabBarClickListener()

        scroll_product_detail_act.setOnScrollChangeListener(nestedScrollChangeListener)

        bottomBarInit()

        slideUpPanelLayoutConfig()

        progressBarOff()
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
        setDetailImage(integrateData.goods.goods_detail)
    }

    private fun infoZoneInit(data: GoodsDetail) {
        tv_product_detail_act_store_name.text = data.store_name
        tv_product_detail_act_goods_name.text = data.goods_name
        setStarRate(data.goods_rating)
        tv_product_detail_act_price.text = data.goods_price
        tv_product_detail_act_deliver_cost.text = data.goods_delivery_charge
        tv_product_detail_act_deliver_deadline.text = data.goods_delivery_period
        tv_product_detail_act_min_amount.text = data.goods_minimum_amount

        isBookmarked = (data.scrap_flag == 1)
        bookmarkInit()

    }

    private fun setStarRate(rate: Float) {
        val fullStarNum: Int = rate.toInt()
        val halfStarNum: Int = ((rate - fullStarNum) * 2).toInt()

        starImageBinding()
        tv_product_detail_act_star_rate.text = rate.toString()

        for (i in 0 until fullStarNum) {
            starImages[i].imageResource = R.drawable.img_goods_star
        }
        if (halfStarNum == 1)
            starImages[fullStarNum].imageResource = R.drawable.img_goods_star_half

    }

    private fun bookmarkInit() {
        if (isBookmarked)
            iv_product_detail_act_bookmark.isSelected = true
        iv_product_detail_act_bookmark.setOnClickListener {
            toast("북마크통신해야함 눌렀을 때의 불린 :  $isBookmarked")
            if (isBookmarked) {
                it.isSelected = false
                isBookmarked = false
                //북마크 취소통신
                return@setOnClickListener
            }
            it.isSelected = true
            isBookmarked = true
            //북마크 요청통신
        }
    }

    private fun reviewZoneInit(dataList: List<ReviewData>) {
        rv_product_detail_act_review_list.apply {
            adapter = ProductDetailReviewRVAdatper(this@ProductDetailActivity, dataList)
            layoutManager = LinearLayoutManager(this@ProductDetailActivity)
        }

        listOf(
            btn_product_detail_act_review_count,
            btn_product_detail_act_review_more
        ).forEach {
            it.setOnClickListener {
                startActivity<ReviewActivity>("goodsIdx" to goodsIdx)
            }
        }
        btn_product_detail_act_review_write.setOnClickListener {
            startActivity<ReviewWriteActivity>("goodsIdx" to goodsIdx)
        }
        tv_product_detail_act_review_count.text = integrateData.goods.goods_review_cnt.toString()
        tv_product_detail_act_review_more_count.text = integrateData.goods.goods_review_cnt.toString()
    }

    private fun setMainImagesHeight() {
        cl_product_detail_act_main_image_frame.layoutParams.height = getDynamicImageHeight()
        vp_product_detail_act_main_image.layoutParams.height = getDynamicImageHeight()
    }

    private fun getDynamicImageHeight(): Int {
        val phoneWidth = getScreenWidth(this)
        return (phoneWidth * 321 / 360)
    }

    private fun setDetailImage(imageUrl: String) {
        Glide.with(this).load(imageUrl).listener(detailImageRequestListener).into(iv_product_detail_act_detail_image)
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
                "storeUrl" to integrateData.store.store_url,
                "storeName" to integrateData.goods.store_name
            )
        }

        btn_product_detail_act_estimate.setOnClickListener {
            slide_product_detail_act_panel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
        }
    }

    private fun slideUpPanelLayoutConfig() {
        btn_product_detail_act_slide_close.setOnClickListener {
            slide_product_detail_act_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }

        et_product_detail_act_slide_amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.isEmpty()) {
                    productQuantity = 0
                    notifyTotalPrice()
                    return
                }
                productQuantity = s.toString().toInt()
                notifyTotalPrice()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        //찜하기버튼
        btn_product_detail_act_slide_bookmark.setOnClickListener {
            val body = getPostBookmarkRequestDTO()
            presenter.bookmarkRequest(body)
            //slide_product_detail_act_panel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }

        tv_product_detail_act_slide_main_name.text =
            ("[${integrateData.goods.store_name}] ${integrateData.goods.goods_name}")

        et_product_detail_act_slide_tag.setText(integrateData.goods.goods_name)

        tv_product_detail_act_slide_total_price.text = integrateData.goods.goods_price
    }

    private fun getPostBookmarkRequestDTO() =
        PostBookmarkRequestDTO(
            integrateData.goods.goods_idx,
            oneTotalPrice * productQuantity,
            et_product_detail_act_slide_tag.text.toString(),
            getSelectedOptionsAddedAmount()
        )

    private fun getSelectedOptionsAddedAmount(): List<SelectedOption> {
        val result = ArrayList<SelectedOption>()
        result.addAll(selectedOptions!!)
        result.add(
            SelectedOption(
                "수량", et_product_detail_act_slide_amount.text.toString()
            )
        )
        return result
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

    private fun starImageBinding() {
        starImages = listOf(
            findViewById(R.id.iv_product_detail_act_star1),
            findViewById(R.id.iv_product_detail_act_star2),
            findViewById(R.id.iv_product_detail_act_star3),
            findViewById(R.id.iv_product_detail_act_star4),
            findViewById(R.id.iv_product_detail_act_star5)
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

    private fun selectViewPagerAt(position: Int) {
        vp_product_detail_act_main_image.currentItem = position
    }

    private fun toNumberFormat(price: Int): String = NumberFormat.getNumberInstance(Locale.US).format(price)

    fun refreshOptionData(totalPrice: Int, selectedOptions: List<SelectedOption>) {
        this.oneTotalPrice = totalPrice
        this.selectedOptions = selectedOptions
    }

    fun notifyTotalPrice() {
        val totalPrice = toNumberFormat(oneTotalPrice * productQuantity)
        tv_product_detail_act_slide_total_price.text = totalPrice
    }
}
