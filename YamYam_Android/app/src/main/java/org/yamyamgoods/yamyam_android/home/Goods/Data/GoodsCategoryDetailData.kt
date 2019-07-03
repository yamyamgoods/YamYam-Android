package org.yamyamgoods.yamyam_android.home.Goods.Data

data class GoodsCategoryDetailData (
        var c_idx: Int,
        var p_img: String,
        var p_liked: Boolean,
        var p_store: String,
        var p_name: String,
        var p_price: String,
        var p_rate: Float,
        var p_minQuantity: Int,
        var p_reviewNum: Int
)