package org.yamyamgoods.yamyam_android.dataclass

//검색은 따로 만듦
data class GoodsData (
        val search_after: List<Int>?,
        val goods_idx: Int,
        val goods_category_idx: Int?,
        val goods_img: String,
        val store_idx: Int?,
        val store_name: String,
        val goods_name: String,
        val goods_price: String,
        val goods_rating: Float?,
        val goods_minimum_amount: String?,
        val goods_review_cnt: Int?,
        var scrap_flag: Int
)