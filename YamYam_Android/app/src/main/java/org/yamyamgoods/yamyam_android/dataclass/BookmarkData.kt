package org.yamyamgoods.yamyam_android.dataclass

/**
 * Created By Yun Hyeok
 * on 7월 12, 2019
 */

data class BookmarkData(
    val goods_idx: Int,
    var goods_price: String,
    val goods_scrap_idx: Int,
    var goods_scrap_label: String,
    val goods_img: String,
    val store_name: String,
    val goods_name: String
) {
    fun getStoreGoodsName() = "[$store_name] $goods_name"
}