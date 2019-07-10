package org.yamyamgoods.yamyam_android.home.goods

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_goods_exhibition_detail.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.home.goods.adapter.GoodsExhibitionDetailRecyclerViewAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.GetExhibitionDetailResponse
import org.yamyamgoods.yamyam_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoodsExhibitionDetailActivity : AppCompatActivity() {

    var e_idx: Int = -1
    var isRequested = false
    lateinit var title: String
    lateinit var subtitle: String
    lateinit var background_img: String
    lateinit var goodsExhibitionDetailRecyclerViewAdapter: GoodsExhibitionDetailRecyclerViewAdapter
    var dataList: ArrayList<GoodsData> = ArrayList()
    val networkServiceGoods = ApplicationController.networkServiceGoods
    var lastIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_exhibition_detail)

        e_idx = intent.getIntExtra("e_idx",-1)
        title = intent.getStringExtra("title")
        subtitle = intent.getStringExtra("subtitle")
        background_img = intent.getStringExtra("gradation_img")
        setExhibitionTitle()
        setOnScrollChange()
        setRecyclerView()
        goodsExhibitionDetailResponse(e_idx)
        setStatusBarTransparent()
    }

    private fun setOnScrollChange(){
        if(Build.VERSION.SDK_INT >=23){
            nsv_goods_exhibition_detail_act.setOnScrollChangeListener(object: View.OnScrollChangeListener{
                override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                    if(!isRequested && !nsv_goods_exhibition_detail_act.canScrollVertically(1)){
                        isRequested = true
                        goodsExhibitionDetailResponse(e_idx)
                    }
                }
            })
        }
    }

    private fun setStatusBarTransparent() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
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

    private fun setExhibitionTitle(){
        //iv_goods_exhibition_detail_act_background.setImageDrawable(resources.getDrawable(R.drawable.img_keyrop))
        val seperate_title= title.split("/")
        val seperate_subtitle = subtitle.split("/")
        tv_goods_exhibition_detail_act_title1.text = seperate_title[0]
        tv_goods_exhibition_detail_act_title2.text = seperate_title[1]
        tv_goods_exhibition_detail_act_title3.text = seperate_title[2]
        val title3:TextView = findViewById(R.id.tv_goods_exhibition_detail_act_title3)
        if(seperate_title[2]!=""){
            title3.visibility = View.VISIBLE
        }
        tv_goods_exhibition_detail_act_subtitle1.text = seperate_subtitle[0]
        tv_goods_exhibition_detail_act_subtitle2.text = seperate_subtitle[1]
        Glide.with(this)
            .load(background_img)
            .into(findViewById(R.id.iv_goods_exhibition_detail_act_background))
    }

    private fun goodsExhibitionDetailResponse(exhibition_idx:Int){
        val getExhibitionDetailResponse = networkServiceGoods.getExhibitionDetailResponse("application/json",User.authorization,exhibition_idx,lastIndex)
        getExhibitionDetailResponse.enqueue(object: Callback<GetExhibitionDetailResponse>{
            override fun onFailure(call: Call<GetExhibitionDetailResponse>, t: Throwable) {
                Log.e("ExhibitionDetail Fail", t.toString())
            }
            override fun onResponse(call: Call<GetExhibitionDetailResponse>, response: Response<GetExhibitionDetailResponse>) {
                if(response.isSuccessful){
                    if(lastIndex!=-1){
                        var prev_cnt = dataList.size
                        dataList.addAll(response.body()!!.data)

                        goodsExhibitionDetailRecyclerViewAdapter.dataList = dataList
                        goodsExhibitionDetailRecyclerViewAdapter.notifyItemRangeInserted(prev_cnt,dataList.size-prev_cnt)
                    } else{
                        dataList = response.body()!!.data
                        goodsExhibitionDetailRecyclerViewAdapter.dataList = dataList
                        goodsExhibitionDetailRecyclerViewAdapter.notifyDataSetChanged()
                        nsv_goods_exhibition_detail_act.fullScroll(NestedScrollView.FOCUS_UP)
                    }
                    isRequested = false
                    if(dataList.size ==0) lastIndex = -1
                    else lastIndex = dataList[dataList.size-1].goods_idx
                }
            }
        })
    }

    private fun setRecyclerView() {
        goodsExhibitionDetailRecyclerViewAdapter = GoodsExhibitionDetailRecyclerViewAdapter(this, dataList)
        rv_goods_exhibition_detail_act_list.adapter = goodsExhibitionDetailRecyclerViewAdapter
        rv_goods_exhibition_detail_act_list.layoutManager = GridLayoutManager(this, 2)
    }

}
