package org.yamyamgoods.yamyam_android.mypage.recent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recently_viewed_products.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.mypage.recent.adapter.RecentlyViewedProductsRVAdapter
import org.yamyamgoods.yamyam_android.util.TempData

class RecentlyViewedProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recently_viewed_products)

        viewInit()
    }

    private fun viewInit() {
        rv_recently_viewed_products_product_list.apply {
            adapter = RecentlyViewedProductsRVAdapter(this@RecentlyViewedProductsActivity, TempData.mypageProducts())
            layoutManager = GridLayoutManager(this@RecentlyViewedProductsActivity, 3)
        }
    }
}
