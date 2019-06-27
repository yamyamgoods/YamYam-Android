package org.yamyamgoods.yamyam_android.util

import org.yamyamgoods.yamyam_android.home.best.goods.BestGoodsItem
import org.yamyamgoods.yamyam_android.R

class TempData {
    companion object {

        fun bestGoods(): List<BestGoodsItem> {
            val result = ArrayList<BestGoodsItem>()
            result.add(
                    BestGoodsItem(
                            0, false, R.drawable.img_goods1,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900, 4.8, 10, 300)
            )
            result.add(
                    BestGoodsItem(
                            0, true, R.drawable.img_goods2,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900, 4.8, 10, 300)
            )
            result.add(
                    BestGoodsItem(
                            0, true, R.drawable.img_goods3,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900, 4.8, 10, 300)
            )
            result.add(
                    BestGoodsItem(
                            0, false, R.drawable.img_goods4,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900, 4.8, 10, 300)
            )
            result.add(
                    BestGoodsItem(
                            0, false, R.drawable.img_goods1,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900, 4.8, 10, 300)
            )
            result.add(
                    BestGoodsItem(
                            0, true, R.drawable.img_goods2,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900, 4.8, 10, 300)
            )
            result.add(
                    BestGoodsItem(
                            0, false, R.drawable.img_goods3,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900, 4.8, 10, 300)
            )
            return result
        }
    }
}