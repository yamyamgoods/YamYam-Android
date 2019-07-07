package org.yamyamgoods.yamyam_android.home.best

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_best_tab.*
import kotlinx.android.synthetic.main.fragment_best_tab.view.*
import kotlinx.android.synthetic.main.tab_best_fragment.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.best.adpater.BestTabFragmentStatePagerAdapter
import org.yamyamgoods.yamyam_android.mypage.MypageActivity

class BestTabFragment : Fragment() {
    lateinit var inflater: LayoutInflater
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.inflater = inflater
        return inflater.inflate(R.layout.fragment_best_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openMypage()
        viewInit(view)

    }

    fun openMypage(){
        btn_best_tab_frag_mypage.setOnClickListener{
            val intent= Intent(ctx, MypageActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun viewInit(view: View) {

        //Best Tab
        view.vp_best_tab_frag_vp.adapter = BestTabFragmentStatePagerAdapter(childFragmentManager, 2)
        view. vp_best_tab_frag_vp.offscreenPageLimit = 2
        view.tl_best_tab_frag_tab.setupWithViewPager(vp_best_tab_frag_vp)

        val navBestLayout : View = inflater.inflate(R.layout.tab_best_fragment, null, false)
        view.tl_best_tab_frag_tab.getTabAt(0)!!.customView = navBestLayout.findViewById(R.id.btn_best_tab_best_goods)
        view.tl_best_tab_frag_tab.getTabAt(1)!!.customView = navBestLayout.findViewById(R.id.btn_best_tab_best_review)
        view.tl_best_tab_frag_tab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectedBestTab(position = tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                selectedBestTab(position = tab!!.position)
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectedBestTab(position = tab!!.position)
            }
        })
    }

    //Best Tab Indicator Setting
    private fun selectedBestTab(position: Int) {
        if (position == 0) {
            tv_best_tab_best_goods.setTextColor(resources.getColor(R.color.colorTabDarkGray))
            tv_best_tab_best_review.setTextColor(resources.getColor(R.color.colorTabLightGray))
        } else {
            tv_best_tab_best_goods.setTextColor(resources.getColor(R.color.colorTabLightGray))
            tv_best_tab_best_review.setTextColor(resources.getColor(R.color.colorTabDarkGray))
        }
    }
}