package org.yamyamgoods.yamyam_android.productdetail

import org.yamyamgoods.yamyam_android.dataclass.ProductOption
import org.yamyamgoods.yamyam_android.network.get.ProductDetailData
import org.yamyamgoods.yamyam_android.network.post.PostBookmarkRequestDTO
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

        fun setProductOptionData(response: List<ProductOption>)

        fun showBookmarkSuccessDialog()

        fun showBookmarkCancelToast()

        fun showLoginRequiredDialog()

        fun showAlreadySameOptionsBookmarkToast()

        fun showAlreadySameLabelBookmarkToast()
    }

    interface Presenter : BasePresenter {

        fun getProductDetailData(goodsIdx: Int)

        fun bookmarkRequest(body: PostBookmarkRequestDTO)

        fun bookmarkCancelRequest(goodsIdx: Int)

        fun getProductOptionData(goodsIdx: Int)
    }
}