package org.yamyamgoods.yamyam_android.home.store.regular

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_regular_store.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.StoreData
import org.yamyamgoods.yamyam_android.home.store.regular.adapter.RegularStoreRVAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.util.TempData

class RegularStoreFragment : Fragment(), RegularStoreContract.View {

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
        regularStoreRVAdapter.addData(data)
    }

    override fun setNoRegularStoreList() {
        rv_regular_store_frag_list.visibility = View.GONE
        cl_regular_store_no_data.visibility = View.VISIBLE
    }

    private fun presenterInit() {
        presenter = RegularStorePresenter().apply {
            storeRepository = ApplicationController.networkServiceStore
            view = this@RegularStoreFragment
            userToken =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJpYXQiOjE1NjIzMTUzNjYsImV4cCI6MTU2MzYyOTM2Nn0.ZkDGasoDPHTrGvy7yFOT9cPjTQ7gnnUOqekY_zYrAuc"
        }
    }

    private fun getServerData() {
        presenter.getRegularStoreList()
    }

    private fun viewInit() {
        val ctx = activity!!
        regularStoreRVAdapter = RegularStoreRVAdapter(ctx)
        rv_regular_store_frag_list.apply {
            adapter = regularStoreRVAdapter
            layoutManager = LinearLayoutManager(ctx)
            addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))
        }
    }
}