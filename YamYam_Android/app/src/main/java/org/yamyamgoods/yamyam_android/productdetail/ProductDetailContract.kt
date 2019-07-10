package org.yamyamgoods.yamyam_android.productdetail

import org.yamyamgoods.yamyam_android.network.get.ProductDetailData
import org.yamyamgoods.yamyam_android.util.BasePresenter
import org.yamyamgoods.yamyam_android.util.BaseView

/**
 * Created By Yun Hyeok
 * on 7월 10, 2019
 */

interface ProductDetailContract {

    interface View : BaseView<Presenter> {

        fun showServerFailToast(message: String, t: Throwable)

        fun setProductDetailData(response: ProductDetailData)

    }

    interface Presenter : BasePresenter {

        fun getProductDetailData(goodsIdx: Int)

    }
}