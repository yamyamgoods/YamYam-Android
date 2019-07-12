package org.yamyamgoods.yamyam_android.home.bookmark

import org.yamyamgoods.yamyam_android.dataclass.BookmarkData
import org.yamyamgoods.yamyam_android.util.BasePresenter
import org.yamyamgoods.yamyam_android.util.BaseView

/**
 * Created By Yun Hyeok
 * on 7월 12, 2019
 */
interface BookmarkContract {

    interface View : BaseView<Presenter> {

        fun showServerFailToast(message: String, t: Throwable)

        fun addBookmarkData(data: List<BookmarkData>)

        fun deleteBookmarkData(position: Int)
    }

    interface Presenter : BasePresenter {

        fun getBookmarkData(lastIndex: Int = -1)

        fun deleteBookmark(scrapIdx: Int, position: Int)

    }
}