package org.yamyamgoods.yamyam_android.storedetail

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_mypage.*
import kotlinx.android.synthetic.main.dialog_category_store_detail_goods.*
import kotlinx.android.synthetic.main.dialog_option.btn_dialog_option_close
import kotlinx.android.synthetic.main.dialog_option.btn_dialog_option_confirm
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.GetStoreDetailGoodsCategoryResponse
import org.yamyamgoods.yamyam_android.network.get.categoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryStoreDetailGoodsDialog(context: Context) : Dialog(context), View.OnClickListener {

    var s_idx: Int = -1
    var dataList: ArrayList<categoryData> = ArrayList()
    val networkServiceStore = ApplicationController.networkServiceStore
    val rl_variety = ArrayList<RelativeLayout>()
    val variety = ArrayList<TextView>()
    var gc_idx: Int? = null
    var gc_idx_flag: Int? = null

    override fun onClick(v: View?) {
        try{
            dismiss()
        } catch(e: Exception){
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_category_store_detail_goods)
        s_idx = StoreDetailGoodsFragment.instance.s_idx
        gc_idx = StoreDetailGoodsFragment.instance.goodsCategoryIdx
        goodsCategoryOptionsResponse(s_idx)
        setOnClickListener()
    }

    private fun setOnClickListener(){
        btn_dialog_option_close.setOnClickListener {
            dismiss()
        }
        btn_dialog_option_confirm.setOnClickListener {
            setCategoryDismiss()
            dismiss()
        }
    }

    private fun goodsCategoryOptionsResponse(s_idx:Int){
        val getStoreDetailGoodsCategoryResponse = networkServiceStore.getStoreDetailGoodsCategoryResponse("application/json",s_idx)
        getStoreDetailGoodsCategoryResponse.enqueue(object: Callback<GetStoreDetailGoodsCategoryResponse> {
            override fun onFailure(call: Call<GetStoreDetailGoodsCategoryResponse>, t: Throwable) {
                Log.e("CategoryOptions fail", t.toString())
            }
            override fun onResponse(call: Call<GetStoreDetailGoodsCategoryResponse>, response: Response<GetStoreDetailGoodsCategoryResponse>) {
                if(response.isSuccessful){
                    dataList = response.body()!!.data
                    Log.e("CategoryOptions Success","성고옹")
                    setVarieties()
                    Log.e("**OD","dataList : $dataList")
                    if(gc_idx!=null){
                        for(i in 0 until dataList.size){
                            Log.e("**OD",gc_idx.toString())
                            if(dataList[i].goods_category_idx == gc_idx){
                                rl_variety[i].isSelected = true
                            }
                        }
                    }
                }
            }
        })
    }

    private fun setVarieties(){

        variety.add(tv_category1)
        variety.add(tv_category2)
        variety.add(tv_category3)
        variety.add(tv_category4)
        variety.add(tv_category5)
        variety.add(tv_category6)
        variety.add(tv_category7)
        variety.add(tv_category8)
        variety.add(tv_category9)
        variety.add(tv_category10)
        variety.add(tv_category11)

        rl_variety.add(btn_category1)
        rl_variety.add(btn_category2)
        rl_variety.add(btn_category3)
        rl_variety.add(btn_category4)
        rl_variety.add(btn_category5)
        rl_variety.add(btn_category6)
        rl_variety.add(btn_category7)
        rl_variety.add(btn_category8)
        rl_variety.add(btn_category9)
        rl_variety.add(btn_category10)
        rl_variety.add(btn_category11)

        val rll_variety = ArrayList<RelativeLayout>()

        for(i in 0 until dataList.size){
            rl_variety[i].visibility = View.VISIBLE
            variety[i].text = dataList[i].goods_category_name
            rll_variety.add(rl_variety[i])
        }


        var currentSelected : RelativeLayout? = btn_category11

        rll_variety.forEach { it ->
            it.setOnClickListener {
                if(currentSelected == it) {
                    currentSelected!!.isSelected = false
                }
                else {
                    it.isSelected = true
                    currentSelected!!.isSelected = false
                    currentSelected = it as RelativeLayout
                    currentSelected!!.isSelected = true
                }
            }
        }

    }

    fun setCategoryDismiss(){
        var flag : Int = -1
        for(i in 0 until dataList.size) {
            if(rl_variety[i].isSelected) {
                flag = 0
                gc_idx = dataList[i].goods_category_idx
            }
        }
        if(flag != 0) {
            gc_idx = null
        }
        StoreDetailGoodsFragment.instance.goodsCategoryIdx = gc_idx
    }
}