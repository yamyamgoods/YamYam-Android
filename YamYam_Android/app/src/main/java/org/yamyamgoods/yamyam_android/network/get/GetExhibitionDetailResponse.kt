package org.yamyamgoods.yamyam_android.network.get

data class GetExhibitionDetailResponse (
        val status: Int,
        val message: String,
        val data: ArrayList<ExhibitionDetailData>
)

data class ExhibitionDetailData(
        val goods_idx: Int,
        val goods_category_idx: Int,
        val goods_name: String,
        val goods_rating: Int,
        val goods_price: String,
        val goods_minimum_amout: Int,
        val store_Idx: Int,
        val store_name: String,
        val scrap_flag: Int,
        val goods_img: String
)