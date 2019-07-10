package org.yamyamgoods.yamyam_android.productdetail.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.ProductOption
import org.yamyamgoods.yamyam_android.dataclass.ProductOptionDetail
import org.yamyamgoods.yamyam_android.dataclass.SelectedOption

/**
 * Created By Yun Hyeok
 * on 7월 10, 2019
 */
class ProductOptionsRVAdatper(private val ctx: Context, private val dataList: List<ProductOption>) :
    RecyclerView.Adapter<ProductOptionsRVAdatper.Holder>() {

    var selectedOptions = ArrayList<SelectedOption>()
    var totalPrice = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_product_option, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        dataList[position].let {
            val optionMap = getOptionValueMap(it.goods_option_detail)
            val optionList = getOptionValueList(it.goods_option_detail)
            setDefaultOption(position)
            holder.optionKey.text = it.goods_option_name
            holder.optionValues.adapter = ArrayAdapter(ctx, R.layout.support_simple_spinner_dropdown_item, optionList)
        }
        Log.v("Malibin Debug", selectedOptions.toString())
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val optionKey: TextView = itemView.findViewById(R.id.tv_rv_item_product_option)
        val optionValues: Spinner = itemView.findViewById(R.id.spinner_rv_item_product_option)
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
//    private fun checkEmptyOption() {
//        selectedOptions
//            ?.takeIf { it.isEmpty() }
//            ?.apply {
//                selectedOptions = null
//            }
//    }
}