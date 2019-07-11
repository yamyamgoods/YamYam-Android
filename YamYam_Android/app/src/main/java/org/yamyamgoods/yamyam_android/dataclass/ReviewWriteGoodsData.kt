package org.yamyamgoods.yamyam_android.dataclass

data class ReviewWriteGoodsData (
    val goodsIdx: Int,
    val content: String,
    val img: ArrayList<String>?,
    val rating: Float
)