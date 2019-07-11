package org.yamyamgoods.yamyam_android.home.best.goods

import org.yamyamgoods.yamyam_android.dataclass.GoodsData
import org.yamyamgoods.yamyam_android.network.post.PostBookmarkRequestDTO
import org.yamyamgoods.yamyam_android.util.BasePresenter
import org.yamyamgoods.yamyam_android.util.BaseView

/**
 * Created By Yun Hyeok
 * on 7월 09, 2019
 */

interface BestGoodsContract {

    interface View : BaseView<Presenter> {

        fun showServerFailToast(message: String, t: Throwable)

        fun showInvalidAccessToast(errorJson : String)

        fun addBestGoodsList(newData: List<GoodsData>)

    }

    interface Presenter : BasePresenter {

        fun getBestGoodsData(lastIndex: Int = -1)

        fun bookmarkRequest(body: PostBookmarkRequestDTO)

        fun bookmarkCancelRequest(goodsIdx: Int)
    }
}