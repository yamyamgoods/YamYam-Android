package org.yamyamgoods.yamyam_android.home.best.review.adapter

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.ReviewData
import org.yamyamgoods.yamyam_android.home.HomeActivity
import org.yamyamgoods.yamyam_android.home.best.review.BestReviewFragment
import org.yamyamgoods.yamyam_android.reviewdetail.ReviewDetailActivity

class BestReviewRVAdapter(private val ctx: Context, var dataList: List<ReviewData>, private val fragment: BestReviewFragment) : RecyclerView.Adapter<BestReviewRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestReviewRVAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_best_review, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size


    override fun onBindViewHolder(holder: Holder, position: Int) {

        dataList[position].let { item ->
            Glide.with(ctx)
                    .load(item.user_img)
                    .centerCrop()
                    .circleCrop()
                    .into(holder.ivUserImage)

            holder.tvUserNickname.text = item.user_name
            holder.tvReviewDate.text = item.goods_review_date

            if (item.review_like_flag == 1)
                holder.ivThumb.isSelected = true
            if (item.review_like_flag == 0)
                holder.ivThumb.isSelected = false

            for (i in 0 until (item.goods_review_rating)) {
                holder.starRate[i].setImageResource(R.drawable.icon_colorstar)
            }

            holder.tvReviewContents.text = item.goods_review_content
            holder.btnDetailReview.setOnClickListener {
                try {
                    var intent = Intent(ctx, ReviewDetailActivity::class.java)
                    intent.putExtra("dto", item)
                    ctx.startActivity(intent)
                } catch (e: Exception) {
                }
            }

            var options: RequestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(10))
            var imageNum = item.goods_review_img.size
            if (item.goods_review_img.size > 3) {
                setVisible(holder.etcImageNum)
                holder.reviewImage[2].setColorFilter(Color.parseColor("#333333"), PorterDuff.Mode.MULTIPLY)
                holder.etcImageNum.text = "+" + (item.goods_review_img.size - 3).toString()
                imageNum = 3
            }
            for (i in 0 until imageNum) {
                setVisible(holder.reviewImage[i])
                Glide.with(ctx)
                        .load(item.goods_review_img[i])
                        .apply(options)
                        .into(holder.reviewImage[i])
            }

            holder.tvThumbNum.text = item.goods_review_like_count.toString()
            holder.btnComments.setImageResource(R.drawable.icon_comment)
            holder.tvCommentsNum.text = item.goods_review_cmt_count.toString()

            holder.btnThumb.setOnClickListener {
                var reviewIndex: Int = item.goods_review_idx
                Log.v("리뷰 좋아요 현재", item.review_like_flag.toString())
                // 리뷰 좋아요
                if (item.review_like_flag == 0){
                    fragment.postReviewLike(reviewIndex)
                    item.review_like_flag = 1
                    holder.ivThumb.isSelected = true
                    //holder.tvThumbNum.text = (item.goods_review_like_count + 1).toString()
                }

                // 리뷰 좋아요 취소
                else if (item.review_like_flag == 1){
                    fragment.deleteReviewLike(reviewIndex)
                    item.review_like_flag = 0
                    holder.ivThumb.isSelected = false
                    //holder.tvThumbNum.text = (item.goods_review_like_count - 1).toString()
                }
            }
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivUserImage: ImageView = itemView.findViewById(R.id.iv_rv_item_best_review_user_image) as ImageView
        var tvUserNickname: TextView = itemView.findViewById(R.id.tv_rv_item_best_review_user_nickname) as TextView
        var tvReviewDate: TextView = itemView.findViewById(R.id.tv_rv_item_best_review_date) as TextView

        var starRate: List<ImageView> = listOf(
                itemView.findViewById(R.id.iv_rv_item_best_review_star1),
                itemView.findViewById(R.id.iv_rv_item_best_review_star2),
                itemView.findViewById(R.id.iv_rv_item_best_review_star3),
                itemView.findViewById(R.id.iv_rv_item_best_review_star4),
                itemView.findViewById(R.id.iv_rv_item_best_review_star5)
        )

        var tvReviewContents: TextView = itemView.findViewById(R.id.tv_rv_item_best_review_contents) as TextView

        var reviewImage: List<ImageView> = listOf(
                itemView.findViewById(R.id.iv_rv_item_best_review_image1),
                itemView.findViewById(R.id.iv_rv_item_best_review_image2),
                itemView.findViewById(R.id.iv_rv_item_best_review_image3)
        )
        var etcImageNum: TextView = itemView.findViewById(R.id.tv_rv_item_best_review_etc_image_num) as TextView
        var btnDetailReview: ConstraintLayout = itemView.findViewById(R.id.btn_rv_item_best_review_detail_review) as ConstraintLayout
        var btnThumb: LinearLayout = itemView.findViewById(R.id.btn_rv_item_best_review_thumbs) as LinearLayout
        var ivThumb: ImageView = itemView.findViewById(R.id.iv_rv_item_best_review_thumbs) as ImageView
        var tvThumbNum: TextView = itemView.findViewById(R.id.tv_rv_item_best_review_thumbs_num) as TextView
        var btnComments: ImageView = itemView.findViewById(R.id.btn_rv_item_best_review_comments) as ImageView
        var tvCommentsNum: TextView = itemView.findViewById(R.id.tv_rv_item_best_review_comments_num) as TextView
    }


    fun setVisible(view: View) {
        view.visibility = View.VISIBLE
    }
}