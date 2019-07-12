package org.yamyamgoods.yamyam_android.storedetail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_goods_category_detail.*
import kotlinx.android.synthetic.main.fragment_store_detail_goods.*

import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.GetStoreDetailGoodsResponse
import org.yamyamgoods.yamyam_android.storedetail.adapter.StoreDetailRVAdapter
import org.yamyamgoods.yamyam_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreDetailGoodsFragment : Fragment() {

    var dataList: ArrayList<GoodsData> = ArrayList()
    var sort_flag: String? = null
    var sort: String? = null
    var order: Int = 0
    val networkServiceStore = ApplicationController.networkServiceStore
    lateinit var storeDetailRVAdapter: StoreDetailRVAdapter
    var s_idx: Int = -1
    companion object {
        var instance : StoreDetailGoodsFragment = StoreDetailGoodsFragment()
    }
    var goodsCategoryIdx:Int? = null
    var firstFlag:Int? = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View =  inflater.inflate(R.layout.fragment_store_detail_goods, container, false)
        s_idx = instance.s_idx
        Log.e("**SDGF","s_idx : $s_idx")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setSortFlag()
        setOnClickListener()
        setRecyclerView()
        storeDetailGoodsResponse(s_idx)
    }

    private fun setOnClickListener() {
        btn_frag_store_detail_goods_sort.setOnClickListener {
            try{
                instance.sort = sort
                setSortDialog()
            } catch(e:Exception){
            }
        }
        btn_frag_store_detail_goods_category.setOnClickListener {
            try{
                instance.s_idx = s_idx
                setCategoryDialog()
            } catch (e:Exception) {
            }
        }
    }

    fun setSortDialog(){
        var sortDialog = SortStoreDetailGoodsDialog(context as StoreDetailActivity)
        sortDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        sortDialog.setCanceledOnTouchOutside(false)
        sortDialog.show()

        sortDialog.setOnDismissListener {
            firstFlag = 0
            setSortFlag()
            setRecyclerView()
            storeDetailGoodsResponse(s_idx)
        }
    }

    fun setCategoryDialog(){
        var optionDialog = CategoryStoreDetailGoodsDialog(context as StoreDetailActivity)
        optionDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        optionDialog.setCanceledOnTouchOutside(false)
        optionDialog.show()

        optionDialog.setOnDismissListener {
            firstFlag = 0
            setCategoryFlag()
            setRecyclerView()
            storeDetailGoodsResponse(s_idx)
        }
    }
    fun setSortFlag(){
        if(instance.sort_flag==null){
            tv_frag_store_detail_goods_sort.text = "인기순"
        } else {
            tv_frag_store_detail_goods_sort.text = instance.sort_flag
            sort = instance.sort_flag
        }
        when (sort) {
            "인기순" -> order=0
            "고가순" -> order=1
            "저가순" -> order=2
        }
    }

    fun setCategoryFlag() {
        goodsCategoryIdx = instance.goodsCategoryIdx
    }

    private fun storeDetailGoodsResponse(s_idx:Int) {
        val getStoreDetailGoodsResponse = networkServiceStore.getStoreDetailGoodsResponse(
            "application/json", User.authorization,s_idx,order,-1,goodsCategoryIdx,firstFlag)
        Log.e("**",s_idx.toString()+"/"+order.toString()+"/"+goodsCategoryIdx+"/"+firstFlag)
        getStoreDetailGoodsResponse.enqueue(object: Callback<GetStoreDetailGoodsResponse> {
            override fun onFailure(call: Call<GetStoreDetailGoodsResponse>, t: Throwable) {
                Log.e("StoreDetailGoods Fail",t.toString())
            }

            override fun onResponse(call: Call<GetStoreDetailGoodsResponse>, response: Response<GetStoreDetailGoodsResponse>) {
                if(response.isSuccessful){
                    Log.e("**SDGF","RESPONSE")
                    dataList = response.body()!!.data
                    setRecyclerView()
                }
            }
        })
    }

    private fun setRecyclerView() {
        storeDetailRVAdapter = StoreDetailRVAdapter(context!!,dataList)
        rv_frag_store_detail_list.adapter = storeDetailRVAdapter
        rv_frag_store_detail_list.layoutManager = GridLayoutManager(context!!,2)
    }
}
