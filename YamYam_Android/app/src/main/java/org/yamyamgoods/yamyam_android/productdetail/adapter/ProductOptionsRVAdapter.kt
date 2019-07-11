package org.yamyamgoods.yamyam_android.productdetail.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.ProductOption
import org.yamyamgoods.yamyam_android.dataclass.ProductOptionDetail
import org.yamyamgoods.yamyam_android.dataclass.SelectedOption
import org.yamyamgoods.yamyam_android.productdetail.ProductDetailActivity
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Created By Yun Hyeok
 * on 7월 10, 2019
 */

class ProductOptionsRVAdapter(private val ctx: Context, private val dataList: List<ProductOption>) :
    RecyclerView.Adapter<ProductOptionsRVAdapter.Holder>() {

    var selectedOptions = ArrayList<SelectedOption>()
    var basePrice = -1
    var totalPrice = -1

    private val optionHolders = ArrayList<Holder>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_product_option, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        optionHolders.add(holder)

        dataList[position].let {
            val optionMap = getOptionValueMap(it.goods_option_detail)
            val optionList = getOptionValueList(it.goods_option_detail)
            setDefaultOption(position)

            holder.optionName = it.goods_option_name
            holder.optionDetails = optionList
            holder.optionMap = optionMap
            holder.currentOptionDetail = it.goods_option_detail[0]

            holder.optionNameTextView.text = it.goods_option_name
            holder.optionSpinner.apply {
                adapter = ArrayAdapter(ctx, R.layout.spinner_item_option, R.id.tv_spinner_option, optionList)
            }
        }
        Log.v("Malibin Debug", "onBindViewHolder : ${selectedOptions.toString()}")
    }


    private fun setDefaultOption(position: Int) {
        dataList[position].let {
            selectedOptions.add(
                SelectedOption(
                    it.goods_option_name,
                    it.goods_option_detail[0].goods_option_detail_name
                )
            )
            totalPrice += it.goods_option_detail[0].goods_option_detail_price
        }
    }

    private fun getOptionValueMap(list: List<ProductOptionDetail>): Map<String, Int> {
        val result = HashMap<String, Int>()
        for (data in list)
            result[data.goods_option_detail_name] = data.goods_option_detail_price
        return result
    }

    private fun getOptionValueList(list: List<ProductOptionDetail>): List<String> {
        val result = ArrayList<String>()
        for (data in list)
            result.add(data.goods_option_detail_name)
        return result
    }

    private fun changeSelectedOption(oldOption: SelectedOption, newOption: SelectedOption) {
        selectedOptions.remove(oldOption)
        selectedOptions.add(newOption)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var optionName: String
        lateinit var optionDetails: List<String>
        lateinit var optionMap: Map<String, Int>
        lateinit var currentOptionDetail: ProductOptionDetail

        val optionNameTextView: TextView = itemView.findViewById(R.id.tv_rv_item_product_option)
        val optionSpinner: Spinner = itemView.findViewById(R.id.spinner_rv_item_product_option)

        private val spinnerListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                priceCalculateProcess(position)
            }
        }

        init {
            optionSpinner.onItemSelectedListener = spinnerListener
        }

        private fun priceCalculateProcess(position: Int) {

            val oldOptionDetailName = currentOptionDetail.goods_option_detail_name
            val oldOptionDetailPrice = optionMap[oldOptionDetailName]

            val newOptionDetailName = optionDetails[position]
            val newOptionDetailPrice = optionMap[newOptionDetailName]

            val oldOption = SelectedOption(optionName, oldOptionDetailName)
            val newOption = SelectedOption(optionName, newOptionDetailName)

            changeSelectedOption(oldOption, newOption)
            currentOptionDetail = ProductOptionDetail(newOptionDetailName, newOptionDetailPrice!!)

            totalPrice = totalPrice - oldOptionDetailPrice!! + newOptionDetailPrice

            (ctx as ProductDetailActivity).refreshOptionData(totalPrice, selectedOptions)
            ctx.notifyTotalPrice()

            Log.v("Malibin Debug", "currunt Price : $totalPrice")
            Log.v("Malibin Debug", "onItemSelected : $selectedOptions")
        }
    }
}