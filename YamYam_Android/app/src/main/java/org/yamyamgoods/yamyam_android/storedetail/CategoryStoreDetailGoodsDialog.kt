package org.yamyamgoods.yamyam_android.storedetail

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
import kotlinx.android.synthetic.main.dialog_option.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.goods.GoodsCategoryDetailFragment
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.CategoryOptionData
import org.yamyamgoods.yamyam_android.network.get.GetGoodsCategoryOptionsResponse
import org.yamyamgoods.yamyam_android.network.get.GetPriceRangeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryStoreDetailGoodsDialog(context: Context) : Dialog(context), View.OnClickListener {

    var c_idx: Int = -1
    var dataList: ArrayList<CategoryOptionData> = ArrayList()
    val networkServiceGoods = ApplicationController.networkServiceGoods
    var dismiss_flag: Int = -1
    val rl_variety = ArrayList<RelativeLayout>()
    val variety = ArrayList<TextView>()

    var price_start: Int? = null
    var price_end: Int? = null
    var min_amount: Int? = null
    var options: ArrayList<Int>? = null
    var size : Int = -1

    override fun onClick(v: View?) {
        try{
            dismiss()
        } catch(e: Exception){
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_category_store_detail_goods)
        c_idx = GoodsCategoryDetailFragment.instance.categoryIdx
        min_amount = GoodsCategoryDetailFragment.instance.min_amount
        options = GoodsCategoryDetailFragment.instance.options
        dataList = GoodsCategoryDetailFragment.instance.dataListOption
        Log.e("**OD",options.toString())
        goodsCategoryOptionsResponse(c_idx)
        setOnClickListener()
    }

    private fun setOnClickListener(){
        btn_dialog_option_close.setOnClickListener {
            dismiss()
        }
        btn_dialog_option_confirm.setOnClickListener {
            setVarietyDismiss()
            if(dismiss_flag == 1){
                dismiss()
            }
        }
    }

    private fun goodsCategoryOptionsResponse(category_idx:Int){
        val getGoodsCategoryOptionsResponse = networkServiceGoods.getGoodsCategoryOptionsResponse("application/json",category_idx)
        getGoodsCategoryOptionsResponse.enqueue(object: Callback<GetGoodsCategoryOptionsResponse> {
            override fun onFailure(call: Call<GetGoodsCategoryOptionsResponse>, t: Throwable) {
                Log.e("CategoryOptions fail", t.toString())
            }
            override fun onResponse(call: Call<GetGoodsCategoryOptionsResponse>, response: Response<GetGoodsCategoryOptionsResponse>) {
                if(response.isSuccessful){
                    dataList = response.body()!!.data
                    GoodsCategoryDetailFragment.instance.dataListOption = dataList
                    Log.e("CategoryOptions Success","성고옹")
                    setVarieties()
                    Log.e("**OD","dataList : $dataList")
                    if(options!=null){
                        for(i in 0 until dataList.size){
                            Log.e("**OD",options.toString())
                            if(dataList[i].category_option_idx in options!!){
                                rl_variety[i].isSelected = true
                                variety[i].setTextColor(context.resources.getColor(R.color.colorWhite))
                            }
                        }
                    }
                }
            }
        })
    }

    private fun setVarieties(){

        variety.add(tv_dialog_option_variety1)
        variety.add(tv_dialog_option_variety2)
        variety.add(tv_dialog_option_variety3)
        variety.add(tv_dialog_option_variety4)
        variety.add(tv_dialog_option_variety5)
        variety.add(tv_dialog_option_variety6)
        variety.add(tv_dialog_option_variety7)
        variety.add(tv_dialog_option_variety8)
        variety.add(tv_dialog_option_variety9)
        variety.add(tv_dialog_option_variety10)
        variety.add(tv_dialog_option_variety11)

        rl_variety.add(btn_dialog_option_variety1)
        rl_variety.add(btn_dialog_option_variety2)
        rl_variety.add(btn_dialog_option_variety3)
        rl_variety.add(btn_dialog_option_variety4)
        rl_variety.add(btn_dialog_option_variety5)
        rl_variety.add(btn_dialog_option_variety6)
        rl_variety.add(btn_dialog_option_variety7)
        rl_variety.add(btn_dialog_option_variety8)
        rl_variety.add(btn_dialog_option_variety9)
        rl_variety.add(btn_dialog_option_variety10)
        rl_variety.add(btn_dialog_option_variety11)

        for(i in 0 until dataList.size){
            rl_variety[i].visibility = View.VISIBLE
            variety[i].text = dataList[i].category_option_name
        }
        for(i in 0 until dataList.size){
            rl_variety[i].setOnClickListener {
                rl_variety[i].isSelected = !(rl_variety[i].isSelected)
                if(!rl_variety[i].isSelected){
                    variety[i].setTextColor(context.resources.getColor(R.color.darkgray))
                } else{
                    variety[i].setTextColor(context.resources.getColor(R.color.colorWhite))
                }
            }
        }
    }

    fun setVarietyDismiss() {
        val optionNetwork: ArrayList<Int> = ArrayList()
        for(i in 0 until dataList.size) {
            if(rl_variety[i].isSelected){
                optionNetwork.add(dataList[i].category_option_idx)
            }
        }
        GoodsCategoryDetailFragment.instance.options = optionNetwork
        dismiss_flag = 1
    }
}