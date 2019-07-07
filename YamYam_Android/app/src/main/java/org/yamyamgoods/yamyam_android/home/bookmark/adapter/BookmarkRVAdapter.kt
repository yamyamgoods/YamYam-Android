package org.yamyamgoods.yamyam_android.home.bookmark.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.imageResource
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.HomeActivity
import org.yamyamgoods.yamyam_android.home.bookmark.BookmarkItem
import org.yamyamgoods.yamyam_android.home.bookmark.dialog.BookmarkEstimateDialog
import org.yamyamgoods.yamyam_android.util.dp2px
import org.yamyamgoods.yamyam_android.util.getScreenWidth

/**
 * Created By Yun Hyeok
 * on 7월 06, 2019
 */

class BookmarkRVAdapter(private val ctx: Context, private val dataList: List<BookmarkItem>)
    : RecyclerView.Adapter<BookmarkRVAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_bookmark, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        dataList[position].let { item ->

            //임시코드 string 으로 바꿀 것 다시
            holder.ivImage.imageResource = item.imageUrl
            //임시코드

            val imageHeight = getDynamicImageHeight()

            val imageParams = holder.clImageFrame.layoutParams
            imageParams.height = imageHeight

            holder.clImageFrame.layoutParams = imageParams

            holder.ivBookmark.apply {
                imageResource = R.drawable.selector_bookmark_heart
                isSelected = item.isBookMarked
            }

            holder.tvStoreName.text = item.storeName
            holder.tvProductName.text = item.productName
            holder.tvPrice.text = item.price.toString()

            holder.clWholeView.setOnClickListener {
                val bookmarkEstimateDialog = BookmarkEstimateDialog(ctx)
                bookmarkEstimateDialog.show()
                bookmarkEstimateDialog.window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }
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

        val tvStoreName: TextView = itemView.findViewById(R.id.tv_rv_item_bookmark_store_name)
        val tvProductName: TextView = itemView.findViewById(R.id.tv_rv_item_bookmark_product_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_rv_item_bookmark_product_price)
    }
}