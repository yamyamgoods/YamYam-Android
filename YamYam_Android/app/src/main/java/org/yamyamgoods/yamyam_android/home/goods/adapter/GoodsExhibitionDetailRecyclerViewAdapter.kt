package org.yamyamgoods.yamyam_android.home.goods.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.home.goods.data.GoodsExhibitionDetailData


class GoodsExhibitionDetailRecyclerViewAdapter(val ctx: Context, var dataList: ArrayList<GoodsData>): RecyclerView.Adapter<GoodsExhibitionDetailRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_goods_exhibtion_detail, viewGroup,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var options: RequestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(10))
        Glide.with(ctx)
                .load(dataList[position].goods_img)
                .apply(options)
                .into(holder.img)

        holder.store.text = dataList[position].store_name
        holder.name.text = dataList[position].goods_name
        holder.price.text = dataList[position].goods_price
        val rate: String = String.format("%.1f",dataList[position].goods_rating)
        holder.rate.text = rate
        val minQuantity: String = String.format("%d",dataList[position].goods_minimum_amount)
        holder.min.text = minQuantity
        val reviewNum: String = String.format("%d",dataList[position].goods_review_cnt)
        holder.reviewNum.text = reviewNum
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var img = itemView.findViewById(R.id.iv_rv_item_goods_exhibition_detail_thumnail) as ImageView
        var store = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_detail_store) as TextView
        var name = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_detail_name) as TextView
        var price = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_detail_price) as TextView
        var rate = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_detail_rate) as TextView
        var min = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_detail_minQuantity) as TextView
        var reviewNum = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_detail_reviewCount) as TextView
    }
}