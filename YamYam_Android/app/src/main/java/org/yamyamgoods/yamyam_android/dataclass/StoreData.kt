package org.yamyamgoods.yamyam_android.dataclass

import android.text.TextUtils

data class StoreData(

    val store_idx: Int,

    val idx: Int,
    val store_img: String,

    val store_name: String,
    val store_hashtags: List<String>,

    var store_scrap_flag: Boolean,
    val store_url: String
) {
    fun getOneLineHashTags(): String {
        return TextUtils.join(" ", store_hashtags)
    }
}