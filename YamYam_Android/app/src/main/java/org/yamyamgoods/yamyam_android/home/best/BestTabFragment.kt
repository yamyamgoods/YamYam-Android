package org.yamyamgoods.yamyam_android.home.best

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_best_tab.*
import org.jetbrains.anko.support.v4.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.best.adpater.BestTabFragmentStatePagerAdapter
import org.yamyamgoods.yamyam_android.mypage.MypageActivity
import org.yamyamgoods.yamyam_android.search.SearchActivity

class BestTabFragment : Fragment() {

    companion object {
        private var instance: BestTabFragment? = null

        @JvmStatic
        fun getInstance(): BestTabFragment = instance
            ?: BestTabFragment().apply { instance = this }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_best_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
        openMypage()
        viewInit()
    }

    private fun setOnClickListener() {
        this.btn_best_tab_frag_search.setOnClickListener {
            startActivity<SearchActivity>()
        }
    }

    fun openMypage() {
        btn_best_tab_frag_mypage.setOnClickListener {
            val intent = Intent(activity!!, MypageActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun viewInit() {
        //Best Tab
        vp_best_tab_frag_vp.apply {
            adapter = BestTabFragmentStatePagerAdapter(childFragmentManager, 2)
            offscreenPageLimit = 2
        }
        tl_best_tab_frag_tab.setupWithViewPager(vp_best_tab_frag_vp)

        val tabLayout: View = this.layoutInflater.inflate(R.layout.tab_best_fragment, null, false)

        tl_best_tab_frag_tab.getTabAt(0)!!.customView = tabLayout.findViewById(R.id.btn_best_tab_best_goods)
        tl_best_tab_frag_tab.getTabAt(1)!!.customView = tabLayout.findViewById(R.id.btn_best_tab_best_review)

    }
}