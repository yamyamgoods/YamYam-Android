package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.GoodsData

data class GetCategoryDetailResponse (
      val status: Int,
      val message: String,
      val data: ArrayList<GoodsData>
)
