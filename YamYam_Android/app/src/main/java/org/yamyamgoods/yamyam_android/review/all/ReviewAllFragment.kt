package org.yamyamgoods.yamyam_android.review.all

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_review_all.*
import org.jetbrains.anko.support.v4.ctx
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.ReviewData
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.NetworkServiceGoods
import org.yamyamgoods.yamyam_android.network.get.GetReviewResponse
import org.yamyamgoods.yamyam_android.review.all.adapter.ReviewAllRVAdapter
import org.yamyamgoods.yamyam_android.util.TempData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewAllFragment :Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_review_all, container, false)
    }

    var dataList: ArrayList<ReviewData> = ArrayList()

    val token: String =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJpYXQiOjE1NjIzMTUzNjYsImV4cCI6MTU2MzYyOTM2Nn0.ZkDGasoDPHTrGvy7yFOT9cPjTQ7gnnUOqekY_zYrAuc"

    val networkService: NetworkServiceGoods by lazy {
        ApplicationController.networkServiceGoods
    }
    lateinit var reviewAllRVAdapter : ReviewAllRVAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var goodsIndex: Int = activity!!.intent.getIntExtra("goodsIdx", -1)

        getReviewResponse(goodsIndex, -1)
        setRecyclerView()
    }

    private fun getReviewResponse(goodsIndex: Int, lastIndex: Int){
        networkService.getReviewResponse("applitcation/json", token,
            goodsIndex, -1, lastIndex)
            .enqueue(object: Callback<GetReviewResponse>{
                override fun onFailure(call: Call<GetReviewResponse>, t: Throwable) {
                    Log.e("ReviewAllFragment", t.toString())
                }

                override fun onResponse(call: Call<GetReviewResponse>, response: Response<GetReviewResponse>) {
                    if (response.isSuccessful){
                        Log.v("ReviewAllFragment", "통신 성공")
                        response.body()?.let{
                            dataList = response.body()!!.data!!
                            reviewAllRVAdapter = ReviewAllRVAdapter(activity!!, dataList)

                        }
                    }
                }
            })
    }

    private fun setRecyclerView(){
        var ctx = activity!!.applicationContext
        rv_review_all_list.apply{
            adapter = ReviewAllRVAdapter(ctx, dataList)
            layoutManager = LinearLayoutManager(ctx)
        }
        reviewAllRVAdapter.notifyDataSetChanged()
    }
}