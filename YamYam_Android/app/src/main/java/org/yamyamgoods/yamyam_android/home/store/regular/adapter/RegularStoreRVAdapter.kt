package org.yamyamgoods.yamyam_android.home.store.regular.adapter

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
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.StoreData
import org.yamyamgoods.yamyam_android.storedetail.StoreDetailActivity

class RegularStoreRVAdapter(private val ctx: Context) : RecyclerView.Adapter<RegularStoreRVAdapter.Holder>() {

    private val dataList = ArrayList<StoreData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_regular_store, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        dataList[position].let { item ->
            setCircleImage(holder.ivImage, item.store_img)

            holder.tvStoreName.text = item.store_name
            holder.tvHashTag.text = item.getOneLineHashTags()

            holder.ivLike.apply {
                imageResource = R.drawable.selector_bookmark_flag
                isSelected = item.store_scrap_flag
            }
        }
        holder.ivImage.setOnClickListener {
            try{
                ctx.startActivity<StoreDetailActivity>(
                    "s_idx" to dataList[position].store_idx,
                    "title" to dataList[position].store_name,
                    "image" to dataList[position].store_img,
                    "hashtag" to dataList[position].store_hashtags,
                    "store_url" to dataList[position].store_url,
                    "scrap_flag" to dataList[position].store_scrap_flag)
            } catch (e:Exception){
            }

        }
    }

    fun addData(newData: List<StoreData>) {
        val previousSize = itemCount
        dataList.addAll(newData)
        notifyItemRangeInserted(previousSize, itemCount)
    }

    private fun setCircleImage(view: ImageView, imageUrl: String) =
        Glide.with(ctx)
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(view)

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.iv_rv_item_regular_store_image)

        val tvStoreName: TextView = itemView.findViewById(R.id.tv_rv_item_regular_store_name)
        val tvHashTag: TextView = itemView.findViewById(R.id.tv_rv_item_regular_store_tags)

        val btnLike: ConstraintLayout = itemView.findViewById(R.id.btn_rv_item_regular_store_like)
        val ivLike: ImageView = itemView.findViewById(R.id.iv_rv_item_regular_store_like)
    }
}