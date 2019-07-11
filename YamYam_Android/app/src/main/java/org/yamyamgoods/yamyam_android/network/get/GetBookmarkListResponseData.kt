package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.BookmarkData

/**
 * Created By Yun Hyeok
 * on 7월 12, 2019
 */

data class GetBookmarkListResponseData(
    val message: String,
    val data: List<BookmarkData>
)