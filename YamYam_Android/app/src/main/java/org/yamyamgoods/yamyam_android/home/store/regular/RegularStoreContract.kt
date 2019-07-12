package org.yamyamgoods.yamyam_android.home.store.regular

import org.yamyamgoods.yamyam_android.dataclass.StoreData
import org.yamyamgoods.yamyam_android.util.BasePresenter
import org.yamyamgoods.yamyam_android.util.BaseView

/**
 * Created By Yun Hyeok
 * on 7월 11, 2019
 */

interface RegularStoreContract {

    interface View : BaseView<Presenter> {

        fun showServerFailToast(message: String, t: Throwable)

        fun addRegularStoreList(data: List<StoreData>)

        fun setNoRegularStoreList()

        fun setRegularStoreCanceled(data: StoreData)

    }

    interface Presenter : BasePresenter {

        fun getRegularStoreList(lastIdx: Int = -1)

        fun regularStoreCancelRequest(data: StoreData, storeIdx: Int)

    }
}