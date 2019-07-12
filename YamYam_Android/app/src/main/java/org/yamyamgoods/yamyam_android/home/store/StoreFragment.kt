package org.yamyamgoods.yamyam_android.home.store

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_store.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.HomeActivity
import org.yamyamgoods.yamyam_android.home.store.adapter.StoreTabFragmentStatePagerAdapter
import org.yamyamgoods.yamyam_android.search.SearchActivity

class StoreFragment : Fragment() {

    companion object {
        private var instance: StoreFragment? = null

        fun getInstance(): StoreFragment = instance
            ?: StoreFragment().apply { instance = this }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_store, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_store_fragment_search.setOnClickListener {
            (context as HomeActivity).startActivity<SearchActivity>()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewInit()
    }

    fun viewInit() {

        vp_store_fragment_vp.apply {
            adapter = StoreTabFragmentStatePagerAdapter(childFragmentManager, 2)
            offscreenPageLimit = 2
        }

        val tabLayout: View = this.layoutInflater.inflate(R.layout.tab_store_fragment, null, false)

        tl_store_fragment_tab.apply {
            setupWithViewPager(vp_store_fragment_vp)
            getTabAt(0)!!.customView = tabLayout.findViewById(R.id.btn_store_tab_store_ranking)
            getTabAt(1)!!.customView = tabLayout.findViewById(R.id.btn_store_tab_regular_store)
        }
    }

}