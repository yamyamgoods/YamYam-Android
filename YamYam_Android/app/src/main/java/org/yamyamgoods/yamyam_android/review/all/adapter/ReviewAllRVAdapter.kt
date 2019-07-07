package org.yamyamgoods.yamyam_android.review.all.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
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
import org.yamyamgoods.yamyam_android.review.all.ReviewAllItem
import org.yamyamgoods.yamyam_android.reviewdetail.ReviewBasicDTO
import org.yamyamgoods.yamyam_android.reviewdetail.ReviewDetailActivity
import java.lang.Exception

class ReviewAllRVAdapter (private val ctx: Context, private val dataList: List<ReviewAllItem>): RecyclerView.Adapter<ReviewAllRVAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAllRVAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_review_all, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        dataList[position].let{ item->
            Glide.with(ctx)
                    .load(item.userImageUrl)
                    .centerCrop()
                    .circleCrop()
                    .into(holder.ivUserImage)
            holder.tvUserNickname.text  = item.userNickname
            holder.tvReviewDate.text = item.date

            for (i in 0 until  item.starCount){
                holder.starRate[i].setImageResource(R.drawable.icon_colorstar)
            }

            holder.tvReviewContents.text = item.reviewContents
            holder.btnDetailReview.setOnClickListener{
                try {
                    var dto = ReviewBasicDTO(item.userImageUrl,
                            item.userNickname,
                            item.date,
                            item.starCount,
                            item.reviewContents,
                            item.imageUrl,
                            item.thumbFlag,
                            item.thumbCount,
                            item.commentsCount)
                    var intent = Intent(ctx, ReviewDetailActivity::class.java)
                    intent.putExtra("dto", dto)
                    ctx.startActivity(intent)
                } catch (e: Exception) {
                }
            }

            var options: RequestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(10))
            var imageNum = item.imageUrl.size
            if (imageNum >3){
                holder.etcImageNum.text = (item.imageUrl.size-3).toString()
                setVisible(holder.etcImageNum)
                holder.reviewImage[2].setColorFilter(Color.parseColor("#777777"), PorterDuff.Mode.MULTIPLY)
                holder.etcImageNum.text = "+" + (item.imageUrl.size-3).toString()
                imageNum = 3
            }
            for (i in 0 until  imageNum){
                setVisible(holder.reviewImage[i])
                Glide.with(ctx)
                        .load(item.imageUrl[i])
                        .apply(options)
                        .into(holder.reviewImage[i])
            }

            holder.btnThumb.setOnClickListener{
                item.thumbFlag = Math.abs(item.thumbFlag - 1)
                holder.ivThumb.isSelected  = !(holder.ivThumb.isSelected)
            }
            holder.tvThumbNum.text = item.thumbCount.toString()
            holder.btnComments.setImageResource(R.drawable.icon_comment)
            holder.tvCommentsNum.text = item.commentsCount.toString()
        }
    }

    inner class Holder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        var ivUserImage: ImageView = itemView.findViewById(R.id.iv_rv_item_review_all_user_img) as ImageView
        var tvUserNickname: TextView = itemView.findViewById(R.id.tv_rv_item_review_all_user_nickname) as TextView
        var tvReviewDate: TextView = itemView.findViewById(R.id.tv_rv_item_review_all_date) as TextView

        var starRate: List<ImageView> = listOf(
                itemView.findViewById(R.id.iv_rv_item_review_all_star1),
                itemView.findViewById(R.id.iv_rv_item_review_all_star2),
                itemView.findViewById(R.id.iv_rv_item_review_all_star3),
                itemView.findViewById(R.id.iv_rv_item_review_all_star4),
                itemView.findViewById(R.id.iv_rv_item_review_all_star5)
        )

        var tvReviewContents: TextView = itemView.findViewById(R.id.tv_rv_item_review_all_contents) as TextView

        var reviewImage: List<ImageView> = listOf(
                itemView.findViewById(R.id.iv_rv_item_review_all_image1),
                itemView.findViewById(R.id.iv_rv_item_review_all_image2),
                itemView.findViewById(R.id.iv_rv_item_review_all_image3)
        )
        var etcImageNum: TextView = itemView.findViewById(R.id.tv_rv_item_review_all_etc_image_num) as TextView

        var btnThumb: LinearLayout = itemView.findViewById(R.id.btn_rv_item_review_all_thumbs) as LinearLayout
        var ivThumb: ImageView = itemView.findViewById(R.id.iv_rv_item_review_all_thumbs) as ImageView
        var tvThumbNum: TextView = itemView.findViewById(R.id.tv_rv_item_review_all_thumbs_num) as TextView
        var btnDetailReview: ConstraintLayout = itemView.findViewById(R.id.btn_rv_item_review_all_detail_review) as ConstraintLayout
        var btnComments: ImageView = itemView.findViewById(R.id.btn_rv_item_review_all_comments) as ImageView
        var tvCommentsNum: TextView = itemView.findViewById(R.id.tv_rv_item_best_review_all_comments_num) as TextView
    }

    fun setVisible(view: View){
        view.visibility = View.VISIBLE
    }
}