package org.yamyamgoods.yamyam_android.search.goods

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
import kotlinx.android.synthetic.main.fragment_search_goods.*

import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.GetSearchGoodsResponse
import org.yamyamgoods.yamyam_android.search.SearchActivity
import org.yamyamgoods.yamyam_android.search.adapter.SearchResultGoodsRecyclerViewAdapter
import org.yamyamgoods.yamyam_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultGoodsFragment : Fragment() {
    var dataList: ArrayList<GoodsData> = ArrayList()
    var isRequested = false
    var sort_flag: String? = null
    var sort: String? = null
    var order: Int = -1
    var searchAfter: String? = null
    var goodsName: String = ""
    companion object {
        var instance : SearchResultGoodsFragment = SearchResultGoodsFragment()
    }
    val networkServiceGoods = ApplicationController.networkServiceGoods
    lateinit var searchResultGoodsRecyclerViewAdapter: SearchResultGoodsRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_search_goods, container, false)
        goodsName = instance.goodsName
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSortFlag()
        setOnScrollChange()
        setOnClickListener()
        setRecyclerView()
        searchGoodsResponse(goodsName)
    }

    private fun setOnScrollChange(){
        try{
            if(Build.VERSION.SDK_INT >=23){
                nsv_frag_search_goods.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                    if(!isRequested && !nsv_frag_search_goods.canScrollVertically(1)){
                        isRequested = true
                        searchGoodsResponse(goodsName)
                    }
                }
            }
        } catch(e:Exception){
        }
    }

    private fun setOnClickListener() {
        btn_frag_search_goods_sort.setOnClickListener {
            try{
                instance.sort = sort
                setSortDialog()
            } catch(e:Exception){
            }
        }
    }

    fun setSortDialog(){
        var sortDialog = SortSearchGoodsDialog(context as SearchActivity)
        sortDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        sortDialog.setCanceledOnTouchOutside(false)
        sortDialog.show()

        sortDialog.setOnDismissListener {
            searchAfter = null
            setSortFlag()
            setRecyclerView()
            searchGoodsResponse(goodsName)
        }
    }

    fun setSortFlag(){
        if(instance.sort_flag==null){
            tv_frag_search_goods_sort.text = "인기순"
        } else {
            tv_frag_search_goods_sort.text = instance.sort_flag
            sort = instance.sort_flag
        }
        when (sort) {
            "인기순" -> order=0
            "고가순" -> order=1
            "저가순" -> order=2
        }
    }


    private fun searchGoodsResponse(goodsName:String){
        val getSearchGoodsResponse = networkServiceGoods.getSearchGoodsResponse("application/json", User.authorization,goodsName,order,searchAfter)
        Log.e("**SRGF", " goodsName: $goodsName, order : $order, searchAfter : $searchAfter")
        getSearchGoodsResponse.enqueue(object: Callback<GetSearchGoodsResponse>{
            override fun onFailure(call: Call<GetSearchGoodsResponse>, t: Throwable) {
                Log.e("SearchGoods Fail", t.toString())
            }

            override fun onResponse(call: Call<GetSearchGoodsResponse>, response: Response<GetSearchGoodsResponse>) {
                if(response.isSuccessful) {
                    if(searchAfter != null){
                        var prev_cnt = dataList.size
                        dataList.addAll(response.body()!!.data.goods)
                        searchResultGoodsRecyclerViewAdapter.dataList = dataList
                        searchResultGoodsRecyclerViewAdapter.notifyItemRangeInserted(prev_cnt,dataList.size - prev_cnt)
                    } else{
                        dataList = response.body()!!.data.goods
                        if(response.body()!!.data.totalCnt==0){
                            rl_frag_search_goods_null.visibility = View.VISIBLE
                        } else{
                            rl_frag_search_goods_null.visibility = View.INVISIBLE
                            tv_search_goods_frag_resultNum.text = response.body()!!.data.totalCnt.toString()+"건"
                            searchResultGoodsRecyclerViewAdapter.dataList = dataList
                            searchResultGoodsRecyclerViewAdapter.notifyDataSetChanged()
                            nsv_frag_search_goods.fullScroll(NestedScrollView.FOCUS_UP)
                        }
                    }
                    dataList = response.body()!!.data.goods
                    if (dataList.size == 0) searchAfter = null
                    else searchAfter = dataList[dataList.size - 1].search_after

                }
            }
        })

    }

    private fun setRecyclerView(){
        searchResultGoodsRecyclerViewAdapter = SearchResultGoodsRecyclerViewAdapter(activity!!, dataList)
        rv_search_goods_frag_list.adapter = searchResultGoodsRecyclerViewAdapter
        rv_search_goods_frag_list.layoutManager = GridLayoutManager(activity!!,2)
    }
}
