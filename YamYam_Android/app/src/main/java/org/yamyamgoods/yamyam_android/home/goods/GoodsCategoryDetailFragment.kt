package org.yamyamgoods.yamyam_android.home.goods


import android.content.Intent
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
import org.yamyamgoods.yamyam_android.network.get.GetCategoryDetailResponse
import org.yamyamgoods.yamyam_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoodsCategoryDetailFragment : Fragment() {
    var dataList: ArrayList<GoodsData> = ArrayList()
    var isRequested = false
    var order: Int = -1
    var lastIndex: Int = -1
    var sort:String? = null
    var categoryIdx:Int = -1
    var sort_flag: String? = null
    companion object {
        var instance : GoodsCategoryDetailFragment = GoodsCategoryDetailFragment()
    }
    val networkServiceGoods = ApplicationController.networkServiceGoods
    lateinit var goodsCategoryDetailRecyclerViewAdapter: GoodsCategoryDetailRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_goods_category_detail, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnScrollChange()
        setOnClickListener()
        setRecyclerView()
        categoryIdx = instance.categoryIdx
        categoryDetailResponse(categoryIdx)


    }

    private fun setOnScrollChange(){
        if(Build.VERSION.SDK_INT >=23){
            nsv_goods_category_detail_frag.setOnScrollChangeListener(object: View.OnScrollChangeListener{
                override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                    if(!isRequested && !nsv_goods_category_detail_frag.canScrollVertically(1)){
                        isRequested = true
                        categoryDetailResponse(categoryIdx)
                    }
                }
            })
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
            categoryIdx = instance.categoryIdx
            setOptionDialog()
        }
        btn_frag_goods_category_detail_minQuantity.setOnClickListener {
            setOptionDialog()
        }
        btn_frag_goods_category_detail_variety.setOnClickListener {
            setOptionDialog()
        }
    }

    fun setSortDialog(){
        var sortDialog = SortDialog(context as HomeActivity)
        sortDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        sortDialog.setCanceledOnTouchOutside(false)
        sortDialog.show()

        sortDialog.setOnDismissListener {
            setSortFlag()
        }
    }

    fun setSortFlag(){
        if(instance.sort_flag==null){
            tv_frag_goods_category_detail_sort.text = "ayay"
        } else {
            tv_frag_goods_category_detail_sort.text = instance.sort_flag
            sort = instance.sort_flag
        }
        //updateDataList()
    }

    fun setOptionDialog(){
        var optionDialog = OptionDialog(context as HomeActivity)
        optionDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        optionDialog.setCanceledOnTouchOutside(false)
        optionDialog.show()

        optionDialog.setOnDismissListener {
            setSortFlag()
        }
    }

    fun categoryDetailResponse(category_idx: Int) {
        val getCategoryDetailResponse = networkServiceGoods.getCategoryDetailResponse(
            "application/json", User.authorization, 5, 0, lastIndex,
            null, null, null, null)
        getCategoryDetailResponse.enqueue(object: Callback<GetCategoryDetailResponse>{
            override fun onFailure(call: Call<GetCategoryDetailResponse>, t: Throwable) {
                Log.e("Category-datil fail", t.toString())
            }

            override fun onResponse(call: Call<GetCategoryDetailResponse>, response: Response<GetCategoryDetailResponse>) {
                if(response.isSuccessful){
                    Log.e("category-detail success", dataList[0].goods_name)
                    if(lastIndex!=-1){
                        var prev_cnt = dataList.size
                        dataList.addAll(response.body()!!.data)
                        goodsCategoryDetailRecyclerViewAdapter.dataList = dataList
                        goodsCategoryDetailRecyclerViewAdapter.notifyItemRangeInserted(prev_cnt, dataList.size-prev_cnt)
                    } else {
                        dataList = response.body()!!.data
                        goodsCategoryDetailRecyclerViewAdapter.dataList = dataList
                        goodsCategoryDetailRecyclerViewAdapter.notifyDataSetChanged()
                        nsv_goods_category_detail_frag.fullScroll(NestedScrollView.FOCUS_UP)

                    }
                    isRequested = false
                    if(dataList.size ==0) lastIndex = -1
                    else lastIndex = dataList[dataList.size-1].goods_idx
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