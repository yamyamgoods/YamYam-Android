package org.yamyamgoods.yamyam_android.reviewwrite.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.reviewwrite.ReviewWriteActivity
import org.yamyamgoods.yamyam_android.reviewwrite.ReviewWriteUploadImagesItem
import java.sql.SQLException

class ReviewWriteUploadImagesRVAdapter(private val ctx: Context, private val dataList: List<ReviewWriteUploadImagesItem>): RecyclerView.Adapter<ReviewWriteUploadImagesRVAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewWriteUploadImagesRVAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_review_write_images, parent, false)

        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var options: RequestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(10))
        dataList[position].let{ item->
            Glide.with(ctx)
                    .load(item.imageUrl)
                    .apply(options)
                    .into(holder.ivUploadedImage)

            holder.btnClose.setOnClickListener{
                try{
                    (ctx as ReviewWriteActivity).deleteClipDataItem(item.idx)
                    notifyItemRemoved(position)
                }
                catch(e: SQLException){
                    e.printStackTrace()
                }
            }
        }
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        var ivUploadedImage = itemView.findViewById(R.id.iv_review_write_image) as ImageView
        var btnClose = itemView.findViewById(R.id.btn_review_write_close) as ConstraintLayout
    }
}