package org.yamyamgoods.yamyam_android.home

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.goods.GoodsExhibitionFragment
import org.yamyamgoods.yamyam_android.home.goods.GoodsTabFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewInit()
    }

    private fun viewInit() {

        vp_home_act_pager.apply {
            adapter = HomeTabFragmentStatePagerAdapter(supportFragmentManager, 4)
            offscreenPageLimit = 4
        }

        tl_home_act_bottom_bar.setupWithViewPager(vp_home_act_pager)

        val tabLayout: View = this.layoutInflater.inflate(R.layout.tab_home_activity, null, false)
        tl_home_act_bottom_bar.getTabAt(0)!!.customView = tabLayout.findViewById(R.id.btn_tab_home_act_best)
        tl_home_act_bottom_bar.getTabAt(1)!!.customView = tabLayout.findViewById(R.id.btn_tab_home_act_store)
        tl_home_act_bottom_bar.getTabAt(2)!!.customView = tabLayout.findViewById(R.id.btn_tab_home_act_goods)
        tl_home_act_bottom_bar.getTabAt(3)!!.customView = tabLayout.findViewById(R.id.btn_tab_home_act_bookmark)

    }

    fun gotoBookmarkTab() {
        vp_home_act_pager.currentItem = 3
    }
//    액티비티에서는 Back버튼을 두번 눌렀을 때, 종료되도록
    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if(count == 0) {
            Log.e("homeActivity", "count0")
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
            Log.e("homeActivity", "pop")
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fl_goods_fragment_frag, GoodsExhibitionFragment())
            transaction.addToBackStack(null)

            transaction.commit()

        }
    }
}
