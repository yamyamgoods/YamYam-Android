package org.yamyamgoods.yamyam_android.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import org.yamyamgoods.yamyam_android.home.best.BestTabFragment
import org.yamyamgoods.yamyam_android.home.bookmark.BookmarkFragment
import org.yamyamgoods.yamyam_android.home.goods.GoodsTabFragment
import org.yamyamgoods.yamyam_android.home.store.StoreFragment

/**
 * Created By Yun Hyeok
 * on 7월 11, 2019
 */
class HomeTabFragmentStatePagerAdapter(fm: FragmentManager, private val fragmentCount: Int) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return BestTabFragment.getInstance()
            1 -> return StoreFragment.getInstance()
            2 -> return GoodsTabFragment()
            3 -> return BookmarkFragment.getInstance()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount

}