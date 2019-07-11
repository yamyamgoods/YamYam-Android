package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.ProductOption
import org.yamyamgoods.yamyam_android.dataclass.SelectedOption

/**
 * Created By Yun Hyeok
 * on 7월 12, 2019
 */

data class GetBookmarkItemOptionResponseData(
    val message: String,
    val data: BookmarkItemOption
)

data class BookmarkItemOption(
    val goods_price: String,
    val goods_scrap_option_idx: Int,
    val goods_scrap_option_data: List<SelectedOption>,
    val goods_option_data: List<ProductOption>
)
