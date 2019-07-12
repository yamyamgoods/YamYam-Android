package org.yamyamgoods.yamyam_android.search.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.network.get.SearchStoreData
import org.yamyamgoods.yamyam_android.storedetail.StoreDetailActivity

class SearchResultStoreRecyclerViewAdapter(val ctx: Context, var dataList: ArrayList<SearchStoreData>): RecyclerView.Adapter<SearchResultStoreRecyclerViewAdapter.Holder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_search_store, viewGroup,false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(ctx)
            .load(dataList[position].store_img)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.img)
        holder.name.text = dataList[position].store_name
        holder.tags.text = TextUtils.join(" ",dataList[position].hash_tag)
        if(dataList[position].store_scrap_flag == 0){
            holder.like.setImageDrawable(ctx.resources.getDrawable(R.drawable.icon_storelikeyellow))
        } else{
            holder.like.setImageDrawable(ctx.resources.getDrawable(R.drawable.icon_storegray))
        }
        holder.whole.setOnClickListener {
            try{
                ctx.startActivity<StoreDetailActivity>(
                    "s_idx" to dataList[position].store_idx,
                    "title" to dataList[position].store_name,
                    "image" to dataList[position].store_img,
                    "hashtag" to dataList[position].hash_tag,
                    "store_url" to dataList[position].store_url,
                    "scrap_flag" to dataList[position].store_scrap_flag)
            } catch (e:Exception){
            }
        }

    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var whole = itemView.findViewById(R.id.btn_rv_item_search_store_whole) as RelativeLayout
        var img = itemView.findViewById(R.id.iv_rv_item_store_search_image) as ImageView
        var name = itemView.findViewById(R.id.tv_rv_item_store_search_name) as TextView
        var tags = itemView.findViewById(R.id.tv_rv_item_store_search_tags) as TextView
        var btn_like = itemView.findViewById(R.id.btn_rv_item_store_search_like) as RelativeLayout
        var like = itemView.findViewById(R.id.iv_rv_item_store_search_like) as ImageView
    }
}