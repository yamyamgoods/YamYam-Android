package org.yamyamgoods.yamyam_android.Home.store

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_store_main.*
import org.yamyamgoods.yamyam_android.R

class StoreMainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_store_main,container,false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureTabNavi()

    }

    fun configureTabNavi(){

        vp_store_info.adapter = StoreTabFragmentStatePagerAdapter(support,3)
        tl_store_tab.setupWithViewPager(vp_store_info)
        val storeNaviBar:View = this.layoutInflater.inflate(R.layout.fragment_store_tab,null,false)

        tl_store_tab.apply {
            getTabAt(0)!!.customView = storeNaviBar.findViewById(R.id.btn_store_ranking)
            getTabAt(1)!!.customView = storeNaviBar.findViewById(R.id.btn_customer_store)
        }
    }
}