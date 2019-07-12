package org.yamyamgoods.yamyam_android.home.goods.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.productdetail.ProductDetailActivity

class GoodsCategoryDetailRecyclerViewAdapter (val ctx: Context, var dataList: ArrayList<GoodsData>): RecyclerView.Adapter<GoodsCategoryDetailRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_goods_category_detail, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Glide.with(ctx)
                .load(dataList[position].goods_img)
                .into(holder.image)
        holder.store.text = dataList[position].store_name
        holder.name.text = dataList[position].goods_name
        holder.price.text = dataList[position].goods_price
        val rate: String = String.format("%.1f",dataList[position].goods_rating)
        holder.rate.text = rate
        var min: String? = dataList[position].goods_minimum_amount
        min = min!!.replace(",000","k")
        holder.minQuantity.text = min
        val reviewNum: String = String.format("%d",dataList[position].goods_review_cnt)
        holder.reviewCount.text = reviewNum
        holder.whole.setOnClickListener {
            ctx.startActivity<ProductDetailActivity>("goodsIdx" to dataList[position].goods_idx)
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var whole = itemView.findViewById(R.id.whole) as CardView
        var image = itemView.findViewById(R.id.iv_rv_item_goods_category_detail) as ImageView
        var store = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_store) as TextView
        var name = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_name) as TextView
        var price = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_price) as TextView
        var rate = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_rate) as TextView
        var minQuantity = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_minQuantity) as TextView
        var reviewCount = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_reviewCount) as TextView
    }
}