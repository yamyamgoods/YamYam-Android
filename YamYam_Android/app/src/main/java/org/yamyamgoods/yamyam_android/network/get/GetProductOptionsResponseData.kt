package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.ProductOption

/**
 * Created By Yun Hyeok
 * on 7월 10, 2019
 */

data class GetProductOptionsResponseData(
    val message: String,
    val data: List<ProductOption>
)