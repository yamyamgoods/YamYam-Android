package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.network.GoodsData

data class GetExhibitionDetailResponse (
        val status: Int,
        val message: String,
        val data: ArrayList<GoodsData>
)