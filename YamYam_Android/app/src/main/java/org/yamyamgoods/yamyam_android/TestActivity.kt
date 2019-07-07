package org.yamyamgoods.yamyam_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.productdetail.ProductDetailActivity

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        startActivity<ProductDetailActivity>()
    }
}
