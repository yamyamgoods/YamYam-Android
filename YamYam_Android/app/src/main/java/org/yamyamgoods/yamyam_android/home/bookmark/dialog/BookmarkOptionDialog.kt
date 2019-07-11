package org.yamyamgoods.yamyam_android.home.bookmark.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_bookmark_estimate_check.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.BookmarkItemOption
import org.yamyamgoods.yamyam_android.network.get.GetBookmarkItemOptionResponseData
import org.yamyamgoods.yamyam_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By Yun Hyeok
 * on 7월 06, 2019
 */

class BookmarkOptionDialog(private val ctx: Context, private val bookmarkIdx: Int) : Dialog(ctx) {

    private val goodsRepository = ApplicationController.networkServiceGoods
    private val userToken = User.authorization

    private lateinit var optionsRVAdapter: BookmarkOptionsRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_bookmark_estimate_check)
        getServerData()
        viewInit()
    }

    private fun viewInit() {
        window!!.setBackgroundDrawableResource(R.color.transparent)
        window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun setOptionData(data: BookmarkItemOption) {
        optionsRVAdapter = BookmarkOptionsRVAdapter(ctx, data)
        rv_bookmark_option_dialog_option_list.apply {
            adapter = optionsRVAdapter
            layoutManager = LinearLayoutManager(ctx)
        }
    }

    private fun getServerData() {
        goodsRepository.getBookmarkItemOptionRequest(token = userToken, goodsScrapId = bookmarkIdx).enqueue(object :
            Callback<GetBookmarkItemOptionResponseData> {
            override fun onFailure(call: Call<GetBookmarkItemOptionResponseData>, t: Throwable) {
                Log.v("Malibin Debug", "t : ${t.message}, stack : ${TextUtils.join("\n", t.stackTrace)}")
            }

            override fun onResponse(
                call: Call<GetBookmarkItemOptionResponseData>,
                response: Response<GetBookmarkItemOptionResponseData>
            ) {
                if (response.isSuccessful) {
                    setOptionData(response.body()!!.data)
                    return
                }
            }
        })
    }


}