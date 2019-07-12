package org.yamyamgoods.yamyam_android.home.store.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import org.yamyamgoods.yamyam_android.home.store.ranking.StoreRankingFragment
import org.yamyamgoods.yamyam_android.home.store.regular.RegularStoreFragment

class StoreTabFragmentStatePagerAdapter(fm: FragmentManager, private val fragmentCount: Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {

        when (position) {
            0 -> return StoreRankingFragment()
            1 -> return RegularStoreFragment.getInstance()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount
}