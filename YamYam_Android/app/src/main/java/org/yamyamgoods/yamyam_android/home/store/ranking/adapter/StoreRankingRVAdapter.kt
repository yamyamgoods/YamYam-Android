package org.yamyamgoods.yamyam_android.home.store.ranking.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.imageResource
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.store.ranking.StoreRankingItem

class StoreRankingRVAdapter(private val ctx: Context, private val dataList: List<StoreRankingItem>)
    : RecyclerView.Adapter<StoreRankingRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_store_ranking, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        dataList[position].let { item ->
            holder.tvIdx.text = item.idx.toString()
            setCircleImage(holder.ivImage, item.imageUrl)

            holder.tvStoreName.text = item.storeName
            holder.tvHashTag.text = item.getOneLineHashTags()

            holder.ivStar.imageResource = R.drawable.star
            holder.tvStarRate.text = item.starRate.toString()
            holder.tvReviewCount.text = item.reviewCount.toString()

            holder.ivLike.apply{
                imageResource = R.drawable.selector_store_list_like
                isSelected = item.isLiked
            }
        }
    }

    private fun setCircleImage(view: ImageView, imageUrl: Int) =
            Glide.with(ctx)
                    .load(imageUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(view)

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIdx: TextView = itemView.findViewById(R.id.tv_rv_item_store_ranking_idx)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_rv_item_store_ranking_image)

        val tvStoreName: TextView = itemView.findViewById(R.id.tv_rv_item_store_ranking_name)
        val tvHashTag: TextView = itemView.findViewById(R.id.tv_rv_item_store_ranking_tags)

        val ivStar: ImageView = itemView.findViewById(R.id.iv_rv_item_store_ranking_star)
        val tvStarRate: TextView = itemView.findViewById(R.id.tv_rv_item_store_ranking_star_rate)
        val tvReviewCount: TextView = itemView.findViewById(R.id.tv_rv_item_store_ranking_review_count)

        val btnLike: ConstraintLayout = itemView.findViewById(R.id.btn_rv_item_store_ranking_like)
        val ivLike: ImageView = itemView.findViewById(R.id.iv_rv_item_store_ranking_like)
    }
}