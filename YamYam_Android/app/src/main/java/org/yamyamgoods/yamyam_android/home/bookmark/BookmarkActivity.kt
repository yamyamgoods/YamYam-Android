package org.yamyamgoods.yamyam_android.home.bookmark

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_bookmark.*
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.BookmarkData
import org.yamyamgoods.yamyam_android.home.bookmark.adapter.BookmarkRVAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.util.HomeObject
import org.yamyamgoods.yamyam_android.util.User


/**
 * Created By Yun Hyeok
 * on 7월 12, 2019
 */

class BookmarkActivity : AppCompatActivity(), BookmarkContract.View {

    override lateinit var presenter: BookmarkContract.Presenter

    private lateinit var bookmarkRVAdapter: BookmarkRVAdapter

    companion object {
        private var instance: BookmarkFragment? = null

        @JvmStatic
        fun getInstance() = instance
            ?: BookmarkFragment().apply { instance = this }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        presenterInit()
        viewInit()
        getServerData()
    }


    override fun showServerFailToast(message: String, t: Throwable) {
        toast(message)
        Log.v("Malibin Debug", "t : ${t.message}, stack : ${TextUtils.join("\n", t.stackTrace)}")
    }

    override fun addBookmarkData(data: List<BookmarkData>) {
        if (data.isEmpty()) {
            rv_bookmark_act_list.visibility = View.GONE
            cl_bookmark_act_no_list.visibility = View.VISIBLE
            return
        }
        rv_bookmark_act_list.visibility = View.VISIBLE
        cl_bookmark_act_no_list.visibility = View.GONE
        bookmarkRVAdapter.addData(data)
        bookmarkRVAdapter.notifyDataSetChanged()
    }

    override fun deleteBookmarkData(bookmarkData: BookmarkData) {
        bookmarkRVAdapter.deleteBookmark(bookmarkData)
        toast("찜한 견적이 삭제되었습니다!")
        HomeObject.notifyBookmarkTabChange()
    }

    private fun presenterInit() {
        presenter = BookmarkPresenter().apply {
            userToken = User.authorization
            goodsRepository = ApplicationController.networkServiceGoods
            view = this@BookmarkActivity
        }
    }

    private fun viewInit() {
        bookmarkRVAdapter = BookmarkRVAdapter(this, presenter)
        rv_bookmark_act_list.apply {
            adapter = bookmarkRVAdapter
            layoutManager = GridLayoutManager(this@BookmarkActivity, 2)
        }
    }

    private fun getServerData() {
        presenter.getBookmarkData()
    }

    fun showNoItemView(){
        rv_bookmark_act_list.visibility = View.GONE
        cl_bookmark_act_no_list.visibility = View.VISIBLE
    }
}
