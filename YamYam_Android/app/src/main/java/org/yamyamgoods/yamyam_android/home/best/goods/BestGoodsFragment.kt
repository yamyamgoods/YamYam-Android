package org.yamyamgoods.yamyam_android.home.best.goods

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_best_goods.*
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.home.best.goods.adapter.BestGoodsRVAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.post.PostBookmarkRequestDTO
import org.yamyamgoods.yamyam_android.productdetail.ProductDetailActivity

class BestGoodsFragment : Fragment(), BestGoodsContract.View {

    override lateinit var presenter: BestGoodsContract.Presenter

    private lateinit var bestGoodsRVAdapter: BestGoodsRVAdapter

    private val bookmarkClickListener = object : BookmarkClickListener {
        override fun onClick(data: GoodsData) {
            val isBookmarked = (data.scrap_flag == 1)
            Log.v("Malibin Debug", "Before server / isBookmarked : $isBookmarked ")
            if (isBookmarked) {
                presenter.bookmarkCancelRequest(data.goods_idx)
                data.scrap_flag = 0
                return
            }
            val intPrice = data.goods_price.replace(",", "").toInt()
            val requestBody =
                PostBookmarkRequestDTO(data.goods_idx, intPrice, data.goods_name, null)
            Log.v("Malibin Debug", "requestBody : $requestBody")
            presenter.bookmarkRequest(requestBody)
            data.scrap_flag = 1
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v("Malibin Debug", "onCreateView called")
        return inflater.inflate(R.layout.fragment_best_goods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenterInit()
        viewInit()
        getBestGoodsData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1001 -> {
                if (resultCode == Activity.RESULT_OK)
                    refreshGoodsData()
            }
        }
    }

    override fun showServerFailToast(message: String, t: Throwable) {
        activity!!.toast(message)
        Log.v("Malibin Debug", "t : ${t.message}, stack : ${TextUtils.join("\n", t.stackTrace)}")
    }

    override fun showInvalidAccessToast(errorJson: String) {
        activity!!.toast("잘못된 접근입니다.")
        Log.v("Malibin Debug", errorJson)
    }

    override fun addBestGoodsList(newData: List<GoodsData>) {
        bestGoodsRVAdapter.addData(newData)
    }

    private fun presenterInit() {
        presenter = BestGoodsPresenter().apply {
            view = this@BestGoodsFragment
            goodsRepository = ApplicationController.networkServiceGoods
            userToken =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJpYXQiOjE1NjIzMTUzNjYsImV4cCI6MTU2MzYyOTM2Nn0.ZkDGasoDPHTrGvy7yFOT9cPjTQ7gnnUOqekY_zYrAuc"
        }
    }

    private fun viewInit() {
        val ctx = activity!!

        bestGoodsRVAdapter = BestGoodsRVAdapter(ctx, bookmarkClickListener, this)
        rv_best_goods_frag_list.apply {
            adapter = bestGoodsRVAdapter
            layoutManager = GridLayoutManager(ctx, 2)
        }
    }

    private fun getBestGoodsData(lastIndex: Int = -1) {
        presenter.getBestGoodsData(lastIndex)
    }

    private fun refreshGoodsData() {
        bestGoodsRVAdapter.dataList.clear()
        presenter.getBestGoodsData()
    }

    fun startProductDetailActivity(storeIdx: Int) {
        startActivityForResult<ProductDetailActivity>(1001, "storeIdx" to storeIdx)
    }

    interface BookmarkClickListener {
        fun onClick(data: GoodsData)
    }

}