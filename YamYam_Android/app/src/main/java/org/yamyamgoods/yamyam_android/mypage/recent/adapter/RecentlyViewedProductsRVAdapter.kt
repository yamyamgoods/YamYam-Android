package org.yamyamgoods.yamyam_android.mypage.recent.adapter

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
import org.yamyamgoods.yamyam_android.mypage.MypageProductItem
import org.yamyamgoods.yamyam_android.mypage.recent.RecentlyViewedProductsActivity

class RecentlyViewedProductsRVAdapter (private val ctx: Context, val dataList: List<MypageProductItem>): RecyclerView.Adapter<RecentlyViewedProductsRVAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentlyViewedProductsRVAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_recently_viewed_product, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var options: RequestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(10))

        dataList[position].let { item ->
            Glide.with(ctx)
                    .load(item.imageUrl)
                    .apply(options)
                    .into(holder.ivImage)

            holder.ivBookmark.setOnClickListener {
                holder.ivBookmark.isSelected = !(holder.ivBookmark.isSelected)
            }

            holder.tvStoreName.text = item.storeName
            holder.tvProductName.text = item.productName
            holder.tvPrice.text = item.price.toString()
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.iv_rv_item_mypage_recently_viewed_product_image)
        val ivBookmark: ImageView = itemView.findViewById(R.id.iv_rv_item_mypage_recently_viewed_product_bookmark)

        val tvStoreName: TextView = itemView.findViewById(R.id.tv_rv_item_mypage_recently_viewed_store_name)
        val tvProductName: TextView = itemView.findViewById(R.id.tv_rv_item_mypage_recently_viewed_product_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_rv_item_mypage_recently_viewed_product_price)
    }
}