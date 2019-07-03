package org.yamyamgoods.yamyam_android.review.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import org.yamyamgoods.yamyam_android.review.all.ReviewAllFragment
import org.yamyamgoods.yamyam_android.review.photo.ReviewPhotoFragment

class ReviewStatePagerAdapter (fm: FragmentManager, private val fragmentCount: Int)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position){
            0 -> return ReviewAllFragment()
            1 -> return ReviewPhotoFragment()
            else -> return null
        }
    }
    override fun getCount(): Int = fragmentCount
}