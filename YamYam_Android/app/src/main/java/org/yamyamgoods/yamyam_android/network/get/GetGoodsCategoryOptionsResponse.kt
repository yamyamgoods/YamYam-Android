package org.yamyamgoods.yamyam_android.network.get

data class GetGoodsCategoryOptionsResponse (
        val message: String,
        val data: ArrayList<CategoryOptionData>
)

data class CategoryOptionData(
        val category_option_idx: Int,
        val category_option_name: String,
        val category_option_detail: ArrayList<CategoryOptionsDetailData>
)

data class CategoryOptionsDetailData(
        val category_option_detail_idx: Int,
        val category_option_detail_name: String
)