package org.yamyamgoods.yamyam_android.Home.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MainFragmentPagerAdapter(fm:FragmentManager, val fragmentCount : Int):FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when(position){
            //TODO 프래그먼트 넣어주세용
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount
}