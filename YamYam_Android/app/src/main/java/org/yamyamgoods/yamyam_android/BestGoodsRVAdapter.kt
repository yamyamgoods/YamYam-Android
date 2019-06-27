package org.yamyamgoods.yamyam_android

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.imageResource
import org.yamyamgoods.yamyam_android.util.dp2px

class BestGoodsRVAdapter(private val ctx: Context, private val dataList: List<BestGoodsItem>) : RecyclerView.Adapter<BestGoodsRVAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_best_goods, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        dataList[position].let { item ->

            //임시코드 string 으로 바꿀 것 다시
            holder.ivImage.imageResource = item.imageUrl
            //임시코드

            val imageHeight = getDynamicImageHeight()

            val imageParams = holder.clImageFrame.layoutParams
            imageParams.height = imageHeight

            holder.clImageFrame.layoutParams = imageParams

            holder.ivBookmark.apply {
                imageResource = R.drawable.selector_bookmark_heart
                isSelected = item.isBookMarked
            }

            holder.tvStoreName.text = item.storeName
            holder.tvProductName.text = item.productName
            holder.tvPrice.text = item.price.toString()

            holder.tvStarRate.text = item.starRate.toString()
            holder.tvMinQuantity.text = item.minQuantity.toString()
            holder.tvReviewCount.text = item.reviewCount.toString()
        }
    }

    private fun getDynamicImageWidth(): Int {
        val displayMetrics = ctx.resources.displayMetrics
        val phoneWidth = displayMetrics.widthPixels

        val blankSpace = dp2px(40f, ctx)

        return (phoneWidth - blankSpace) / 2
    }

    private fun getDynamicImageHeight(): Int {
        val width = getDynamicImageWidth()
        val ratio = 150f / 160f
        return (width * ratio).toInt()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clImageFrame: ConstraintLayout = itemView.findViewById(R.id.cl_rv_item_best_goods_image)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_rv_item_best_goods_picture)
        val ivBookmark: ImageView = itemView.findViewById(R.id.iv_rv_item_best_goods_bookmark)

        val tvStoreName: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_store_name)
        val tvProductName: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_product_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_product_price)

        val tvStarRate: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_rate)
        val tvMinQuantity: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_minimum_quantity)
        val tvReviewCount: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_review_num)
    }
}