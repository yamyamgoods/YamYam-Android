package org.yamyamgoods.yamyam_android.home.bookmark.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.imageResource
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.BookmarkData
import org.yamyamgoods.yamyam_android.home.bookmark.BookmarkContract
import org.yamyamgoods.yamyam_android.home.bookmark.dialog.BookmarkOptionDialog
import org.yamyamgoods.yamyam_android.util.dp2px
import org.yamyamgoods.yamyam_android.util.getScreenWidth
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created By Yun Hyeok
 * on 7월 06, 2019
 */

class BookmarkRVAdapter(private val ctx: Context, private val presenter: BookmarkContract.Presenter) :
    RecyclerView.Adapter<BookmarkRVAdapter.Holder>() {

    val dataList = ArrayList<BookmarkData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_bookmark, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        dataList[position].let { item ->

            Glide.with(ctx).load(item.goods_img).into(holder.ivImage)

            val imageHeight = getDynamicImageHeight()
            val imageParams = holder.clImageFrame.layoutParams
            imageParams.height = imageHeight

            holder.clImageFrame.layoutParams = imageParams

            holder.ivBookmark.apply {
                imageResource = R.drawable.selector_bookmark_heart
                isSelected = true
            }

            holder.btnBookmark.setOnClickListener {
                Log.v("Malibin Debug", " btnBookmark.setOnClickListener : position : $position")
                presenter.deleteBookmark(item.goods_scrap_idx, item)
            }

            holder.tvStoreName.text = item.store_name
            holder.tvProductName.text = item.goods_scrap_label
            holder.tvPrice.text = item.goods_price

            holder.clWholeView.setOnClickListener {
                BookmarkOptionDialog(ctx, item.goods_scrap_idx).apply {
                    totalPrice = item.goods_price.replace(",", "").toInt()
                    bookmarkData = item
                }.show()
            }
        }
    }

    fun addData(newData: List<BookmarkData>) {
        val previousSize = itemCount
        dataList.addAll(newData)
        notifyItemRangeInserted(previousSize, itemCount)
    }

    fun deleteBookmark(bookmarkData: BookmarkData) {
        dataList.remove(bookmarkData)
        notifyDataSetChanged()
    }

    private fun getDynamicImageWidth(): Int {
        val phoneWidth = getScreenWidth(ctx)
        val blankSpace = dp2px(40f, ctx)

        return (phoneWidth - blankSpace) / 2
    }

    private fun getDynamicImageHeight(): Int {
        val width = getDynamicImageWidth()
        val ratio = 150f / 160f
        return (width * ratio).toInt()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clWholeView: ConstraintLayout = itemView.findViewById(R.id.cl_rv_item_bookmark_whole)

        val clImageFrame: ConstraintLayout = itemView.findViewById(R.id.cl_rv_item_bookmark_image)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_rv_item_bookmark_picture)
        val ivBookmark: ImageView = itemView.findViewById(R.id.iv_rv_item_bookmark_bookmark)
        val btnBookmark: ConstraintLayout = itemView.findViewById(R.id.btn_rv_item_bookmark_bookmark)

        val tvStoreName: TextView = itemView.findViewById(R.id.tv_rv_item_bookmark_store_name)
        val tvProductName: TextView = itemView.findViewById(R.id.tv_rv_item_bookmark_product_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_rv_item_bookmark_product_price)
    }
}