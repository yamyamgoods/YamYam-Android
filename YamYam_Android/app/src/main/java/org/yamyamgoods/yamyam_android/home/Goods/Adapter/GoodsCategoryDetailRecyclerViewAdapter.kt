package org.yamyamgoods.yamyam_android.home.Goods.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.Goods.Data.GoodsCategoryDetailData

class GoodsCategoryDetailRecyclerViewAdapter (val ctx: Context, val dataList: ArrayList<GoodsCategoryDetailData>): RecyclerView.Adapter<GoodsCategoryDetailRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GoodsCategoryDetailRecyclerViewAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_goods_category, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Glide.with(ctx)
                .load(dataList[position].p_img)
                .into(holder.image)
        holder.store.text = dataList[position].p_store
        holder.name.text = dataList[position].p_name
        holder.price.text = dataList[position].p_price
        val rate: String = String.format("%.1f",dataList[position].p_rate)
        holder.rate.text = rate
        val minQuantity: String = String.format("%d",dataList[position].p_minQuantity)
        holder.minQuantity.text = minQuantity
        val reviewNum: String = String.format("%d",dataList[position].p_reviewNum)
        holder.reviewCount.text = reviewNum
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById(R.id.iv_rv_item_goods_category_detail) as ImageView
        var store = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_store) as TextView
        var name = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_name) as TextView
        var price = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_price) as TextView
        var rate = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_rate) as TextView
        var minQuantity = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_minQuantity) as TextView
        var reviewCount = itemView.findViewById(R.id.tv_rv_item_goods_category_detail_reviewCount) as TextView
    }
}