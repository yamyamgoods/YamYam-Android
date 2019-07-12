package org.yamyamgoods.yamyam_android.storedetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_store_detail.*
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.storeweb.StoreWebActivity

class StoreDetailActivity : AppCompatActivity() {

    var s_idx: Int = -1
    lateinit var title: String
    lateinit var image: String
    lateinit var hashtag: List<String>
    var store_url: String? = null
    var scrap_flag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)
        val transaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_act_store_detail, StoreDetailGoodsFragment()).commit()
        s_idx = intent.getIntExtra("s_idx",-1)
        StoreDetailGoodsFragment.instance.s_idx = s_idx
        title = intent.getStringExtra("title")
        image = intent.getStringExtra("image")
        hashtag = intent.getStringArrayListExtra("hashtag")
        store_url = intent.getStringExtra("store_url")
        scrap_flag = intent.getBooleanExtra("store_scrap_flag",false)
        setView()
        setOnClickListener()
    }

    private fun setOnClickListener(){
        btn_store_detail_act_web_link.setOnClickListener {
            this.startActivity<StoreWebActivity>(
                "storeUrl" to store_url, "storeName" to title)
        }
    }

    private fun setView() {

        Glide.with(this)
            .load(image)
            .apply(RequestOptions.circleCropTransform())
            .into(iv_store_detail_act_image)
        tv_store_detail_act_store_name.text = title
        tv_act_store_detail_hashtag1.text = hashtag[0]
        if(hashtag.size>1) {
            tv_act_store_detail_hashtag2.text = hashtag[1]
        } else{
            rl_act_store_detail_hashtag2.visibility  = View.GONE
        }
    }
}
