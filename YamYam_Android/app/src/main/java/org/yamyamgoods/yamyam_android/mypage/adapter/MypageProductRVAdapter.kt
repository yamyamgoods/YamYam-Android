package org.yamyamgoods.yamyam_android.mypage.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
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
import org.yamyamgoods.yamyam_android.network.get.RecentlyViewedProducts

class MypageProductRVAdapter(private val ctx: Context, var dataList: ArrayList<RecentlyViewedProducts>)
    : RecyclerView.Adapter<MypageProductRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_recently_viewed_product, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var options: RequestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(10))

        dataList[position].let { item ->
            Glide.with(ctx)
                    .load(item.goods_img)
                    .apply(options)
                    .into(holder.ivImage)

            if (item.scrap_flag == 0)
                holder.ivBookmark.isSelected = false
            if (item.scrap_flag == 1)
                holder.ivBookmark.isSelected = true

            holder.ivBookmark.setOnClickListener {
                holder.ivBookmark.isSelected = !(holder.ivBookmark.isSelected)
            }

            holder.tvStoreName.text = item.store_name
            holder.tvProductName.text = item.goods_name
            holder.tvPrice.text = item.goods_price
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