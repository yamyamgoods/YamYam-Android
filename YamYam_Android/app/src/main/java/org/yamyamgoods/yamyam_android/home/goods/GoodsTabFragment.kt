package org.yamyamgoods.yamyam_android.home.goods

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_goods.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.goods.adapter.GoodsCategoryRecyclerViewAdapter
import org.yamyamgoods.yamyam_android.home.HomeActivity
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.GetGoodsTabResponse
import org.yamyamgoods.yamyam_android.network.get.GoodsCategoryData
import org.yamyamgoods.yamyam_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class GoodsTabFragment : Fragment(){
    var dataList: ArrayList<GoodsCategoryData> = ArrayList()
    lateinit var goodsCategoryRecyclerViewAdapter: GoodsCategoryRecyclerViewAdapter
    val networkServiceGoods = ApplicationController.networkServiceGoods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View =  inflater.inflate(R.layout.fragment_goods, container, false)
        goodsTabResponse()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Goods탭 처음에 기획전 화면을 띄우도록
        val transaction: FragmentTransaction = (context as HomeActivity).supportFragmentManager.beginTransaction()
        //transaction.add(R.id.fl_goods_fragment_frag, GoodsExhibitionFragment()).commit()
    }

    private fun goodsTabResponse(){
        val getGoodsTabResponse = networkServiceGoods.getGoodsTabResponse("application/json", User.authorization)
        getGoodsTabResponse.enqueue(object: Callback<GetGoodsTabResponse> {
            override fun onFailure(call: Call<GetGoodsTabResponse>, t:Throwable) {
                Log.e("GoodsTab-Category fail", t.toString())
            }

            override fun onResponse(call: Call<GetGoodsTabResponse>, response: Response<GetGoodsTabResponse>) {
                if(response.isSuccessful){
                    try{
                        dataList = response.body()!!.data.goods_category_data
                        setRecyclerView()
                    } catch (e:Exception){
                    }
                }
            }
        })
    }

    private fun setRecyclerView(){
        goodsCategoryRecyclerViewAdapter = GoodsCategoryRecyclerViewAdapter(context!!,dataList)
        rv_goods_frag_category.adapter = goodsCategoryRecyclerViewAdapter
        rv_goods_frag_category.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL,false) as RecyclerView.LayoutManager?
    }

}