package org.yamyamgoods.yamyam_android.home.store.ranking.adapter

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
import org.yamyamgoods.yamyam_android.home.store.ranking.StoreRankingContract
import org.yamyamgoods.yamyam_android.home.store.ranking.StoreRankingPresenter
import org.yamyamgoods.yamyam_android.network.post.PostRegularStoreMarkRequestDTO
import org.yamyamgoods.yamyam_android.storedetail.StoreDetailActivity

class StoreRankingRVAdapter(private val ctx: Context, private val presenter: StoreRankingContract.Presenter) :
    RecyclerView.Adapter<StoreRankingRVAdapter.Holder>() {

    val dataList = ArrayList<StoreData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_store_ranking, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        dataList[position].let { item ->
            holder.tvIdx.text = (position + 1).toString()
            setCircleImage(holder.ivImage, item.store_img)

            holder.tvStoreName.text = item.store_name
            holder.tvHashTag.text = item.getOneLineHashTags()

            holder.ivLike.apply {
                imageResource = R.drawable.selector_bookmark_flag
                isSelected = item.store_scrap_flag
            }

            holder.btnLike.setOnClickListener {
                if (item.store_scrap_flag) {
                    presenter.regularStoreCancelRequest(position, item.store_idx)
                    return@setOnClickListener
                }
                val reqBody = PostRegularStoreMarkRequestDTO(item.store_idx)
                presenter.regularStoreMarkRequest(position, reqBody)
            }
        }
        holder.whole.setOnClickListener {
            ctx.startActivity<StoreDetailActivity>(
                "s_idx" to dataList[position].store_idx,
                "title" to dataList[position].store_name,
                "image" to dataList[position].store_img,
                "hashtag" to dataList[position].store_hashtags,
                "store_url" to dataList[position].store_url,
                "isBookmarked" to dataList[position].store_scrap_flag
            )
        }
    }

    fun refreshAllDataWith(data: List<StoreData>) {
        val currentSize = itemCount
        dataList.clear()
        notifyItemRangeRemoved(0, currentSize)
        dataList.addAll(data)
        notifyItemRangeInserted(0, itemCount)
    }

    fun setRegularStoreSelected(position: Int, isSelected: Boolean) {
        dataList[position].store_scrap_flag = isSelected
        notifyItemChanged(position)
    }

    private fun setCircleImage(view: ImageView, imageUrl: String) =
        Glide.with(ctx)
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(view)

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val whole: ConstraintLayout = itemView.findViewById(R.id.whole)
        val tvIdx: TextView = itemView.findViewById(R.id.tv_rv_item_store_ranking_idx)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_rv_item_store_ranking_image)

        val tvStoreName: TextView = itemView.findViewById(R.id.tv_rv_item_store_ranking_name)
        val tvHashTag: TextView = itemView.findViewById(R.id.tv_rv_item_store_ranking_tags)

        val btnLike: ConstraintLayout = itemView.findViewById(R.id.btn_rv_item_store_ranking_like)
        val ivLike: ImageView = itemView.findViewById(R.id.iv_rv_item_store_ranking_like)
    }
}