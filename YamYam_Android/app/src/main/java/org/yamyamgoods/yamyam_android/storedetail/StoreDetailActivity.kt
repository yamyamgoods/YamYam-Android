package org.yamyamgoods.yamyam_android.storedetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_store_detail.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.storedetail.adapter.StoreDetailRVAdapter

/**
 * Created By Yun Hyeok
 * on 7월 09, 2019
 */

class StoreDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)

      //  viewInit()
    }
//
//    private fun viewInit() {
//        rv_store_detail_act_list.apply {
//            adapter = StoreDetailRVAdapter(this@StoreDetailActivity, TempData.storeGoods(), "스토어이름")
//            layoutManager = GridLayoutManager(this@StoreDetailActivity, 2)
//        }
//    }
}
