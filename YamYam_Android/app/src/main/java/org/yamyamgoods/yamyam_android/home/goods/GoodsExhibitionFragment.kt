package org.yamyamgoods.yamyam_android.home.goods


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_goods_exhibition.*

import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.goods.adapter.GoodsExhibitionRecyclerViewAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.ExhibitionData
import org.yamyamgoods.yamyam_android.network.get.GetGoodsTabResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoodsExhibitionFragment : Fragment() {

    var dataList : ArrayList<ExhibitionData> = ArrayList()
    lateinit var goodsExhibitionRecyclerViewAdapter: GoodsExhibitionRecyclerViewAdapter
    val networkServiceGoods = ApplicationController.networkServiceGoods

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_goods_exhibition, container, false)
        goodsExhibitionResponse()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun setRecyclerView(){
        goodsExhibitionRecyclerViewAdapter = GoodsExhibitionRecyclerViewAdapter(dataList)
        rv_frag_goods_exhibition_list.adapter = goodsExhibitionRecyclerViewAdapter
        rv_frag_goods_exhibition_list.layoutManager = LinearLayoutManager(context,LinearLayout.VERTICAL,false)
    }

    private fun goodsExhibitionResponse(){
        val getGoodsTabResponse = networkServiceGoods.getGoodsTabResponse("application/json", org.yamyamgoods.yamyam_android.util.User.authorization)
        getGoodsTabResponse.enqueue(object: Callback<GetGoodsTabResponse> {
            override fun onFailure(call: Call<GetGoodsTabResponse>, t:Throwable) {
                Log.e("GoodsTab-Exhibi fail", t.toString())
            }

            override fun onResponse(call: Call<GetGoodsTabResponse>, response: Response<GetGoodsTabResponse>) {
                if(response.isSuccessful){
                    dataList = response.body()!!.data.exhibition_data
                    setRecyclerView()
                }
            }
        })
    }
}
