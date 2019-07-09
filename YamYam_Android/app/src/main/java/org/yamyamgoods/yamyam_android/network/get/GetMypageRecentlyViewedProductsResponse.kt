package org.yamyamgoods.yamyam_android.network.get

data class GetMypageRecentlyViewedProductsResponse (
        val message: String,
        val data: ArrayList<RecentlyViewedProducts>
)

data class RecentlyViewedProducts(
        val user_recent_goods_idx: Int,
        val goods_idx: Int,
        val goods_name: String,
        val goods_price: String,
        val store_name: String,
        val scrap_flag: Int,
        val goods_img: String
)