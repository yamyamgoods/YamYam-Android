package org.yamyamgoods.yamyam_android.home.bookmark

/**
 * Created By Yun Hyeok
 * on 7월 06, 2019
 */

data class BookmarkItem (
        val idx: Int,

        val isBookMarked: Boolean,
        val imageUrl: Int,

        val storeName: String,
        val productName: String,
        val price: Int
)