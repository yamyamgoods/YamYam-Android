package org.yamyamgoods.yamyam_android.home.best.goods.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.home.best.goods.BestGoodsItem
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.home.best.goods.BestGoodsFragment
import org.yamyamgoods.yamyam_android.productdetail.ProductDetailActivity
import org.yamyamgoods.yamyam_android.util.dp2px
import org.yamyamgoods.yamyam_android.util.getScreenWidth

class BestGoodsRVAdapter(private val ctx: Context, private val listener: BestGoodsFragment.BookmarkClickListener) :
    RecyclerView.Adapter<BestGoodsRVAdapter.Holder>() {

    val dataList = ArrayList<GoodsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_best_goods, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        dataList[position].let { item ->

            Glide.with(ctx).load(item.goods_img).into(holder.ivImage)

            val imageHeight = getDynamicImageHeight()

            val imageParams = holder.clImageFrame.layoutParams
            imageParams.height = imageHeight

            holder.clImageFrame.layoutParams = imageParams

            holder.ivBookmark.apply {
                val isBookMarked = (item.scrap_flag == 1)
                imageResource = R.drawable.selector_bookmark_heart
                isSelected = isBookMarked
                setOnClickListener {
                    listener.onClick(item)
                    notifyItemChanged(position)
                }
            }

            holder.tvStoreName.text = item.store_name
            holder.tvProductName.text = item.goods_name
            holder.tvPrice.text = item.goods_price

            holder.ivStarView.imageResource = R.drawable.star
            holder.tvStarRate.text = item.goods_rating.toString()
            holder.tvMinQuantity.text = item.goods_minimum_amount.toString()
            holder.tvReviewCount.text = item.goods_review_cnt.toString()

            holder.clWhole.setOnClickListener {
                ctx.startActivity<ProductDetailActivity>("goodsIdx" to item.goods_idx)
            }
        }
    }

    fun addData(newData: List<GoodsData>) {
        val previousSize = itemCount
        dataList.addAll(newData)
        notifyItemRangeInserted(previousSize, itemCount)
    }

    private fun getDynamicImageWidth(): Int {
        val phoneWidth = getScreenWidth(ctx)
        val blankSpace = dp2px(40f, ctx)

        return (phoneWidth - blankSpace) / 2
    }

    private fun getDynamicImageHeight(): Int {
        val width = getDynamicImageWidth()
        val ratio = 150f / 160f
        return (width * ratio).toInt()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clWhole: ConstraintLayout = itemView.findViewById(R.id.cl_rv_item_best_goods_whole)
        val clImageFrame: ConstraintLayout = itemView.findViewById(R.id.cl_rv_item_best_goods_image)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_rv_item_best_goods_picture)
        val ivBookmark: ImageView = itemView.findViewById(R.id.iv_rv_item_best_goods_bookmark)

        val tvStoreName: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_store_name)
        val tvProductName: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_product_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_product_price)

        val ivStarView: ImageView = itemView.findViewById(R.id.iv_rv_item_best_goods_star)
        val tvStarRate: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_rate)
        val tvMinQuantity: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_minimum_quantity)
        val tvReviewCount: TextView = itemView.findViewById(R.id.tv_rv_item_best_goods_review_num)
    }
}