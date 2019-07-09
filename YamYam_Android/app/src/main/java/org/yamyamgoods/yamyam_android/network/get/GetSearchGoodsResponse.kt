package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.GoodsData

data class GetSearchGoodsResponse (
        val message: String,
        val data: GoodsSearchResultList
)

data class GoodsSearchResultList(
        val data: ArrayList<GoodsData>
)