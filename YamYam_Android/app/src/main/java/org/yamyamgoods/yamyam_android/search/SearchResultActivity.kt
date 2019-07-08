package org.yamyamgoods.yamyam_android.search

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_search_result.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.search.adapter.SearchResultFragmentStatePagerAdapter

class SearchResultActivity : AppCompatActivity() {

    lateinit var tv_tab_search_act_goods: TextView
    lateinit var tv_tab_search_act_store: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        setStatusBarTransparent()
        configureTopNavigation()
    }

    private fun setStatusBarTransparent() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        if (Build.VERSION.SDK_INT >= 21) {
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
            return
        }
        winParams.flags = winParams.flags and bits.inv()
        win.attributes = winParams
    }

    private fun configureTopNavigation(){
        vp_search_result_act_pager.adapter = SearchResultFragmentStatePagerAdapter(supportFragmentManager,2)
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