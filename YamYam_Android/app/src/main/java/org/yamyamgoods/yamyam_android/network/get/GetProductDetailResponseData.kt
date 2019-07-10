package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.StoreDetail
import org.yamyamgoods.yamyam_android.dataclass.ReviewData

/**
 * Created By Yun Hyeok
 * on 7월 10, 2019
 */

data class GetProductDetailResponseData(
    val message: String,
    val data: ProductDetailData
)

data class ProductDetailData(
    val goods: GoodsDetail,
    val store: StoreDetail,
    val reviews: List<ReviewData>
)

data class GoodsDetail(
    val goods_idx: Int,
    val goods_name: String,
    val goods_rating: Float,
    val store_name: String,
    val goods_price: String,
    val goods_delivery_charge: String,
    val goods_delivery_period: String,
    val goods_minimum_amount: Int,
    val goods_detail: String,
    val scrap_flag: Int,
    val goods_img: List<String>
)

