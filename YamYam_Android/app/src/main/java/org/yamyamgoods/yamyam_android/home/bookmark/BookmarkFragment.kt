package org.yamyamgoods.yamyam_android.home.bookmark

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_bookmark.*
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.BookmarkData
import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.home.bookmark.adapter.BookmarkRVAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.util.TempData
import org.yamyamgoods.yamyam_android.util.User

/**
 * Created By Yun Hyeok
 * on 7월 06, 2019
 */

class BookmarkFragment : Fragment(), BookmarkContract.View {

    override lateinit var presenter: BookmarkContract.Presenter

    private lateinit var bookmarkRVAdapter: BookmarkRVAdapter
    private lateinit var ctx: Context

    companion object {
        private var instance: BookmarkFragment? = null

        @JvmStatic
        fun getInstance() = instance
            ?: BookmarkFragment().apply { instance = this }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ctx = activity!!
        presenterInit()
        viewInit()
        getServerData()
    }

    override fun showServerFailToast(message: String, t: Throwable) {
        activity!!.toast(message)
        Log.v("Malibin Debug", "t : ${t.message}, stack : ${TextUtils.join("\n", t.stackTrace)}")
    }

    override fun addBookmarkData(data: List<BookmarkData>) {
        if (data.isEmpty()) {
            rv_bookmark_frag_list.visibility = View.GONE
            cl_bookmark_frag_no_list.visibility = View.VISIBLE
            return
        }
        rv_bookmark_frag_list.visibility = View.VISIBLE
        cl_bookmark_frag_no_list.visibility = View.GONE
        bookmarkRVAdapter.addData(data)
        bookmarkRVAdapter.notifyDataSetChanged()
    }

    private fun presenterInit() {
        presenter = BookmarkPresenter().apply {
            userToken = User.authorization
            goodsRepository = ApplicationController.networkServiceGoods
            view = this@BookmarkFragment
        }
    }

    private fun viewInit() {
        bookmarkRVAdapter = BookmarkRVAdapter(ctx)
        rv_bookmark_frag_list.apply {
            adapter = bookmarkRVAdapter
            layoutManager = GridLayoutManager(ctx, 2)
        }
    }

    private fun getServerData() {
        presenter.getBookmarkData()
    }

    fun refreshDataList(){
        val size = bookmarkRVAdapter.itemCount
        bookmarkRVAdapter.dataList.clear()
        bookmarkRVAdapter.notifyItemRangeRemoved(0,size)
        presenter.getBookmarkData()
    }
}