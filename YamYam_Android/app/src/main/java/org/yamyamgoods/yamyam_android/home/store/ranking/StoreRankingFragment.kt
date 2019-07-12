package org.yamyamgoods.yamyam_android.home.store.ranking

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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_store_ranking.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.dataclass.StoreCategory
import org.yamyamgoods.yamyam_android.dataclass.StoreData
import org.yamyamgoods.yamyam_android.home.store.ranking.adapter.StoreRankingRVAdapter
import org.yamyamgoods.yamyam_android.home.store.regular.RegularStoreFragment
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.productdetail.dialog.LoginRequestDialog
import org.yamyamgoods.yamyam_android.util.HomeObject
import org.yamyamgoods.yamyam_android.util.User

class StoreRankingFragment : Fragment(), StoreRankingContract.View {

    companion object {
        private var instance: StoreRankingFragment? = null

        fun getInstance(): StoreRankingFragment = instance
            ?: StoreRankingFragment().apply { instance = this }
    }

    override lateinit var presenter: StoreRankingContract.Presenter

    private lateinit var storeRankingRVAdapter: StoreRankingRVAdapter
    private lateinit var storeCategories: List<StoreCategory>

    private var isFirstSpinner = true
    private var currentCategory = -1

    private val spinnerListener = object : AdapterView.OnItemSelectedListener {

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (isFirstSpinner) {
                isFirstSpinner = false
                return
            }
            val categoryIdx = storeCategories[position].store_category_idx
            presenter.getStoreRankingList(categoryIdx)
            currentCategory = categoryIdx
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_store_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenterInit()
        getStoreCategoryData()
        viewInit()
    }

    override fun showServerFailToast(message: String, t: Throwable) {
        activity!!.toast(message)
        Log.v("Malibin Debug", "t : ${t.message}, stack : ${TextUtils.join("\n", t.stackTrace)}")
    }

    override fun setStoreCategory(data: List<StoreCategory>) {
        storeCategories = data
        categorySpinnerInit(data)
        val firstCategoryIdx = data[0].store_category_idx
        currentCategory = firstCategoryIdx
        presenter.getStoreRankingList(firstCategoryIdx)
    }

    override fun setStoreRankingList(data: List<StoreData>) {
        storeRankingRVAdapter.refreshAllDataWith(data)
    }

    override fun setRegularStoreMarked(position: Int) {
        storeRankingRVAdapter.setRegularStoreSelected(position, true)
        HomeObject.notifyRegularStoreTabChange()
        toast("단골 스토어가 등록되었습니다!")
    }

    override fun setRegularStoreCanceled(position: Int) {
        storeRankingRVAdapter.setRegularStoreSelected(position, false)
        HomeObject.notifyRegularStoreTabChange()
        toast("단골 스토어가 삭제되었습니다!")
    }

    override fun showLoginRequiredDialog() {
        LoginRequestDialog(activity!!).show()
    }

    private fun presenterInit() {
        presenter = StoreRankingPresenter().apply {
            userToken = User.authorization
            view = this@StoreRankingFragment
            storeRepository = ApplicationController.networkServiceStore
        }
    }

    private fun getStoreCategoryData() {
        presenter.getStoreCategory()
    }

    private fun categorySpinnerInit(data: List<StoreCategory>) {
        val categories = getCategoryList(data)
        spinner_rv_item_store_ranking_frag.apply {
            adapter = ArrayAdapter(activity!!, R.layout.spinner_item_category, R.id.tv_spinner_category, categories)
            onItemSelectedListener = spinnerListener
        }
    }

    private fun getCategoryList(data: List<StoreCategory>): List<String> {
        val result = ArrayList<String>()
        for (category in data) {
            result.add(category.store_category_name)
        }
        return result
    }

    private fun viewInit() {
        val ctx = activity!!
        storeRankingRVAdapter = StoreRankingRVAdapter(ctx, presenter)

        val dividerItem = DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(activity!!, R.drawable.divider_gray_line_05dp)!!)
        }
        rv_item_store_ranking_frag_list.apply {
            adapter = storeRankingRVAdapter
            layoutManager = LinearLayoutManager(ctx)
            addItemDecoration(dividerItem)
        }
    }

    fun refreshDataList() {
        val size = storeRankingRVAdapter.itemCount
        storeRankingRVAdapter.dataList.clear()
        storeRankingRVAdapter.notifyItemRangeRemoved(0, size)
        presenter.getStoreRankingList(currentCategory)

        Log.v("Malibin Debug", "StoreRanking Frag refreshDataList() called")
    }

}