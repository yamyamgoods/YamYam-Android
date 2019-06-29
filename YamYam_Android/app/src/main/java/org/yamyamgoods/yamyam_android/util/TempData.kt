package org.yamyamgoods.yamyam_android.util

import org.yamyamgoods.yamyam_android.home.best.goods.BestGoodsItem
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.store.ranking.StoreRankingItem
import org.yamyamgoods.yamyam_android.home.store.regular.RegularStoreItem

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

        fun storeRankings(): List<StoreRankingItem> {
            val result = ArrayList<StoreRankingItem>()

            result.add(
                    StoreRankingItem(0, 1, R.drawable.img_store1, "레드프린팅&프레스",
                            listOf("#어쩌구 전문", "#저쩌구전문", "#어쩌구 전문", "#저쩌구전문", "#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    StoreRankingItem(0, 2, R.drawable.img_store2, "후니프린팅",
                            listOf("#어쩌구 전문", "#저쩌구전문", "#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, false
                    ))
            result.add(
                    StoreRankingItem(0, 3, R.drawable.img_store3, "또또굿즈제작소",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    StoreRankingItem(0, 4, R.drawable.img_goods1, "옥수수스토어",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, false
                    ))
            result.add(
                    StoreRankingItem(0, 5, R.drawable.img_goods2, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, false
                    ))
            result.add(
                    StoreRankingItem(0, 6, R.drawable.img_goods3, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, false
                    ))
            result.add(
                    StoreRankingItem(0, 7, R.drawable.img_goods4, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, false
                    ))
            result.add(
                    StoreRankingItem(0, 8, R.drawable.img_store1, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, false
                    ))
            result.add(
                    StoreRankingItem(0, 9, R.drawable.img_store3, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, false
                    ))
            result.add(
                    StoreRankingItem(0, 10, R.drawable.img_store1, "레드프린팅&프레스",
                            listOf("#어쩌구 전문", "#저쩌구전문", ""),
                            4.8f, 1300, true
                    ))
            result.add(
                    StoreRankingItem(0, 11, R.drawable.img_store2, "후니프린팅",
                            listOf("#어쩌구 전문", "#저쩌구전문", ""),
                            4.8f, 1300, false
                    ))
            result.add(
                    StoreRankingItem(0, 12, R.drawable.img_store3, "또또굿즈제작소",
                            listOf("#어쩌구 전문", "#저쩌구전문", ""),
                            4.8f, 1300, true
                    ))
            result.add(
                    StoreRankingItem(0, 13, R.drawable.img_goods1, "옥수수스토어",
                            listOf("#어쩌구 전문", "#저쩌구전문", ""),
                            4.8f, 1300, false
                    ))
            result.add(
                    StoreRankingItem(0, 14, R.drawable.img_goods2, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, false
                    ))


            return result
        }

        fun regularStores(): List<RegularStoreItem> {
            val result = ArrayList<RegularStoreItem>()

            result.add(
                    RegularStoreItem(0, R.drawable.img_store1, "레드프린팅&프레스",
                            listOf("#어쩌구 전문", "#저쩌구전문", "#어쩌구 전문", "#저쩌구전문", "#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_store2, "후니프린팅",
                            listOf("#어쩌구 전문", "#저쩌구전문", "#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_store3, "또또굿즈제작소",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_goods1, "옥수수스토어",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_goods2, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_goods3, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_goods4, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_store1, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_store3, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_store1, "레드프린팅&프레스",
                            listOf("#어쩌구 전문", "#저쩌구전문", ""),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_store2, "후니프린팅",
                            listOf("#어쩌구 전문", "#저쩌구전문", ""),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_store3, "또또굿즈제작소",
                            listOf("#어쩌구 전문", "#저쩌구전문", ""),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_goods1, "옥수수스토어",
                            listOf("#어쩌구 전문", "#저쩌구전문", ""),
                            4.8f, 1300, true
                    ))
            result.add(
                    RegularStoreItem(0, R.drawable.img_goods2, "스토어 이름",
                            listOf("#어쩌구 전문", "#저쩌구전문"),
                            4.8f, 1300, true
                    ))

            return result
        }
    }
}