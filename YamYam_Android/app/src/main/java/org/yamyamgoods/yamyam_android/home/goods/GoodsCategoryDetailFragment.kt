package org.yamyamgoods.yamyam_android.home.goods

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_goods_category_detail.*
import org.yamyamgoods.yamyam_android.SortDialog
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.home.HomeActivity
import org.yamyamgoods.yamyam_android.home.goods.adapter.GoodsCategoryDetailRecyclerViewAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.CategoryOptionData
import org.yamyamgoods.yamyam_android.network.get.GetCategoryDetailResponse
import org.yamyamgoods.yamyam_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoodsCategoryDetailFragment : Fragment() {
    var dataList: ArrayList<GoodsData> = ArrayList()
    var isRequested = false
    var order: Int = 0
    var lastIndex: Int = -1
    var sort:String? = null
    var categoryIdx:Int = -1
    var sort_flag: String? = null
    var option_flag: Int = -1
    var price_start: Int? = null
    var price_end: Int? = null
    var min_amount: Int? = null
    var options: ArrayList<Int>? = null
    var dataListOption : ArrayList<CategoryOptionData> = ArrayList()
    companion object {
        var instance : GoodsCategoryDetailFragment = GoodsCategoryDetailFragment()
    }
    val networkServiceGoods = ApplicationController.networkServiceGoods
    lateinit var goodsCategoryDetailRecyclerViewAdapter: GoodsCategoryDetailRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_goods_category_detail, container, false)
        categoryIdx = instance.categoryIdx
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnScrollChange()
        setOnClickListener()
        setRecyclerView()
        categoryDetailResponse(categoryIdx)
    }

    private fun setOnScrollChange(){
        try{
            if(Build.VERSION.SDK_INT >=23){
                nsv_goods_category_detail_frag.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                    if(!isRequested && !nsv_goods_category_detail_frag.canScrollVertically(1)){
                        isRequested = true
                        categoryDetailResponse(categoryIdx)
                    }
                }
            }
        } catch(e:Exception){
        }
    }

    private fun setOnClickListener(){
        btn_frag_goods_category_detail_sort.setOnClickListener {
            try{
                instance.sort = sort
                setSortDialog()
            } catch(e:Exception){
            }
        }
        btn_frag_goods_category_detail_price.setOnClickListener {
            instance.option_flag = 0
            instance.price_start = price_start
            instance.price_end = price_end
            instance.min_amount = min_amount
            instance.options = options
            categoryIdx = instance.categoryIdx
            setOptionDialog()
        }
        btn_frag_goods_category_detail_minQuantity.setOnClickListener {
            instance.option_flag = 1
            instance.price_start = price_start
            instance.price_end = price_end
            instance.min_amount = min_amount
            instance.options = options
            setOptionDialog()
        }
        btn_frag_goods_category_detail_variety.setOnClickListener {
            instance.option_flag = 2
            instance.price_start = price_start
            instance.price_end = price_end
            instance.min_amount = min_amount
            instance.dataListOption = dataListOption
            Log.e("**GCDF", "options : $options")
            instance.options = options
            setOptionDialog()
        }
    }

    fun setSortDialog(){
        var sortDialog = SortDialog(context as HomeActivity)
        sortDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        sortDialog.setCanceledOnTouchOutside(false)
        sortDialog.show()

        sortDialog.setOnDismissListener {
            lastIndex = -1
            setSortFlag()
            setRecyclerView()
            categoryDetailResponse(categoryIdx)
        }
    }

    fun setOptionDialog(){
        var optionDialog = OptionDialog(context as HomeActivity)
        optionDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        optionDialog.setCanceledOnTouchOutside(false)
        optionDialog.show()

        optionDialog.setOnDismissListener {
            lastIndex = -1
            setOptionFlag()
            setRecyclerView()
            categoryDetailResponse(categoryIdx)
        }
    }

    fun setSortFlag(){
        if(instance.sort_flag==null){
            tv_frag_goods_category_detail_sort.text = "인기순"
        } else {
            tv_frag_goods_category_detail_sort.text = instance.sort_flag
            sort = instance.sort_flag
        }
        when (sort) {
            "인기순" -> order=0
            "고가순" -> order=1
            "저가순" -> order=2
        }
    }

    fun setOptionFlag(){
        if(instance.min_amount!=null){
            min_amount = instance.min_amount
        }
        if(instance.price_start != null){
            price_start = instance.price_start
            price_end = instance.price_end
        }
        if(instance.options !=null){
            options = instance.options
        }
        dataListOption = instance.dataListOption
    }

    fun categoryDetailResponse(category_idx: Int) {
        val getCategoryDetailResponse = networkServiceGoods.getCategoryDetailResponse(
            "application/json", User.authorization, category_idx, order, lastIndex,
            price_start, price_end, min_amount, options)
        Log.e("**GCDF", "min_amount : $min_amount, price_start: $price_start, price_end: $price_end, options: $options")

        getCategoryDetailResponse.enqueue(object: Callback<GetCategoryDetailResponse>{
            override fun onFailure(call: Call<GetCategoryDetailResponse>, t: Throwable) {
                Log.e("Category-detail fail", t.toString())
            }

            override fun onResponse(call: Call<GetCategoryDetailResponse>, response: Response<GetCategoryDetailResponse>) {
                if(response.isSuccessful) {
                    try{
                        if (lastIndex != -1) {
                            var prev_cnt = dataList.size
                            dataList.addAll(response.body()!!.data)
                            goodsCategoryDetailRecyclerViewAdapter.dataList = dataList
                            goodsCategoryDetailRecyclerViewAdapter.notifyItemRangeInserted(prev_cnt,dataList.size - prev_cnt)
                        } else {
                            dataList = response.body()!!.data
                            goodsCategoryDetailRecyclerViewAdapter.dataList = dataList
                            goodsCategoryDetailRecyclerViewAdapter.notifyDataSetChanged()
                            nsv_goods_category_detail_frag.fullScroll(NestedScrollView.FOCUS_UP)
                        }
                        isRequested = false
                        if (dataList.size == 0) lastIndex = -1
                        else lastIndex = dataList[dataList.size - 1].goods_idx
                    } catch(e:Exception){
                    }
                }
            }
        })
    }

    private fun setRecyclerView() {
        goodsCategoryDetailRecyclerViewAdapter = GoodsCategoryDetailRecyclerViewAdapter(activity!!, dataList)
        rv_frag_goods_category_detail.adapter = goodsCategoryDetailRecyclerViewAdapter
        rv_frag_goods_category_detail.layoutManager = GridLayoutManager(activity!!,2)
    }
}