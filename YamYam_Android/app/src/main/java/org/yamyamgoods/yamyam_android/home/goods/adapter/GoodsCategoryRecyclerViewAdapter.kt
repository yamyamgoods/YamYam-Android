package org.yamyamgoods.yamyam_android.home.goods.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.goods.GoodsCategoryDetailFragment
import org.yamyamgoods.yamyam_android.home.HomeActivity
import org.yamyamgoods.yamyam_android.network.get.GoodsCategoryData

class GoodsCategoryRecyclerViewAdapter (val ctx: Context, val dataList: ArrayList<GoodsCategoryData>): RecyclerView.Adapter<GoodsCategoryRecyclerViewAdapter.Holder>(){
    var selected_position:Int = -1
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_goods_category, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.category_name.text = dataList[position].goods_category_name

        if(position==selected_position){
            holder.category_name.setTextColor(ctx.resources.getColor(R.color.colorWhite))
            holder.category_name.typeface = ctx.resources.getFont(R.font.notosansbold)
            holder.category_color.setImageDrawable(ctx.resources.getDrawable(R.drawable.buttons_circley))
        }
        else{
            holder.category_name.setTextColor(ctx.resources.getColor(R.color.category_gray))
            holder.category_name.typeface = ctx.resources.getFont(R.font.notosansmedium)
            holder.category_color.setImageDrawable(ctx.resources.getDrawable(R.drawable.buttons_circleline))
        }

        holder.category.setOnClickListener {
            try {
                //선택된 카테고리의 position을 넘겨주고, 변하도록
                selected_position = position
                notifyItemRangeChanged(0,itemCount)

                //vp를 CategoryDetail로 변환
                val transaction: FragmentTransaction = (ctx as HomeActivity).supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fl_goods_fragment_frag, GoodsCategoryDetailFragment())
                //transaction.addToBackStack(null)
                transaction.commit()

                GoodsCategoryDetailFragment.instance.categoryIdx = dataList[position].goods_category_idx
            } catch (e: Exception){
            }
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var category = itemView.findViewById(R.id.btn_rv_item_goods_category) as RelativeLayout
        var category_name = itemView.findViewById(R.id.tv_rv_item_goods_category) as TextView
        var category_color = itemView.findViewById(R.id.iv_rv_item_goods_category) as ImageView
    }
}