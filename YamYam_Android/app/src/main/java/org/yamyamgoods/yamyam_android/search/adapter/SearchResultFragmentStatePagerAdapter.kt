package org.yamyamgoods.yamyam_android.search.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import org.yamyamgoods.yamyam_android.search.goods.SearchResultGoodsFragment
import org.yamyamgoods.yamyam_android.search.store.SearchResultStoreFragment

class SearchResultFragmentStatePagerAdapter (fm: FragmentManager,val fragmentCount: Int): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return SearchResultGoodsFragment()
            1 -> return SearchResultStoreFragment()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount
}