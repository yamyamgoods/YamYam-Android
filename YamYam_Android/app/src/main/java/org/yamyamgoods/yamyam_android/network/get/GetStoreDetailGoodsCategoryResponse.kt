package org.yamyamgoods.yamyam_android.network.get

data class GetStoreDetailGoodsCategoryResponse (
    val message: String,
    val data: ArrayList<categoryData>
)

data class categoryData(
    val goods_category_idx: Int,
    val goods_category_name: String
)