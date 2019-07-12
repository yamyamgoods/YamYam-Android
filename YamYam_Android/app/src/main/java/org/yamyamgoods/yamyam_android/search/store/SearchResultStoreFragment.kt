package org.yamyamgoods.yamyam_android.search.store

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_search_goods.*
import kotlinx.android.synthetic.main.fragment_search_store.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.GetSearchStoreResponse
import org.yamyamgoods.yamyam_android.network.get.SearchStoreData
import org.yamyamgoods.yamyam_android.search.SearchActivity
import org.yamyamgoods.yamyam_android.search.adapter.SearchResultStoreRecyclerViewAdapter
import org.yamyamgoods.yamyam_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultStoreFragment : Fragment() {

    var dataList: ArrayList<SearchStoreData> = ArrayList()
    var isRequested = false
    var sort_flag: String? = null
    var sort: String? = null
    var order: Int = 0
    var searchAfter: String? = null
    val networkServiceStore = ApplicationController.networkServiceStore
    lateinit var searchResultStoreRecyclerViewAdapter: SearchResultStoreRecyclerViewAdapter
    var storeName: String = ""
    companion object {
        var instance : SearchResultStoreFragment = SearchResultStoreFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_search_store, container, false)
        storeName = instance.storeName
        return view
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSortFlag()
        setOnScrollChange()
        setOnClickListener()
        setRecyclerView()
        searchStoreResponse(storeName)
    }

    private fun setOnScrollChange(){
        try{
            if(Build.VERSION.SDK_INT >=23){
                nsv_frag_search_store.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                    if(!isRequested && !nsv_frag_search_store.canScrollVertically(1)){
                        isRequested = true
                        searchStoreResponse(storeName)
                    }
                }
            }
        } catch(e:Exception){
        }
    }

    private fun setOnClickListener() {
        btn_frag_search_store_sort.setOnClickListener {
            try{
                SearchResultStoreFragment.instance.sort = sort
                setSortDialog()
            } catch(e:Exception){
            }
        }
    }

    fun setSortDialog(){
        var sortDialog = SortSearchStoreDialog(context as SearchActivity)
        sortDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        sortDialog.setCanceledOnTouchOutside(false)
        sortDialog.show()

        sortDialog.setOnDismissListener {
            searchAfter = null
            setSortFlag()
            setRecyclerView()
            searchStoreResponse(storeName)
        }
    }

    fun setSortFlag(){
        if(SearchResultStoreFragment.instance.sort_flag==null){
            tv_frag_search_store_sort.text = "인기순"
        } else {
            tv_frag_search_store_sort.text = SearchResultStoreFragment.instance.sort_flag
            sort = SearchResultStoreFragment.instance.sort_flag
        }
        when (sort) {
            "인기순" -> order=0
            "고가순" -> order=1
            "저가순" -> order=2
        }
    }


    private fun searchStoreResponse(storeName:String){
        val getSearchStoreResponse = networkServiceStore.getSearchStoreResponse("application/json", User.authorization,storeName,order,searchAfter)
        Log.e("**SRGF", " storeName: $storeName, order : $order, searchAfter : $searchAfter")
        getSearchStoreResponse.enqueue(object: Callback<GetSearchStoreResponse> {
            override fun onFailure(call: Call<GetSearchStoreResponse>, t: Throwable) {
                Log.e("SearchStore Fail", t.toString())
            }

            override fun onResponse(call: Call<GetSearchStoreResponse>, response: Response<GetSearchStoreResponse>) {
                if(response.isSuccessful) {
                    if(searchAfter != null){
                        var prev_cnt = dataList.size
                        dataList.addAll(response.body()!!.data.store)
                        searchResultStoreRecyclerViewAdapter.dataList = dataList
                        searchResultStoreRecyclerViewAdapter.notifyItemRangeInserted(prev_cnt,dataList.size - prev_cnt)
                    } else{
                        dataList = response.body()!!.data.store
                        if(response.body()!!.data.totalCnt==0) {
                            rl_frag_search_store_null.visibility = View.VISIBLE
                        } else{
                            rl_frag_search_store_null.visibility = View.INVISIBLE
                            tv_search_store_frag_resultNum.text = response.body()!!.data.totalCnt.toString()+"건"
                            searchResultStoreRecyclerViewAdapter.dataList = dataList
                            searchResultStoreRecyclerViewAdapter.notifyDataSetChanged()
                            nsv_frag_search_store.fullScroll(NestedScrollView.FOCUS_UP)
                        }
                    }
                    dataList = response.body()!!.data.store
                    if (dataList.size == 0) searchAfter = null
                    else searchAfter = dataList[dataList.size - 1].search_after

                }
            }
        })

    }

    private fun setRecyclerView(){
        searchResultStoreRecyclerViewAdapter = SearchResultStoreRecyclerViewAdapter(activity!!, dataList)
        rv_search_store_frag_list.adapter = searchResultStoreRecyclerViewAdapter
        rv_search_store_frag_list.layoutManager = LinearLayoutManager(activity!!, LinearLayout.VERTICAL,false)
    }

}