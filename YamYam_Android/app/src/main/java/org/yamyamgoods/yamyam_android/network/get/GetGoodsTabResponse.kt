package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.home.goods.data.GoodsExhibitionData

data class GetGoodsTabResponse(
        val status: Int,
        val message: String,
        val data: GoodsData
)

data class GoodsData(
        val goods_category_data: ArrayList<GoodsCategoryData>,
        val exhibition_data: ArrayList<ExhibitionData>
)

data class GoodsCategoryData(
        val goods_category_idx: Int,
        val goods_category_name: String
)

data class ExhibitionData(
        val exhibition_idx: Int,
        val exhibition_name: String,
        val exhibition_img: String,
        val exhibition_sub_name: String,
        val exhibition_gradation_img: String
)