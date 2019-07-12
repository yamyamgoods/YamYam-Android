package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.GoodsData

data class GetStoreDetailGoodsResponse (
    val message: String,
    val data: ArrayList<GoodsData>
)