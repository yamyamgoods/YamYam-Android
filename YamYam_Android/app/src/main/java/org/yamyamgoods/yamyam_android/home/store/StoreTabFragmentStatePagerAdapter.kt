package org.yamyamgoods.yamyam_android.Home.store

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class StoreTabFragmentStatePagerAdapter(fm:FragmentManager,val fragmentCounter:Int):FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment? {
        when(p0) {
            0 -> return StoreRankingFragment()
            1 -> return CustomerStoreFragment()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCounter
}