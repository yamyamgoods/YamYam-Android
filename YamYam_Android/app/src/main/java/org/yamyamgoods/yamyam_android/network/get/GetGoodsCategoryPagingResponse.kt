package org.yamyamgoods.yamyam_android.network.get

data class GetGoodsCategoryPagingResponse (
        val status: Int,
        val message: String,
        val data: ArrayList<GoodsCategoryData>
)