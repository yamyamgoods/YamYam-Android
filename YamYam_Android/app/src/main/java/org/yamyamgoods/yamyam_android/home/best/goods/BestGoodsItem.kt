package org.yamyamgoods.yamyam_android.home.best.goods

data class BestGoodsItem(
        val idx: Int,

        val isBookMarked: Boolean,
        val imageUrl: Int,

        val storeName: String,
        val productName: String,
        val price: Int,

        val starRate: Double,
        val minQuantity: Int,
        val reviewCount: Int
)