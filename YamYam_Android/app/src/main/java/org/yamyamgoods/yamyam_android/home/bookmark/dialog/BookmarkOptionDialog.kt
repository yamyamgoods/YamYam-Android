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
import org.yamyamgoods.yamyam_android.dataclass.BookmarkData
import org.yamyamgoods.yamyam_android.dataclass.ProductOptionDetail
import org.yamyamgoods.yamyam_android.dataclass.SelectedOption
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.BookmarkItemOption
import org.yamyamgoods.yamyam_android.network.get.GetBookmarkItemOptionResponseData
import org.yamyamgoods.yamyam_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

/**
 * Created By Yun Hyeok
 * on 7월 06, 2019
 */

class BookmarkOptionDialog(private val ctx: Context, private val bookmarkIdx: Int) : Dialog(ctx) {

    var totalPrice = -1
    lateinit var bookmarkData: BookmarkData

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
        val amount = getAmountData(data.goods_scrap_option_data)
        et_bookmark_option_dialog_amount.setText(amount.toString())
        et_bookmark_option_dialog_tag.setText(bookmarkData.goods_scrap_label)
    }

    private fun bindViewServerData() {
        tv_bookmark_option_dialog_total_price.text = toNumberFormat(totalPrice)
        tv_bookmark_option_dialog_main_name.text = bookmarkData.getStoreGoodsName()

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
                    bindViewServerData()
                    return
                }
            }
        })
    }

    private fun toNumberFormat(price: Int): String = NumberFormat.getNumberInstance(Locale.US).format(price)

    private fun getAmountData(data: List<SelectedOption>): Int {
        for (option in data) {
            if (option.optionName == "수량") {
                return option.optionValue.replace(",", "").toInt()
            }
        }
        return -1
    }


}