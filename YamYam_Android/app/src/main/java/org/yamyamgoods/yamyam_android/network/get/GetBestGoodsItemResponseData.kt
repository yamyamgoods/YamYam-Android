package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.GoodsData

/**
 * Created By Yun Hyeok
 * on 7월 09, 2019
 */

data class GetBestGoodsItemResponseData(
        val message: String,
        val data: List<GoodsData>
)