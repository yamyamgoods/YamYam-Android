package org.yamyamgoods.yamyam_android.home.best.review

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_best_review.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.best.review.adapter.BestReviewRVAdapter
import org.yamyamgoods.yamyam_android.util.TempData

class BestReviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_best_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewInit()
    }

    private fun viewInit() {
        val ctx = activity!!.applicationContext

        rv_best_review_list.apply{
            adapter = BestReviewRVAdapter(ctx, TempData.BestReview())
            layoutManager = LinearLayoutManager(ctx)
        }
    }
}