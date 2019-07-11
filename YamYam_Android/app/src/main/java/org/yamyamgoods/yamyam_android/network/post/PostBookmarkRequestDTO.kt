package org.yamyamgoods.yamyam_android.network.post

import org.yamyamgoods.yamyam_android.dataclass.SelectedOption

/**
 * Created By Yun Hyeok
 * on 7월 10, 2019
 */

data class PostBookmarkRequestDTO(
        var goodsIdx: Int,
        var goodsScrapPrice: Int,
        var goodsScrapLabel: String,
        var options: List<SelectedOption>?
)