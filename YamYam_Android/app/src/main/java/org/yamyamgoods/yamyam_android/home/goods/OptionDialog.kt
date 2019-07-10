package org.yamyamgoods.yamyam_android.home.goods

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
import kotlinx.android.synthetic.main.dialog_option.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.goods.adapter.GoodsCategoryOptionRecyclerViewAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.GetPriceRangeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OptionDialog(context:Context) : Dialog(context), View.OnClickListener {

    lateinit var goodsCategoryOptionRecyclerViewAdapter: GoodsCategoryOptionRecyclerViewAdapter
    lateinit var getPriceRangeResponse: GetPriceRangeResponse
    var c_idx: Int = -1
    //var dataList: ArrayList<GoodsCategoryOptionData> = ArrayList()
    val networkServiceGoods = ApplicationController.networkServiceGoods
    val choiceflag:Int = -1
    lateinit var min: String
    lateinit var max: String
    override fun onClick(v: View?) {
        try{
            dismiss()
        } catch(e: Exception){
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_option)
        c_idx = GoodsCategoryDetailFragment.instance.categoryIdx
        priceRangeResponse(c_idx)
        setOnClickListener()
    }

    private fun setRangeSeekbar() {
        val rangeSeekBar: CrystalRangeSeekbar = findViewById(R.id.range_seekbar)
        val minv: Float = min.toFloat()
        val maxv: Float = max.toFloat()
        rangeSeekBar.setMinStartValue(minv)
        rangeSeekBar.setMaxStartValue(maxv)

        rangeSeekBar.setOnRangeSeekbarChangeListener (object :OnRangeSeekbarFinalValueListener,OnRangeSeekbarChangeListener{
            override fun finalValue(minValue: Number?, maxValue: Number?) {
                tv_dialog_option_price_min.text = min
                tv_dialog_option_price_max.text = max
            }

            override fun valueChanged(minValue: Number?, maxValue: Number?) {
            }

        })
    }

    private fun setOnClickListener(){
        btn_dialog_option_close.setOnClickListener {
            dismiss()
        }

        btn_dialog_option_price.setOnClickListener {
            setView(0)
        }

        btn_dialog_option_minAmount.setOnClickListener {
            setView(1)
        }

        btn_dialog_option_variety.setOnClickListener {
            setView(2)
        }
        btn_dialog_option_confirm.setOnClickListener {
            dismiss()
        }
    }

    private fun setView(choice:Int){
        if(choice == 0){
            btn_dialog_option_price.setTextColor(context.resources.getColor(R.color.darkgray))
            btn_dialog_option_minAmount.setTextColor(context.resources.getColor(R.color.lightgray))
            btn_dialog_option_variety.setTextColor(context.resources.getColor(R.color.lightgray))
            rl_dialog_option_price.visibility = View.VISIBLE
            rl_dialog_option_min_amount.visibility = View.INVISIBLE
            rl_dialog_option_variety.visibility = View.INVISIBLE
        } else if(choice == 1){
            btn_dialog_option_price.setTextColor(context.resources.getColor(R.color.lightgray))
            btn_dialog_option_minAmount.setTextColor(context.resources.getColor(R.color.darkgray))
            btn_dialog_option_variety.setTextColor(context.resources.getColor(R.color.lightgray))
            rl_dialog_option_price.visibility = View.INVISIBLE
            rl_dialog_option_min_amount.visibility = View.VISIBLE
            rl_dialog_option_variety.visibility = View.INVISIBLE
        } else if(choice == 2){
            btn_dialog_option_price.setTextColor(context.resources.getColor(R.color.lightgray))
            btn_dialog_option_minAmount.setTextColor(context.resources.getColor(R.color.lightgray))
            btn_dialog_option_variety.setTextColor(context.resources.getColor(R.color.darkgray))
            rl_dialog_option_price.visibility = View.INVISIBLE
            rl_dialog_option_min_amount.visibility = View.INVISIBLE
            rl_dialog_option_variety.visibility = View.VISIBLE
        }
    }

//    private fun optionDialogVarietyResponse(category_idx:Int){
//        val optionDialogResponse = networkServiceGoods.getOptionDialog
//    }

    private fun priceRangeResponse(category_idx:Int){
        val getPriceRangeResponse = networkServiceGoods.getPriceRangeResponse("application/json",1,null)
        getPriceRangeResponse.enqueue(object: Callback<GetPriceRangeResponse>{
            override fun onFailure(call: Call<GetPriceRangeResponse>, t: Throwable) {
                Log.e("OptionPriceRange fail", t.toString())
            }
            override fun onResponse(call: Call<GetPriceRangeResponse>, response: Response<GetPriceRangeResponse>) {
                if(response.isSuccessful){
                    min = response.body()!!.data.price_start
                    max = response.body()!!.data.price_end
                    setRangeSeekbar()
                }
            }
        })
    }

//    private fun setRecyclerView(){
//        goodsCategoryOptionRecyclerViewAdapter = GoodsCategoryOptionRecyclerViewAdapter(context, dataList)
//        rv_dialog_option_variety.adapter = goodsCategoryOptionRecyclerViewAdapter
//        rv_dialog_option_variety.layoutManager = GridLayoutManager(context, 3)
//    }

}
