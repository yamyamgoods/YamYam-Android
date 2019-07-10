package org.yamyamgoods.yamyam_android.dataclass

/**
 * Created By Yun Hyeok
 * on 7월 10, 2019
 */

data class ProductOption(
    val goods_option_idx: Int,
    val goods_option_name: String,
    val goods_idx: Int,
    val goods_option_detail: List<ProductOptionDetail>
)