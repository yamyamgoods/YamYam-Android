package org.yamyamgoods.yamyam_android.util

import org.yamyamgoods.yamyam_android.home.best.goods.BestGoodsItem
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.best.review.BestReviewItem
import org.yamyamgoods.yamyam_android.home.store.ranking.StoreRankingItem
import org.yamyamgoods.yamyam_android.home.store.regular.RegularStoreItem
import org.yamyamgoods.yamyam_android.review.all.ReviewAllItem

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

        fun BestReview(): List<BestReviewItem>{
            val result = ArrayList<BestReviewItem>()
            result.add(
                    BestReviewItem(
                            0, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            1, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf(),
                            3, 55
                    )
            )
            result.add(
                    BestReviewItem(
                            0, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            2, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"),
                            5, 55
                    )
            )
            result.add(
                    BestReviewItem(
                            0, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            3, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"),
                            3, 55
                    )
            )
            result.add(
                    BestReviewItem(
                            0, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            4, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"),
                            3, 55
                    )
            )
            result.add(
                    BestReviewItem(
                            0, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            5, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"
                                    ,"https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"),
                            3, 55
                    )
            )

            return result
        }
        fun ReviewAll(): List<ReviewAllItem> {
            val result = ArrayList<ReviewAllItem>()
            result.add(
                    ReviewAllItem(
                            0,
                            0, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            1, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf(),
                            3, 55
                    )
            )
            result.add(
                    ReviewAllItem(
                            0,
                            1, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            2, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"),
                            5, 55
                    )
            )
            result.add(
                    ReviewAllItem(
                            0,
                            2, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            3, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"),
                            3, 55
                    )
            )
            result.add(
                    ReviewAllItem(
                            0,
                            3, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            4, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"),
                            3, 55
                    )
            )
            result.add(
                    ReviewAllItem(
                            0,
                            4, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            5, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"
                                    , "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"),
                            3, 55
                    )
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