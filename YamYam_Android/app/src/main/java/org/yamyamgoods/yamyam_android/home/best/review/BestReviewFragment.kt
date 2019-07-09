package org.yamyamgoods.yamyam_android.home.best.review

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_review_detail.*
import kotlinx.android.synthetic.main.fragment_best_review.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.best.review.adapter.BestReviewRVAdapter
import org.yamyamgoods.yamyam_android.util.TempData

class BestReviewFragment : Fragment() {

    var dataList:ArrayList<BestReviewItem> = ArrayList()
    lateinit var bestReviewRVAdapter: BestReviewRVAdapter
 //   lateinit var REQUEST_CODE_REVIEW_DETAIL_ACTIVITY: Int
    var flag:Int = -1
    companion object{
        var instance:BestReviewFragment = BestReviewFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_best_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewInit()
    }

    private fun viewInit() {
       // val ctx = activity!!.applicationContext
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

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
       // if (resultCode == REQUEST_CODE_REVIEW_DETAIL_ACTIVITY){
//        if(instance.flag==1)
//            when (requestCode){
//                3000 -> {
//                    //iv_review_detail_review_thumbs.isSelected  = !(iv_review_detail_review_thumbs.isSelected)
//                    //review_like_flag = Math.abs(review_like_flag - 1)
//                    dataList[0].review_like_flag = 1
//                }
//            }
//        }
        if(instance.flag==1)
            dataList[0].thumbFlag = 1
        bestReviewRVAdapter.dataList = dataList
        bestReviewRVAdapter.notifyDataSetChanged()
    }
    fun startReviewDetailActivity(){
        Log.v("guswn", "ddd")
    }
}