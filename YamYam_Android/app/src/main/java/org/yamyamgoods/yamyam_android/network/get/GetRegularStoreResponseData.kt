package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.StoreData

/**
 * Created By Yun Hyeok
 * on 7월 11, 2019
 */

data class GetRegularStoreResponseData(
    val message: String,
    val data: List<StoreData>
)