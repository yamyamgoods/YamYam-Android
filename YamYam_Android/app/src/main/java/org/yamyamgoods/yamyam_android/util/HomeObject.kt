package org.yamyamgoods.yamyam_android.util

import org.yamyamgoods.yamyam_android.home.bookmark.BookmarkFragment

/**
 * Created By Yun Hyeok
 * on 7월 12, 2019
 */
object HomeObject {

    fun notifyBookmarkTabChange(){
        BookmarkFragment.getInstance().refreshDataList()
    }
}