package org.yamyamgoods.yamyam_android.network.put

import org.yamyamgoods.yamyam_android.dataclass.SelectedOption

/**
 * Created By Yun Hyeok
 * on 7월 12, 2019
 */

data class PutBookmarkModifyRequestDTO(
    val goodsScrapIdx: Int,
    val goodsIdx: Int,
    val goodsScrapPrice: Int,
    val goodsScrapLabel: String,
    var options: List<SelectedOption>?
)