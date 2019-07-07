package org.yamyamgoods.yamyam_android.reviewdetail

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_review_detail.*
import kotlinx.android.synthetic.main.rv_item_review_detail_comment.*
import org.jetbrains.anko.ctx
import org.w3c.dom.Text
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.best.review.BestReviewFragment
import org.yamyamgoods.yamyam_android.reviewdetail.adapter.ReviewDetailRVAdapter
import org.yamyamgoods.yamyam_android.util.TempData
import java.io.Serializable


class ReviewDetailActivity : AppCompatActivity() {

    lateinit var edtComment: EditText;

    var options: RequestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(10))

    var starCount: Int = 0
    var thumbCount: Int = 0
    var commentCount: Int = 0
    var thumbFlag: Int = 0
    lateinit var userNickname: String
    lateinit var date: String
    lateinit var reviewContents: String
    lateinit var imageUrl: List<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_detail)
        getVariables()
        configureProduct(TempData.ReviewDetailProducts(), 2)
        configureReview()
        configureComments()
    }

    private fun getVariables() {
        setResult(Activity.RESULT_OK, intent)
        var rvDTO: ReviewBasicDTO = intent.getParcelableExtra("dto")
        userNickname = rvDTO.userNickname
        date = rvDTO.date
        reviewContents = rvDTO.reviewContents

        starCount = rvDTO.starCount
        imageUrl = rvDTO.imageUrl
        thumbFlag = rvDTO.thumbFlag
        thumbCount = rvDTO.thumbCount
        commentCount = rvDTO.commentsCount
    }

    private fun configureProduct(dataList: List<ProductShortInfo>, position: Int) {
        dataList[position].let { item ->
            Glide.with(this)
                    .load(item.productImageUrl)
                    .apply(options)
                    .into(findViewById(R.id.iv_review_detail_product))

            tv_review_detail_store_name.text = item.storeName
            tv_review_detail_product_name.text = item.productName
            tv_review_detail_product_price.text = item.amount

            var productStarRate: List<ImageView> = listOf(
                    findViewById(R.id.iv_review_detail_poduct_star1),
                    findViewById(R.id.iv_review_detail_poduct_star2),
                    findViewById(R.id.iv_review_detail_poduct_star3),
                    findViewById(R.id.iv_review_detail_poduct_star4),
                    findViewById(R.id.iv_review_detail_poduct_star5)
            )

            var productStarCount = item.starRate
            var intStarCount: Int = productStarCount.toInt()
            var remainder: Float = item.starRate - intStarCount.toFloat()

            for (i in 0 until (intStarCount)) {
                productStarRate[i].setImageResource(R.drawable.img_goods_star)
                if (0.5 > remainder && remainder >= 0)
                    continue
                if (1 > remainder && remainder >= 0.5)
                    productStarRate[i + 1].setImageResource(R.drawable.img_goods_star_half)
            }
        }
    }

    private fun configureReview() {
        Glide.with(this)
                .load(intent.getStringExtra("userImageUrl"))
                .centerCrop()
                .circleCrop()
                .into(findViewById(R.id.iv_review_detail_review_user_img))

        tv_review_detail_review_user_nickname.text = userNickname
        tv_review_detail_review_date.text = date
        tv_review_detail_review_contents.text = reviewContents

        var starRate: List<ImageView> = listOf(
                findViewById(R.id.iv_review_detail_review_star1),
                findViewById(R.id.iv_review_detail_review_star2),
                findViewById(R.id.iv_review_detail_review_star3),
                findViewById(R.id.iv_review_detail_review_star4),
                findViewById(R.id.iv_review_detail_review_star5)
        )

        var reviewImage: List<ImageView> = listOf(
                findViewById(R.id.iv_review_detail_review_image1),
                findViewById(R.id.iv_review_detail_review_image2),
                findViewById(R.id.iv_review_detail_review_image3)
        )

        var imageNum = imageUrl.size

        for (i in 0 until (starCount)) {
            starRate[i].setImageResource(R.drawable.icon_colorstar)
        }

        var etcImageNum: TextView = findViewById(R.id.tv_review_detail_review_etc_image_num) as TextView
        if (imageUrl.size > 3) {
            setVisible(etcImageNum)
            reviewImage[2].setColorFilter(Color.parseColor("#333333"), PorterDuff.Mode.MULTIPLY)
            etcImageNum.text = "+" + (imageUrl.size - 3).toString()
            imageNum = 3
        }
        for (i in 0 until imageNum) {
            setVisible(reviewImage[i])
            Glide.with(this)
                    .load(imageUrl[i])
                    .apply(options)
                    .into(reviewImage[i])
        }

        if (thumbFlag == 1)
            iv_review_detail_review_thumbs.isSelected  = !(iv_review_detail_review_thumbs.isSelected)
        btn_review_detail_review_thumbs.setOnClickListener{
            thumbFlag = Math.abs(thumbFlag - 1)
            iv_review_detail_review_thumbs.isSelected  = !(iv_review_detail_review_thumbs.isSelected)
            BestReviewFragment.instance.flag = 1
        }

        tv_review_detail_review_thumbs_num.text = thumbCount.toString()
        tv_rv_item_best_review_all_comments_num.text = commentCount.toString()
    }

    private fun configureComments() {
        rv_review_detail_comment_list.apply {
            adapter = ReviewDetailRVAdapter(this@ReviewDetailActivity, TempData.ReviewComments())
            layoutManager = LinearLayoutManager(this@ReviewDetailActivity)
        }
    }

    fun editTextTag(nickname: String){
        edtComment =  findViewById(R.id.edt_review_detail_input_comment)
        var originText: String = edtComment.getText().toString()

        var tagUser: String = originText.plus("@").plus(nickname).plus(" ")
        edt_review_detail_input_comment.setText(tagUser)
        edt_review_detail_input_comment.setSelection(edt_review_detail_input_comment.length())
    }

   fun setVisible(view: View) {
        view.visibility = View.VISIBLE
    }
}