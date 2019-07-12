package org.yamyamgoods.yamyam_android.home.store.ranking

import org.yamyamgoods.yamyam_android.dataclass.StoreCategory
import org.yamyamgoods.yamyam_android.dataclass.StoreData
import org.yamyamgoods.yamyam_android.util.BasePresenter
import org.yamyamgoods.yamyam_android.util.BaseView

/**
 * Created By Yun Hyeok
 * on 7월 11, 2019
 */

interface StoreRankingContract {

    interface View : BaseView<Presenter> {

        fun showServerFailToast(message: String, t: Throwable)

        fun setStoreCategory(data: List<StoreCategory>)

        fun setStoreRankingList(data: List<StoreData>)
    }

    interface Presenter : BasePresenter {

        fun getStoreCategory()

        fun getStoreRankingList(storeCategoryIdx: Int, lastIdx: Int = -1)
    }
}