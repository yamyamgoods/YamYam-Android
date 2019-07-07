package org.yamyamgoods.yamyam_android.review.photo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_review_photo.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.review.photo.adapter.ReviewPhotoRVAdapter
import org.yamyamgoods.yamyam_android.util.TempData

class ReviewPhotoFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_review_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewInit()
    }

    private fun viewInit() {
        val ctx = activity!!.applicationContext
        rv_review_photo_list.apply {
            adapter = ReviewPhotoRVAdapter(ctx, TempData.ReviewAll())
            layoutManager = LinearLayoutManager(ctx)
        }
    }
}