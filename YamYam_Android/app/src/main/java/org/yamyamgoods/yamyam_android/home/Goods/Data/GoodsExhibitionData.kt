package org.yamyamgoods.yamyam_android.home.Goods.Data

data class GoodsExhibitionData(
        var e_idx: Int,
        var img_thumnail: String,
        var title: String,

        var countSum: Int,
        var detailList:ArrayList<GoodsExhibitionDetailData>
)