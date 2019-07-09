package org.yamyamgoods.yamyam_android.home.best.goods

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_best_goods.*
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.home.best.goods.adapter.BestGoodsRVAdapter
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.util.TempData

class BestGoodsFragment : Fragment(), BestGoodsContract.View {

    override lateinit var presenter: BestGoodsContract.Presenter

    lateinit var bestGoodsRVAdapter: BestGoodsRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_best_goods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenterInit()
        viewInit()
        getBestGoodsData()
    }

    override fun showServerFailToast(message: String, t: Throwable) {
        activity!!.toast(message)
        Log.v("Malibin Debug", "t : ${t.message}, stack : ${t.stackTrace}")
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
        }
    }

    private fun viewInit() {
        val ctx = activity!!.applicationContext

        bestGoodsRVAdapter = BestGoodsRVAdapter(ctx)
        rv_best_goods_frag_list.apply {
            adapter = bestGoodsRVAdapter
            layoutManager = GridLayoutManager(ctx, 2)
        }
    }

    private fun getBestGoodsData(lastIndex: Int = -1) {
        presenter.getBestGoodsData(null, lastIndex)
    }

}