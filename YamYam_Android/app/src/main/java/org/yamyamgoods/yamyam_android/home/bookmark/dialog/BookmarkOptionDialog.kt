package org.yamyamgoods.yamyam_android.home.bookmark.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_bookmark_estimate_check.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.BookmarkData
import org.yamyamgoods.yamyam_android.dataclass.ProductOptionDetail
import org.yamyamgoods.yamyam_android.dataclass.SelectedOption
import org.yamyamgoods.yamyam_android.home.bookmark.dto.Bookmark2ProductDetailDTO
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.get.BookmarkItemOption
import org.yamyamgoods.yamyam_android.network.get.GetBookmarkItemOptionResponseData
import org.yamyamgoods.yamyam_android.network.put.PutBookmarkModifyRequestDTO
import org.yamyamgoods.yamyam_android.network.put.PutBookmarkModifyResponseData
import org.yamyamgoods.yamyam_android.productdetail.ProductDetailActivity
import org.yamyamgoods.yamyam_android.util.HomeObject
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
    var oneTotalPrice = -1
    var productQuantity = 1
    var selectedOptions = ArrayList<SelectedOption>()

    var currentAmountOption: SelectedOption? = null

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

        et_bookmark_option_dialog_amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.isEmpty()) {
                    productQuantity = 0
                    setAmountOption("0")
                    notifyTotalPrice()
                    return
                }
                productQuantity = s.toString().toInt()
                setAmountOption(s.toString())
                notifyTotalPrice()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        btn_bookmark_option_dialog_save.setOnClickListener {
            scrapModifyRequest(getPutBookmarkModifyRequestDTO())
        }

        btn_bookmark_option_dialog_close.setOnClickListener {
            dismiss()
        }

        btn_bookmark_option_dialog_go_detail.setOnClickListener {
            val dto = Bookmark2ProductDetailDTO(et_bookmark_option_dialog_tag.text.toString(), selectedOptions)
            ctx.startActivity<ProductDetailActivity>("goodsIdx" to bookmarkData.goods_idx, "dto" to dto)
            dismiss()
        }
    }

    private fun setOptionData(data: BookmarkItemOption) {
        val amount = getAmountData(data.goods_scrap_option_data)
        currentAmountOption = SelectedOption("수량", amount.toString())
        et_bookmark_option_dialog_amount.setText(amount.toString())
        et_bookmark_option_dialog_tag.setText(bookmarkData.goods_scrap_label)
        selectedOptions.addAll(data.goods_scrap_option_data)

        optionsRVAdapter = BookmarkOptionsRVAdapter(ctx, data, this).apply {
            this.totalPrice = this@BookmarkOptionDialog.totalPrice / amount
        }
        rv_bookmark_option_dialog_option_list.apply {
            adapter = optionsRVAdapter
            layoutManager = LinearLayoutManager(ctx)
        }
    }

    private fun setAmountOption(amount: String) {

        for (option in selectedOptions) {
            if (option.optionName == "수량") {
                option.optionValue = amount
            }
        }
    }

    private fun bindViewServerData() {
        tv_bookmark_option_dialog_total_price.text = toNumberFormat(totalPrice)
        tv_bookmark_option_dialog_main_name.text = bookmarkData.getStoreGoodsName()
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

    private fun getPutBookmarkModifyRequestDTO() = PutBookmarkModifyRequestDTO(
        bookmarkIdx,
        bookmarkData.goods_idx,
        oneTotalPrice * productQuantity,
        et_bookmark_option_dialog_tag.text.toString(),
        selectedOptions
    )

    private fun getServerData() {
        goodsRepository.getBookmarkItemOptionRequest(token = userToken, goodsScrapId = bookmarkIdx).enqueue(object :
            Callback<GetBookmarkItemOptionResponseData> {
            override fun onFailure(call: Call<GetBookmarkItemOptionResponseData>, t: Throwable) {
                ctx.toast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.")
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

    private fun scrapModifyRequest(body: PutBookmarkModifyRequestDTO) {
        goodsRepository.putBookmarkModifyRequest(token = userToken, body = body)
            .enqueue(object : Callback<PutBookmarkModifyResponseData> {
                override fun onFailure(call: Call<PutBookmarkModifyResponseData>, t: Throwable) {
                    ctx.toast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.")
                    Log.v("Malibin Debug", "t : ${t.message}, stack : ${TextUtils.join("\n", t.stackTrace)}")
                }

                override fun onResponse(
                    call: Call<PutBookmarkModifyResponseData>,
                    response: Response<PutBookmarkModifyResponseData>
                ) {
                    if (response.isSuccessful) {
                        ctx.toast("견적 수정이 완료되었습니다!")
                        HomeObject.notifyBookmarkTabChange()
                    }
                }
            })
    }

    fun refreshOptionData(totalPrice: Int, selectedOptions: ArrayList<SelectedOption>) {
        this.oneTotalPrice = totalPrice
        this.selectedOptions = selectedOptions
    }

    fun notifyTotalPrice() {
        val totalPrice = toNumberFormat(oneTotalPrice * productQuantity)
        tv_bookmark_option_dialog_total_price.text = totalPrice
    }


}
