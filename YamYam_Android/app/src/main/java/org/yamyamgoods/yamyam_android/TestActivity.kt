package org.yamyamgoods.yamyam_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_store.*
import org.yamyamgoods.yamyam_android.home.store.adapter.StoreTabFragmentStatePagerAdapter

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_store)

        viewInit()
    }

    //storeTab
    fun viewInit(){

        vp_store_fragment_vp.apply{
            adapter = StoreTabFragmentStatePagerAdapter(supportFragmentManager, 2)
            offscreenPageLimit = 2
        }

        val tabLayout: View = this.layoutInflater.inflate(R.layout.tab_store_fragment, null, false)

        tl_store_fragment_tab.apply{
            setupWithViewPager(vp_store_fragment_vp)
            getTabAt(0)!!.customView = tabLayout.findViewById(R.id.btn_store_tab_store_ranking)
            getTabAt(1)!!.customView = tabLayout.findViewById(R.id.btn_store_tab_regular_store)
        }
    }
}
