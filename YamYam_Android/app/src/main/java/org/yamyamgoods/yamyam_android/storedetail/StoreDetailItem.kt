package org.yamyamgoods.yamyam_android.storedetail

/**
 * Created By Yun Hyeok
 * on 7월 09, 2019
 */

data class StoreDetailItem(
        val goods_idx : Int,

        val goods_like_flag: Boolean,
        val goods_img: Int,

        val goods_name: String,
        val goods_price: String,

        val goods_rating: Double,
        val goods_minimum_amount: Int,
        val goods_review_cnt: Int
)