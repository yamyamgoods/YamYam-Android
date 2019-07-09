package org.yamyamgoods.yamyam_android.network.get

data class GetSearchGoodsResponse (
        val message: String,
        val data: goods
)

data class goods(
        val data: ArrayList<SearchGoodsData>
)

data class SearchGoodsData(
        val search_after: List<Int>,
        val goods_idx: Int,
        val goods_name: String,
        val goods_rating: Float,
        val goods_price: Int,
        val goods_minimum_amout: Int,
        val goods_detail: String,
        val goods_review_cnt: Int,
        val goods_img: String,
        val store_name: String
)