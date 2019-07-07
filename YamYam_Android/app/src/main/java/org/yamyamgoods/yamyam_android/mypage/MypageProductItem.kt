package org.yamyamgoods.yamyam_android.mypage

data class MypageProductItem (
        val idx: Int,

        val isBookMarked: Boolean,
        val imageUrl: String,

        val storeName: String,
        val productName: String,
        val price: Int
)