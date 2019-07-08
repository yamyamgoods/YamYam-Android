package org.yamyamgoods.yamyam_android.util

import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.best.goods.BestGoodsItem
import org.yamyamgoods.yamyam_android.home.best.review.BestReviewItem
import org.yamyamgoods.yamyam_android.home.bookmark.BookmarkItem
import org.yamyamgoods.yamyam_android.home.store.ranking.StoreRankingItem
import org.yamyamgoods.yamyam_android.home.store.regular.RegularStoreItem
import org.yamyamgoods.yamyam_android.mypage.MypageProductItem
import org.yamyamgoods.yamyam_android.mypage.alarm.MypageAlarmItem
import org.yamyamgoods.yamyam_android.review.all.ReviewAllItem
import org.yamyamgoods.yamyam_android.reviewdetail.ProductShortInfo
import org.yamyamgoods.yamyam_android.reviewdetail.ReviewCommentItem
import org.yamyamgoods.yamyam_android.reviewwrite.ReviewWriteUploadImagesItem

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

        fun BestReview(): ArrayList<BestReviewItem> {
            val result = ArrayList<BestReviewItem>()
            result.add(
                    BestReviewItem(
                            0, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            1, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf(),
                            0, 3, 55
                    )
            )
            result.add(
                    BestReviewItem(
                            0, "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "유저닉네임", "2019. 06. 24",
                            2, "키릥 소량제작 몇개 했는데 뫄뫄 좋다는 소리듣고 여기서 했거덩요~ 근데 괜찮네여. 배송은 얼마정도 걸렸는데 파본도 별로 없고 조와~ 엄마아빠 할머니가 조와해요 근데 주문할때 뫄뫄는 조심하세요",
                            listOf("https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"),
                            0, 5, 55
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
                            0, 3, 55
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
                            0, 3, 55
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
                            0, 3, 55
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
                            0, 3, 55
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
                            0, 5, 55
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
                            0, 3, 55
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
                            0, 3, 55
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
                                    "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                                     "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045"),
                            0, 3, 55
                    )
            )
            return result
        }

        fun ReviewComments(): List<ReviewCommentItem>{
            val result = ArrayList<ReviewCommentItem>()
            result.add(
                    ReviewCommentItem(
                            1, 0,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "고웽이", "2019.06.24", "혹시 수량은 몇개정도 제작하셨나요??")
                    )
            result.add(
                    ReviewCommentItem(
                            1, 1,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "댕댕쓰", "2019.06.24", "10개가 최소라 10개 딱맞춰서요!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 2,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "곰탱이밤탱이", "2019.06.24", "무무프린팅이 빠르긴한데 파본이 많아서ㅠㅠ")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            result.add(
                    ReviewCommentItem(
                            1, 3,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "톡기", "2019.06.24", "좋은 후기 감사합니다~~~!")
            )
            return result
        }

        fun ReviewDetailProducts(): List<ProductShortInfo>{
            var result = ArrayList<ProductShortInfo>()
            result.add(
                    ProductShortInfo(
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "레드프린팅&프레스",
                            "캐릭터 키링 제작",
                            4.9f, "29,000")
            )
            result.add(
                    ProductShortInfo(
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "레드프린팅&프레스",
                            "캐릭터 키링 제작",
                            5.0f, "87,000")
            )
            result.add(
                    ProductShortInfo(
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "레드프린팅&프레스",
                            "캐릭터 키링 제작",
                            1.6f, "262,000")
            )
            result.add(
                    ProductShortInfo(
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "레드프린팅&프레스",
                            "캐릭터 키링 제작",
                            4.8f, "49,000")
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

        fun bookmarks(): List<BookmarkItem> {
            val result = ArrayList<BookmarkItem>()
            result.add(
                    BookmarkItem(
                            0, false, R.drawable.img_goods1,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900)
            )
            result.add(
                    BookmarkItem(
                            0, true, R.drawable.img_goods2,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900)
            )
            result.add(
                    BookmarkItem(
                            0, true, R.drawable.img_goods3,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900)
            )
            result.add(
                    BookmarkItem(
                            0, false, R.drawable.img_goods4,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900)
            )
            result.add(
                    BookmarkItem(
                            0, false, R.drawable.img_goods1,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900)
            )
            result.add(
                    BookmarkItem(
                            0, true, R.drawable.img_goods2,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900)
            )
            result.add(
                    BookmarkItem(
                            0, false, R.drawable.img_goods3,
                            "레드프린팅&프레스", "도무송 스티커",
                            32900)
            )
            return result
        }

        fun mypageProducts(): List<MypageProductItem>{
            val result = ArrayList<MypageProductItem>()
            result.add(
                    MypageProductItem(
                            0, false,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/57336208_1714122562067955_3478487248456908800_o.jpg?_nc_cat=110&_nc_ht=scontent-icn1-1.xx&oh=37e2d4b1ab1e6797ae00db0b61ba8c1c&oe=5D2A80BE",
                            "레드프린팅&프레스", "도무송 스티커", 32900)
            )
            result.add(
                    MypageProductItem(
                            0, false,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "레드프린팅&프레스", "도무송 스티커", 32900)
            )
            result.add(
                    MypageProductItem(
                            0, false,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "레드프린팅&프레스", "도무송 스티커", 32900)
            )
            result.add(
                    MypageProductItem(
                            0, false,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "레드프린팅&프레스", "도무송 스티커", 32900)
            )
            result.add(
                    MypageProductItem(
                            0, false,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/58383648_1714122605401284_8261916106969579520_o.jpg?_nc_cat=107&_nc_ht=scontent-icn1-1.xx&oh=5685d155cc3622b1535485166619daff&oe=5D756045",
                            "레드프린팅&프레스", "도무송 스티커", 32900)
            )
            result.add(
                    MypageProductItem(
                            0, false,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/57336208_1714122562067955_3478487248456908800_o.jpg?_nc_cat=110&_nc_ht=scontent-icn1-1.xx&oh=37e2d4b1ab1e6797ae00db0b61ba8c1c&oe=5D2A80BE",
                            "레드프린팅&프레스", "도무송 스티커", 32900)
                    )
            result.add(
                    MypageProductItem(
                            0, false,
                            "https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/57484969_1714122525401292_2191765538210840576_o.jpg?_nc_cat=109&_nc_ht=scontent-icn1-1.xx&oh=cdefea1b7b9c21f74652ad3684d3e723&oe=5D6B2C6D",
                            "레드프린팅&프레스", "도무송 스티커", 32900)
            )
            return result
        }

        fun mypageAlarms(): List<MypageAlarmItem>{
            val result= ArrayList<MypageAlarmItem>()
            result.add(
                    MypageAlarmItem(
                            0, 0, 0, true,
                            0, "혹시 수량은 몇개 제작하셨나요?? 저도 할 수 있을까요?0000000000000000000000000",
                            "2019.06.24"
                    )
            )
            result.add(
                    MypageAlarmItem(
                            0, 2, 1, false,
                            1, "혹시 수량은 몇개 제작하셨나요?? 저도 할 수 있을까요?",
                            "2019.06.24"
                    )
            )
            result.add(
                    MypageAlarmItem(
                            5, 3, 2, true,
                            1, "혹시 수량은 몇개 제작하셨나요?? 저도 할 수 있을까요?",
                            "2019.06.24"
                    )
            )
            result.add(
                    MypageAlarmItem(
                            1, 4, 3, false,
                            1, "색감은 쨍하게 잘 나왔나요?_?",
                            "2019.06.24"
                    )
            )
            result.add(
                    MypageAlarmItem(
                            2, 5, 4, true,
                            0, "혹시 수량은 몇개 제작하셨나요?? 저도 할 수 있을까요?",
                            "2019.06.24"
                    )
            )
            result.add(
                    MypageAlarmItem(
                            3, 6, 5, false,
                            0, "혹시 수량은 몇개 제작하셨나요?? 저도 할 수 있을까요?",
                            "2019.06.24"
                    )
            )
            return result
        }

        fun imageUrls(): List<String> = listOf(
                "https://ae01.alicdn.com/kf/HTB10mgVXnwKL1JjSZFgq6z6aVXaF/Kawaii-Anime-Clothes-Acrylic-Badges-Pins-Icon-Decorating-Badge-Backpack-Pin-Button-Acrylic-Brooch-Un.jpg_640x640.jpg"
                , "https://images-na.ssl-images-amazon.com/images/I/51eQv0pInHL.jpg"
                , "https://pics.me.me/thumb_random-depressive-thoughts-me-just-chilling-with-the-bois-on-49748524.png"
                , "https://media.mnn.com/assets/images/2014/08/snowy_owl.png.653x0_q80_crop-smart.jpg"
                , "https://onlineimagetools.com/images/examples-onlineimagetools/color-grid.png"
                , "https://conversationstartersworld.com/wp-content/uploads/2015/12/Random-questions-to-ask-a-girl.jpg"
                , "http://www.mkyong.com/wp-content/uploads/2015/08/java-random-integer-in-range.jpg"
                , "https://ichef.bbci.co.uk/news/660/cpsprodpb/37B5/production/_89716241_thinkstockphotos-523060154.jpg"
                , "https://i0.wp.com/dataaspirant.com/wp-content/uploads/2017/04/Random-Forest-Applications.jpg?resize=500%2C500"
        )
        fun imageUrls2(): List<String> = listOf(
                "https://ae01.alicdn.com/kf/HTB10mgVXnwKL1JjSZFgq6z6aVXaF/Kawaii-Anime-Clothes-Acrylic-Badges-Pins-Icon-Decorating-Badge-Backpack-Pin-Button-Acrylic-Brooch-Un.jpg_640x640.jpg"
                , "https://images-na.ssl-images-amazon.com/images/I/51eQv0pInHL.jpg"
                , "https://pics.me.me/thumb_random-depressive-thoughts-me-just-chilling-with-the-bois-on-49748524.png"
                , "https://media.mnn.com/assets/images/2014/08/snowy_owl.png.653x0_q80_crop-smart.jpg"
                , "https://onlineimagetools.com/images/examples-onlineimagetools/color-grid.png"
                , "https://conversationstartersworld.com/wp-content/uploads/2015/12/Random-questions-to-ask-a-girl.jpg"
        )
        fun imageUrls3(): List<String> = listOf(
                "https://ae01.alicdn.com/kf/HTB10mgVXnwKL1JjSZFgq6z6aVXaF/Kawaii-Anime-Clothes-Acrylic-Badges-Pins-Icon-Decorating-Badge-Backpack-Pin-Button-Acrylic-Brooch-Un.jpg_640x640.jpg"
                , "https://images-na.ssl-images-amazon.com/images/I/51eQv0pInHL.jpg"
                , "https://pics.me.me/thumb_random-depressive-thoughts-me-just-chilling-with-the-bois-on-49748524.png"
        )
    }
}