package org.yamyamgoods.yamyam_android.home.goods.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.goods.GoodsExhibitionDetailActivity
import org.yamyamgoods.yamyam_android.network.get.ExhibitionData

class GoodsExhibitionRecyclerViewAdapter(val dataList: ArrayList<ExhibitionData>):RecyclerView.Adapter<GoodsExhibitionRecyclerViewAdapter.Holder>() {
    lateinit var ctx: Context
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        ctx = p0.context
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_goods_exhibition,p0,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var options: RequestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(10))
//        //통신할때
        Glide.with(ctx)
                .load(dataList[position].exhibition_img)
                .apply(options)
                .into(holder.img_thumnail)

        var title: String= dataList[position].exhibition_name
        val seperate_title= title.split("/")

        holder.title1.text = seperate_title[0]
        holder.title2.text = seperate_title[1]
        holder.title3.text = seperate_title[2]


        holder.btn.setOnClickListener {
            try{
                ctx.startActivity<GoodsExhibitionDetailActivity>(
                        "e_idx" to dataList[position].exhibition_idx,
                        "title" to dataList[position].exhibition_name,
                        "subtitle" to dataList[position].exhibition_sub_name,
                        "gradation_img" to dataList[position].exhibition_gradation_img)
            } catch (e:Exception){
            }
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var img_thumnail = itemView.findViewById(R.id.iv_rv_item_goods_exhibition_thumnail) as ImageView
        var title1 = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_title1) as TextView
        var title2 = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_title2) as TextView
        var title3 = itemView.findViewById(R.id.tv_rv_item_goods_exhibition_title3) as TextView
        var btn = itemView.findViewById(R.id.rl_rv_item_goods_exhibition) as RelativeLayout
    }
}