package org.yamyamgoods.yamyam_android.home.best.adpater

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import org.yamyamgoods.yamyam_android.home.best.goods.BestGoodsFragment
import org.yamyamgoods.yamyam_android.home.best.review.BestReviewFragment

class BestTabFragmentStatePagerAdapter(fm: FragmentManager, private val fragmentCount: Int)
    : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return BestGoodsFragment()
            1 -> return BestReviewFragment()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount

}