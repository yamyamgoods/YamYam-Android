package org.yamyamgoods.yamyam_android.home.goods

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar
import kotlinx.android.synthetic.main.dialog_option.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.CategoryOptionData
import org.yamyamgoods.yamyam_android.network.get.GetGoodsCategoryOptionsResponse
import org.yamyamgoods.yamyam_android.network.get.GetPriceRangeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OptionDialog(context:Context) : Dialog(context), View.OnClickListener {

    var c_idx: Int = -1
    var dataList: ArrayList<CategoryOptionData> = ArrayList()
    val networkServiceGoods = ApplicationController.networkServiceGoods
    var min: String? = "10000"
    var max: String? = "30000"
    var option_flag = -1
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
        setContentView(R.layout.dialog_option)
        c_idx = GoodsCategoryDetailFragment.instance.categoryIdx
        option_flag = GoodsCategoryDetailFragment.instance.option_flag
        price_start = GoodsCategoryDetailFragment.instance.price_start
        price_end = GoodsCategoryDetailFragment.instance.price_end
        min_amount = GoodsCategoryDetailFragment.instance.min_amount
        options = GoodsCategoryDetailFragment.instance.options
        dataList = GoodsCategoryDetailFragment.instance.dataListOption
        Log.e("**OD",options.toString())
        setView(option_flag)
        priceRangeResponse(c_idx)
        goodsCategoryOptionsResponse(c_idx)
        setOnClickListener()
    }

    private fun setRangeSeekbar() {
        val rangeSeekBar: CrystalRangeSeekbar = findViewById(R.id.range_seekbar)

        min = min!!.replace(",0","0")
        val minv: Float = Integer.parseInt(min).toFloat()
        max = max!!.replace(",0","0")
        val maxv: Float = Integer.parseInt(max).toFloat()
        rangeSeekBar.setMinValue(minv)
        rangeSeekBar.setMaxValue(maxv)
        if(price_start!=null){
            rangeSeekBar.setMinStartValue(price_start!!.toFloat())
                .setMaxStartValue(price_end!!.toFloat())
                .apply()
        }

        rangeSeekBar.setOnRangeSeekbarChangeListener { minValue, maxValue ->
            tv_dialog_option_price_min.text = minValue.toString()
            tv_dialog_option_price_max.text = maxValue.toString()
        }
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

            setPriceDismiss()
            setVarietyDismiss()
            setMinAmountDismiss()
            if(dismiss_flag == 1){
                dismiss()
            }
        }
    }

    private fun setView(choice:Int){

        when (choice) {
            0 -> {
                btn_dialog_option_price.setTextColor(context.resources.getColor(R.color.darkgray))
                btn_dialog_option_minAmount.setTextColor(context.resources.getColor(R.color.lightgray))
                btn_dialog_option_variety.setTextColor(context.resources.getColor(R.color.lightgray))
                rl_dialog_option_price.visibility = View.VISIBLE
                rl_dialog_option_min_amount.visibility = View.INVISIBLE
                rl_dialog_option_variety.visibility = View.INVISIBLE
            }
            1 -> {
                btn_dialog_option_price.setTextColor(context.resources.getColor(R.color.lightgray))
                btn_dialog_option_minAmount.setTextColor(context.resources.getColor(R.color.darkgray))
                btn_dialog_option_variety.setTextColor(context.resources.getColor(R.color.lightgray))
                rl_dialog_option_price.visibility = View.INVISIBLE
                rl_dialog_option_min_amount.visibility = View.VISIBLE
                rl_dialog_option_variety.visibility = View.INVISIBLE
                if(min_amount!=null){
                    et_dialog_option_minAmount.setText(min_amount.toString())
                }
            }
            2 -> {

                btn_dialog_option_price.setTextColor(context.resources.getColor(R.color.lightgray))
                btn_dialog_option_minAmount.setTextColor(context.resources.getColor(R.color.lightgray))
                btn_dialog_option_variety.setTextColor(context.resources.getColor(R.color.darkgray))
                rl_dialog_option_price.visibility = View.INVISIBLE
                rl_dialog_option_min_amount.visibility = View.INVISIBLE
                rl_dialog_option_variety.visibility = View.VISIBLE

            }
        }
    }

    private fun priceRangeResponse(category_idx:Int){
        val getPriceRangeResponse = networkServiceGoods.getPriceRangeResponse("application/json",category_idx,null)
        getPriceRangeResponse.enqueue(object: Callback<GetPriceRangeResponse>{
            override fun onFailure(call: Call<GetPriceRangeResponse>, t: Throwable) {
                Log.e("OptionPriceRange fail", t.toString())
            }
            override fun onResponse(call: Call<GetPriceRangeResponse>, response: Response<GetPriceRangeResponse>) {
                if(response.isSuccessful){
                    min = response.body()!!.data.price_start
                    max = response.body()!!.data.price_end
                    Log.e("OptionPrice Success", "성고옹$min$max")
                    setRangeSeekbar()
                }
            }
        })
    }

    private fun goodsCategoryOptionsResponse(category_idx:Int){
        val getGoodsCategoryOptionsResponse = networkServiceGoods.getGoodsCategoryOptionsResponse("application/json",category_idx)
        getGoodsCategoryOptionsResponse.enqueue(object: Callback<GetGoodsCategoryOptionsResponse>{
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

                    setView(option_flag)
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

    fun setPriceDismiss(){
        val priceStart: Int = tv_dialog_option_price_min.text.toString().toInt()
        val priceEnd: Int = tv_dialog_option_price_max.text.toString().toInt()
        try{
            GoodsCategoryDetailFragment.instance.price_start = priceStart
            GoodsCategoryDetailFragment.instance.price_end = priceEnd
            Log.e("**OD","priceStart : $priceStart, priceEnd: $price_end")
            dismiss_flag = 1
        } catch(e:Exception){
        }
    }

    fun setMinAmountDismiss() {
        val minAmount: String = et_dialog_option_minAmount.text.toString()
        try {
            if (minAmount == "") {
                GoodsCategoryDetailFragment.instance.min_amount = null
                dismiss_flag = 1
            } else if (minAmount.toInt() < 0) {
                et_dialog_option_minAmount.setText("")
                Toast.makeText(context, "0 이상을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                GoodsCategoryDetailFragment.instance.min_amount = minAmount.toInt()
                dismiss_flag = 1
            }
        } catch (e: Exception) {
            et_dialog_option_minAmount.setText("")
            Toast.makeText(context, "숫자를 입력해주세요!", Toast.LENGTH_SHORT).show()
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
