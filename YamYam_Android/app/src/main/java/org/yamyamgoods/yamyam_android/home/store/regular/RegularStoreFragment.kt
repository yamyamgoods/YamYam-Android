package org.yamyamgoods.yamyam_android.home.store.regular

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_regular_store.*
import org.jetbrains.anko.support.v4.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.StoreData
import org.yamyamgoods.yamyam_android.home.store.regular.adapter.RegularStoreRVAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.util.HomeObject
import org.yamyamgoods.yamyam_android.util.User

class RegularStoreFragment : Fragment(), RegularStoreContract.View {

    companion object {
        private var instance: RegularStoreFragment? = null

        fun getInstance(): RegularStoreFragment = instance
            ?: RegularStoreFragment().apply { instance = this }
    }

    override lateinit var presenter: RegularStoreContract.Presenter

    private lateinit var regularStoreRVAdapter: RegularStoreRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_regular_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenterInit()
        getServerData()
        viewInit()
    }

    override fun showServerFailToast(message: String, t: Throwable) {
        toast(message)
        Log.v("Malibin Debug", "t : ${t.message}, stack : ${TextUtils.join("\n", t.stackTrace)}")
    }

    override fun addRegularStoreList(data: List<StoreData>) {
        if (data.isEmpty()) {
            setNoRegularStoreList()
            return
        }
        rv_regular_store_frag_list.visibility = View.VISIBLE
        cl_regular_store_no_data.visibility = View.GONE
        regularStoreRVAdapter.dataList.clear()
        regularStoreRVAdapter.addData(data)
    }

    override fun setNoRegularStoreList() {
        rv_regular_store_frag_list.visibility = View.GONE
        cl_regular_store_no_data.visibility = View.VISIBLE
    }

    override fun setRegularStoreCanceled(data: StoreData) {
        regularStoreRVAdapter.setRegularStoreRemove(data)
        HomeObject.notifyStoreRankingTabChange()
        toast("단골 스토어가 삭제되었습니다!")
    }

    private fun presenterInit() {
        presenter = RegularStorePresenter().apply {
            storeRepository = ApplicationController.networkServiceStore
            view = this@RegularStoreFragment
            userToken = User.authorization
        }
    }

    private fun getServerData() {
        presenter.getRegularStoreList()
    }

    private fun viewInit() {
        val ctx = activity!!
        regularStoreRVAdapter = RegularStoreRVAdapter(ctx, presenter)

        val dividerItem = DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(activity!!, R.drawable.divider_gray_line_05dp)!!)
        }
        rv_regular_store_frag_list.apply {
            adapter = regularStoreRVAdapter
            layoutManager = LinearLayoutManager(ctx)
            addItemDecoration(dividerItem)
        }
    }

    fun refreshDataList() {
        val size = regularStoreRVAdapter.itemCount
        regularStoreRVAdapter.dataList.clear()
        regularStoreRVAdapter.notifyItemRangeRemoved(0, size)
        presenter.getRegularStoreList()

        Log.v("Malibin Debug", "RegularStore Frag refreshDataList() called")
    }
}