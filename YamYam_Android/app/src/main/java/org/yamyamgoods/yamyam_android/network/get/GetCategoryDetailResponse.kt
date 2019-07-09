package org.yamyamgoods.yamyam_android.network.get

data class GetCategoryDetailResponse (
      val status: Int,
      val message: String,
      val data: ArrayList<CategoryDetailData>
)

data class CategoryDetailData(
        val goods_idx:Int,
        val goods_name: String,
        val goods_price: String,
        val goods_rating: Float,
        val goods_minimum_amount: Int,
        val goods_review_cnt: Int,
        val goods_img: String,
        val goods_like_flag: Boolean
)