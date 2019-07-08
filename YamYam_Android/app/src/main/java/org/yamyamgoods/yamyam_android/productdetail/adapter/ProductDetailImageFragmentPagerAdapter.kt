package org.yamyamgoods.yamyam_android.productdetail.adapter

import android.os.Build
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import org.yamyamgoods.yamyam_android.productdetail.fragment.ProductDetailImageFragment
import java.util.stream.Collectors

/**
 * Created By Yun Hyeok
 * on 7월 07, 2019
 */

class ProductDetailImageFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val imageFragments = mutableListOf<Fragment>()

    override fun getItem(position: Int): Fragment = imageFragments[position]

    override fun getCount(): Int = imageFragments.size

    fun addImages(newImageUrls: List<String>) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val newImageFragments = newImageUrls
                    .stream()
                    .map {
                        ProductDetailImageFragment.getInstance(it)
                    }
                    .collect(Collectors.toList())
            imageFragments.addAll(newImageFragments)
            return
        }

        for (imageUrl in newImageUrls) {
            imageFragments.add(ProductDetailImageFragment.getInstance(imageUrl))
        }

    }


}