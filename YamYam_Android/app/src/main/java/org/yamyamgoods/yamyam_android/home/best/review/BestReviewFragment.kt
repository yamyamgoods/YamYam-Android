package org.yamyamgoods.yamyam_android.home.best.review

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_best_review.*
import org.jetbrains.anko.support.v4.ctx
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.ReviewData
import org.yamyamgoods.yamyam_android.home.best.review.adapter.BestReviewRVAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.NetworkServiceGoods
import org.yamyamgoods.yamyam_android.network.get.GetReviewResponse
import org.yamyamgoods.yamyam_android.util.TempData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BestReviewFragment : Fragment() {

    var dataList:ArrayList<ReviewData> = ArrayList()

    lateinit var bestReviewRVAdapter: BestReviewRVAdapter

    val token: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJpYXQiOjE1NjIzMTUzNjYsImV4cCI6MTU2MzYyOTM2Nn0.ZkDGasoDPHTrGvy7yFOT9cPjTQ7gnnUOqekY_zYrAuc"

    val networkService: NetworkServiceGoods by lazy{
        ApplicationController.networkServiceGoods
    }

    var flag:Int = -1
    companion object{
        var instance:BestReviewFragment = BestReviewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_best_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBestReviewResponse()
    }

    /*private fun viewInit() {
       // val ctx = activity!!.applicationContext
        var tmp: ArrayList<ReviewData> = response.body()!!.data!!
        dataList = TempData.BestReview()
        bestReviewRVAdapter = BestReviewRVAdapter(context!!,dataList)
        rv_best_review_list.apply{
            adapter = bestReviewRVAdapter
            layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)
        }
        if(instance.flag==1)
            dataList[0].thumbFlag = 1
        bestReviewRVAdapter.dataList = dataList
        bestReviewRVAdapter.notifyDataSetChanged()
    }*/

    // 베스트 리뷰 보기 서버 통신
    private fun getBestReviewResponse(){
        networkService.getBestReviewItemRequest("application/json", token, -1)
            .enqueue(object: Callback<GetReviewResponse>{
                override fun onFailure(call: Call<GetReviewResponse>, t: Throwable) {
                    Log.e("BestReviewFragment", t.toString())
                }

                override fun onResponse(call: Call<GetReviewResponse>, response: Response<GetReviewResponse>) {
                    if (response.isSuccessful){
                        Log.v("현주", "통신 성공")
                        response.body()?.let{
                            dataList = response.body()!!.data!!
                            rv_best_review_list.apply{
                                adapter = BestReviewRVAdapter(ctx, dataList)
                                layoutManager = LinearLayoutManager(ctx)
                            }
                            bestReviewRVAdapter = BestReviewRVAdapter(ctx, dataList)
                        }
                    }
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(instance.flag==1)
            dataList[0].review_like_flag = 1
        bestReviewRVAdapter.dataList = dataList
        bestReviewRVAdapter.notifyDataSetChanged()
    }

}