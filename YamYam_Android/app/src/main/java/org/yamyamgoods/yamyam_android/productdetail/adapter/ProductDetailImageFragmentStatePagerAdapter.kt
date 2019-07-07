package org.yamyamgoods.yamyam_android.productdetail.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created By Yun Hyeok
 * on 7월 07, 2019
 */

class ProductDetailImageFragmentStatePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    val imageUrls = mutableListOf<String>()

    override fun getItem(position: Int): Fragment? = null

    override fun getCount(): Int = imageUrls.size

    fun addImages(newImageUrls: List<String>) = imageUrls.addAll(newImageUrls)


}