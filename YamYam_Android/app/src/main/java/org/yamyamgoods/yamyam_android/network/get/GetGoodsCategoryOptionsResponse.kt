package org.yamyamgoods.yamyam_android.network.get

data class GetGoodsCategoryOptionsResponse (
        val message: String,
        val data: ArrayList<CategoryOptionData>
)

data class CategoryOptionData(
        val category_option_idx: Int,
        val category_option_name: String
)