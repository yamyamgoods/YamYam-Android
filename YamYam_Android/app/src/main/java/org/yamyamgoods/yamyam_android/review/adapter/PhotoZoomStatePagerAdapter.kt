package org.yamyamgoods.yamyam_android.review.adapter

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import org.yamyamgoods.yamyam_android.R

class PhotoZoomStatePagerAdapter (private val list:ArrayList<String>) : PagerAdapter() {
    private val MAX_VALUE=200

    override fun isViewFromObject(v: View, p1: Any): Boolean {
        return v===p1 as View
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view :View = LayoutInflater.from(container?.context).inflate(R.layout.vp_item_photo_zoom_in,container,false)
        val img_url: ImageView = view.findViewById(R.id.pv_rv_item_photo_zoom_in) as ImageView

        Glide.with(view)
                .load(list.get(position%list.size))
                .into(img_url)
        container?.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }
}