package org.yamyamgoods.yamyam_android.home.store.regular

import android.text.TextUtils

data class RegularStoreItem (

        val storeIdx: Int,

        val imageUrl: Int,// 수정할 것

        val storeName: String,
        val hashTags: List<String>,
        val starRate: Float,
        val reviewCount: Int,

        val isLiked: Boolean

) {
    fun getOneLineHashTags(): String {
        return TextUtils.join(" ", hashTags)
    }
}