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
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_best_review.*
import kotlinx.android.synthetic.main.rv_item_best_review.*
import org.jetbrains.anko.support.v4.ctx
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.ReviewData
import org.yamyamgoods.yamyam_android.home.best.review.adapter.BestReviewRVAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.NetworkServiceGoods
import org.yamyamgoods.yamyam_android.network.delete.DeleteReviewLikeResponseData
import org.yamyamgoods.yamyam_android.network.get.GetBestReviewResponse
import org.yamyamgoods.yamyam_android.network.get.GetReviewResponse
import org.yamyamgoods.yamyam_android.network.post.PostReviewLikeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class BestReviewFragment : Fragment() {

    var dataList: ArrayList<ReviewData> = ArrayList()

    lateinit var bestReviewRVAdapter: BestReviewRVAdapter

    val token: String =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJpYXQiOjE1NjIzMTUzNjYsImV4cCI6MTU2MzYyOTM2Nn0.ZkDGasoDPHTrGvy7yFOT9cPjTQ7gnnUOqekY_zYrAuc"

    val networkService: NetworkServiceGoods by lazy {
        ApplicationController.networkServiceGoods
    }

    //var likeFlag: Boolean = true   //좋아요

    companion object {
        var instance: BestReviewFragment = BestReviewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_best_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBestReviewResponse()
    }

    // 베스트 리뷰 보기 서버 통신
    fun getBestReviewResponse() {
        networkService.getBestReviewItemRequest("application/json", token, -1)
            .enqueue(object : Callback<GetBestReviewResponse> {
                override fun onFailure(call: Call<GetBestReviewResponse>, t: Throwable) {
                    Log.e("BestReviewFragment", t.toString())
                }

                override fun onResponse(call: Call<GetBestReviewResponse>, response: Response<GetBestReviewResponse>) {
                    if (response.isSuccessful) {
                        Log.v("BestReviewFragment", "통신 성공")
                        response.body()?.let {
                            dataList = response.body()!!.data!!
                            rv_best_review_list.apply {
                                adapter = BestReviewRVAdapter(activity!!, dataList, this@BestReviewFragment)
                                layoutManager = LinearLayoutManager(activity!!)
                            }
                            bestReviewRVAdapter = BestReviewRVAdapter(activity!!, dataList, this@BestReviewFragment)
                            bestReviewRVAdapter.notifyDataSetChanged()
                        }
                    }
                }
            })
    }

    // 리뷰 좋아요
    fun postReviewLike(reviewIdx: Int) {
        networkService.postReviewLikeRequest("application/json", token, PostReviewLikeData(reviewIdx))
            .enqueue(object : Callback<PostReviewLikeData> {

                override fun onFailure(call: Call<PostReviewLikeData>, t: Throwable) {
                    Log.e("BestReviewFragment", t.toString())
                }

                override fun onResponse(call: Call<PostReviewLikeData>, response: Response<PostReviewLikeData>) {
                    Log.v("BestReviewFragment", "리뷰 좋아요 서버 통신 성공  response : ${response.body()}")
                    if (response.isSuccessful) {
                        Log.v("현주", "리뷰 좋아요 통신 성공: "+ reviewIdx.toString())
                    }
                }
            })
    }

    // 리뷰 좋아요 취소
    fun deleteReviewLike(reviewIdx: Int) {
        networkService.deleteReviewLikeRequest("application/json", token, reviewIdx)
            .enqueue(object: Callback<DeleteReviewLikeResponseData>{
                override fun onFailure(call: Call<DeleteReviewLikeResponseData>, t: Throwable) {
                    Log.e("BestReviewFragment", t.toString())
                }

                override fun onResponse(
                    call: Call<DeleteReviewLikeResponseData>,
                    response: Response<DeleteReviewLikeResponseData>){
                  if (response.isSuccessful){
                      Log.v("현주", "리뷰 좋아요 취소 통신 성공: "+ reviewIdx.toString())
                  }
                }
            })
    }
}