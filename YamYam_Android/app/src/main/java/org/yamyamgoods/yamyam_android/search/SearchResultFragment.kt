package org.yamyamgoods.yamyam_android.search

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_search_result.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.search.adapter.SearchResultFragmentStatePagerAdapter

class SearchResultFragment : Fragment() {

    lateinit var tv_tab_search_act_goods: TextView
    lateinit var tv_tab_search_act_store: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_search_result, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureTopNavigation()
    }


    private fun configureTopNavigation(){
        vp_search_result_act_pager.apply {
            adapter = SearchResultFragmentStatePagerAdapter(childFragmentManager,2)
            offscreenPageLimit = 2
        }
        tl_search_result_act.setupWithViewPager(vp_search_result_act_pager)

        val tabtopLayout: View = this.layoutInflater.inflate(R.layout.tab_search_activity,null, false)

        tv_tab_search_act_goods = tabtopLayout.findViewById(R.id.tv_tab_search_act_goods)
        tv_tab_search_act_store = tabtopLayout.findViewById(R.id.tv_tab_search_act_store)

        tl_search_result_act.getTabAt(0)!!.customView = tabtopLayout.findViewById(R.id.btn_tab_search_act_goods) as RelativeLayout
        tl_search_result_act.getTabAt(1)!!.customView = tabtopLayout.findViewById(R.id.btn_tab_search_act_store) as RelativeLayout
        tl_search_result_act.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectedSearchTab(position = tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                selectedSearchTab(position = tab!!.position)
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectedSearchTab(position = tab!!.position)
            }
        })
    }

    private fun selectedSearchTab(position: Int){
        if(position ==0){
            tv_tab_search_act_goods.setTextColor(resources.getColor(R.color.colorTabDarkGray))
            tv_tab_search_act_store.setTextColor(resources.getColor(R.color.colorTabLightGray))
        } else if(position ==1){
            tv_tab_search_act_goods.setTextColor(resources.getColor(R.color.colorTabLightGray))
            tv_tab_search_act_store.setTextColor(resources.getColor(R.color.colorTabDarkGray))
        }
    }
}