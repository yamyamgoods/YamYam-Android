package org.yamyamgoods.yamyam_android.home.bookmark.dialog

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
import org.yamyamgoods.yamyam_android.network.get.BookmarkItemOption
import org.yamyamgoods.yamyam_android.productdetail.ProductDetailActivity

/**
 * Created By Yun Hyeok
 * on 7월 10, 2019
 */

class BookmarkOptionsRVAdapter(
    private val ctx: Context,
    private val data: BookmarkItemOption,
    private val dialog: BookmarkOptionDialog
) :
    RecyclerView.Adapter<BookmarkOptionsRVAdapter.Holder>() {

    var dataList = ArrayList<ProductOption>()
    var selectedOptions = ArrayList<SelectedOption>()
    var selectedOptionsMap: Map<String, String>
    var basePrice = data.goods_price.replace(",", "").toInt()
    var totalPrice = -1

    init {
        dataList.addAll(data.goods_option_data)
        selectedOptions.addAll(data.goods_scrap_option_data)
        selectedOptionsMap = getSelectedOptionsMap(selectedOptions)
        Log.v("Malibin Debug", "init{} selectedOptions : $selectedOptions")

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_product_option, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        dataList[position].let {
            val optionMap = getOptionValueMap(it.goods_option_detail)
            val optionList = getOptionValueList(it.goods_option_detail)
            val optionName = it.goods_option_name
            val currentOptionDetailName = selectedOptionsMap[optionName]
            val currentOptionDetailValue = optionMap[currentOptionDetailName]

            holder.optionName = optionName
            holder.optionDetails = optionList
            holder.optionMap = optionMap

            Log.v(
                "Malibin Debug",
                "currentOptionDetailName : $currentOptionDetailName , currentOptionDetailValue : $currentOptionDetailValue"
            )
            holder.currentOptionDetail = ProductOptionDetail(currentOptionDetailName!!, currentOptionDetailValue!!)

            holder.optionNameTextView.text = it.goods_option_name
            holder.optionSpinner.apply {
                adapter = ArrayAdapter(ctx, R.layout.spinner_item_option, R.id.tv_spinner_option, optionList)
            }

            val optionIdx = optionList.indexOf(currentOptionDetailName)
            holder.setSpinnerItem(optionIdx)
            holder.setSpinnerListener()
        }
        Log.v("Malibin Debug", "onBindViewHolder : $selectedOptions")
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

    private fun getSelectedOptionsMap(options: List<SelectedOption>): Map<String, String> {
        val result = HashMap<String, String>()
        for (option in options) {
            result[option.optionName] = option.optionValue
        }
        return result
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var optionName: String
        lateinit var optionDetails: List<String>
        lateinit var optionMap: Map<String, Int>
        lateinit var currentOptionDetail: ProductOptionDetail

        val optionNameTextView: TextView = itemView.findViewById(R.id.tv_rv_item_product_option)
        val optionSpinner: Spinner = itemView.findViewById(R.id.spinner_rv_item_product_option)

        private var isFirstSpinner = true

        private val spinnerListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isFirstSpinner) {
                    isFirstSpinner = false
                    return
                }
                priceCalculateProcess(position)
            }
        }

        fun setSpinnerItem(optionIdx: Int) {
            optionSpinner.setSelection(optionIdx)
        }

        fun setSpinnerListener() {
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

            dialog.refreshOptionData(totalPrice, selectedOptions)
            dialog.notifyTotalPrice()

            Log.v("Malibin Debug", "currunt Price : $totalPrice, basePrice : $basePrice")
            Log.v("Malibin Debug", "onItemSelected : $selectedOptions")
        }
    }
}