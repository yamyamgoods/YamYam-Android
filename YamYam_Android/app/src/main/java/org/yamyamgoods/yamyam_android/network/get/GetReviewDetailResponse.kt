package org.yamyamgoods.yamyam_android.network.get

data class GetReviewDetailResponse (
    val message: String,
    val data: reviewDetailData
)

data class reviewDetailData(
    val review: ReviewDetailReviewData,
    val goods: ReviewDetailGoodsData,
    val review_comment: ArrayList<ReviewCommentData>
)

data class ReviewDetailReviewData(
    val user_idx: Int
)

data class ReviewDetailGoodsData(
    val goods_idx: Int,
    val goods_img: String,
    val goods_name: String,
    val goods_price: String,
    val goods_rating: Float,
    val store_name: String
)
//
//data class ReviewAlarmGoodsData(
//    val goods_review_idx: Int,
//    val user_idx: Int,
//    val user_name: String,
//    val user_img: String,
//    val goods_review_date: String,
//    val goods_review_rating: Int,
//    val goods_review_photo_flag: Int,
//    val goods_review_like_count: String,
//    val goods_review_cmt_content: String,
//    val review_like_flag: Int,
//    val goods_review_img,
//)

data class ReviewCommentData(
    val goods_review_cmt_idx: Int,
    val user_idx: Int,
    val goods_review_cmt_content: String,
    val goods_review_cmt_date: String,
    val user_name: String,
    val user_img: String
)