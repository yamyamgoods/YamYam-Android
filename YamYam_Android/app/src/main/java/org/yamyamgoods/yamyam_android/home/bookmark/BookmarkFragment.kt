package org.yamyamgoods.yamyam_android.home.bookmark

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_bookmark.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.bookmark.adapter.BookmarkRVAdapter
import org.yamyamgoods.yamyam_android.util.TempData

/**
 * Created By Yun Hyeok
 * on 7월 06, 2019
 */

class BookmarkFragment : Fragment() {

    companion object {
        private var instance: BookmarkFragment? = null

        @JvmStatic
        fun getInstance() = instance
            ?: BookmarkFragment().apply { instance = this }

    }

    private lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ctx = activity!!
        viewInit()
    }

    private fun viewInit() {

        rv_bookmark_frag_list.apply {
            adapter = BookmarkRVAdapter(ctx, TempData.bookmarks())
            layoutManager = GridLayoutManager(ctx, 2)
        }
    }
}