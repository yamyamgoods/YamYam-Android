package org.yamyamgoods.yamyam_android.util

import org.yamyamgoods.yamyam_android.home.bookmark.BookmarkFragment
import org.yamyamgoods.yamyam_android.home.store.ranking.StoreRankingFragment
import org.yamyamgoods.yamyam_android.home.store.regular.RegularStoreFragment

/**
 * Created By Yun Hyeok
 * on 7월 12, 2019
 */
object HomeObject {

    fun notifyBookmarkTabChange() {
        BookmarkFragment.getInstance().refreshDataList()
    }

    fun notifyRegularStoreTabChange() {
        RegularStoreFragment.getInstance().refreshDataList()
    }

    fun notifyStoreRankingTabChange() {
        StoreRankingFragment.getInstance().refreshDataList()
    }
}