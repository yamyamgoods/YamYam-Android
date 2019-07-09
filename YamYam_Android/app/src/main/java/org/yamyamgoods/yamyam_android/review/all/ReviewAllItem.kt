package org.yamyamgoods.yamyam_android.review.all

data class ReviewAllItem(
        var goods_review_idx: Int,

        var user_img: String,
        var user_name: String,
        var goods_review_date: String,
        var goods_review_rating: Int,

        var goods_review_content: String,

        var goods_review_img: List<String>,

        var review_like_flag: Int,
        var goods_review_like_count: Int,
        var goods_review_cmt_count: Int,

        val goods_review_photo_flag: Int
)
//{
//    "user_name": "아영이",
//    "goods_review_idx": 6,
//    "goods_review_date": "2019.07.05 22:24:30",
//    "goods_review_rating": 5,
//    "goods_review_content": "내용",
//    "goods_review_like_count": 5,
//    "goods_review_cmt_count": 5,
//    "goods_review_photo_flag": 1,!!!!!!!!!!!!!!!!1
//    "goods_review_img": [
//    "https://yamyamgoods.s3.ap-northeast-2.amazonaws.comimgg",
//    "https://yamyamgoods.s3.ap-northeast-2.amazonaws.comimg"
//    ],
//    "review_like_flag": 0
//}