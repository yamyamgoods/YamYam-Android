package org.yamyamgoods.yamyam_android.home.Goods.Adapter

import android.content.Context
import android.graphics.drawable.shapes.RoundRectShape
import android.support.design.shape.CornerTreatment
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.Goods.Data.GoodsExhibitionData
import org.yamyamgoods.yamyam_android.home.Goods.Data.GoodsExhibitionDetailData
import org.yamyamgoods.yamyam_android.home.Goods.GoodsExhibitionDetailActivity

class GoodsExhibitionRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<GoodsExhibitionData>):RecyclerView.Adapter<GoodsExhibitionRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_goods_exhibition,p0,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var options: RequestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(10))
        //기획전
        Glide.with(ctx)
                .load(dataList[position].img_thumnail)
                .apply(options)
                .into(holder.img_thumnail)
        holder.title.text = dataList[position].title

        //기획전 아이템1
        Glide.with(ctx)
                .load(dataList[position].detailList[0].p_img)
                .apply(options)
                .into(holder.ex1_image)
        holder.ex1_store.text = dataList[position].detailList[0].p_store
        holder.ex1_name.text = dataList[position].detailList[0].p_name
        holder.ex1_price.text = dataList[position].detailList[0].p_price
        val rate1:String = String.format("%.1f",dataList[position].detailList[0].p_rate)
        holder.ex1_rate.text = rate1
        val minQuantity1: String = String.format("%d",dataList[position].detailList[0].p_minQuantity)
        holder.ex1_minQuantity.text = minQuantity1

        //기획전 아이템2
        Glide.with(ctx)
                .load(dataList[position].detailList[1].p_img)
                .apply(options)
                .into(holder.ex2_image)
        holder.ex2_store.text = dataList[position].detailList[1].p_store
        holder.ex2_name.text = dataList[position].detailList[1].p_name
        holder.ex2_price.text = dataList[position].detailList[1].p_price
        val rate2:String = String.format("%.1f",dataList[position].detailList[1].p_rate)
        holder.ex2_rate.text = rate2
        val minQuantity2: String = String.format("%d",dataList[position].detailList[1].p_minQuantity)
        holder.ex2_minQuantity.text = minQuantity2

        //기획전 아이템3
        Glide.with(ctx)
                .load(dataList[position].detailList[2].p_img)
                .apply(options)
                .into(holder.ex3_image)
        holder.ex3_store.text = dataList[position].detailList[2].p_store
        holder.ex3_name.text = dataList[position].detailList[2].p_name
        holder.ex3_price.text = dataList[position].detailList[2].p_price
        val rate3:String = String.format("%.1f",dataList[position].detailList[2].p_rate)
        holder.ex3_rate.text = rate3
        val minQuantity3: String = String.format("%d",dataList[position].detailList[2].p_minQuantity)
        holder.ex3_minQuantity.text = minQuantity3

        holder.btn_all.setOnClickListener {
            try{
                ctx.startActivity<GoodsExhibitionDetailActivity>("e_idx" to dataList[position].e_idx)

            } catch (e: Exception){
            }
        }
        val sum:String = String.format("%,d",dataList[position].countSum)
        holder.count_sum.text = sum
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var img_thumnail = itemView.findViewById(R.id.iv_rv_item_goods_exhibition_thumnail) as ImageView
        var title = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_title) as TextView

        var ex1_image = itemView.findViewById(R.id.iv_rv_item_goods_exhibition_example1_image) as ImageView
        var ex1_store = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example1_store) as TextView
        var ex1_name = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example1_name) as TextView
        var ex1_price = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example1_price) as TextView
        var ex1_rate = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example1_rate) as TextView
        var ex1_minQuantity = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example1_minQuantity) as TextView

        var ex2_image = itemView.findViewById(R.id.iv_rv_item_goods_exhibition_example2_image) as ImageView
        var ex2_store = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example2_store) as TextView
        var ex2_name = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example2_name) as TextView
        var ex2_price = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example2_price) as TextView
        var ex2_rate = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example2_rate) as TextView
        var ex2_minQuantity = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example2_minQuantity) as TextView

        var ex3_image = itemView.findViewById(R.id.iv_rv_item_goods_exhibition_example3_image) as ImageView
        var ex3_store = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example3_store) as TextView
        var ex3_name = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example3_name) as TextView
        var ex3_price = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example3_price) as TextView
        var ex3_rate = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example3_rate) as TextView
        var ex3_minQuantity = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_example3_minQuantity) as TextView

        var btn_all = itemView.findViewById(R.id.btn_rv_item_goods_exhibition_all) as RelativeLayout
        var count_sum = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_countSum) as TextView
    }
}