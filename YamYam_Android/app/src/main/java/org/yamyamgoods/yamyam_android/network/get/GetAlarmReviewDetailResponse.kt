package org.yamyamgoods.yamyam_android.network.get

data class GetAlarmReviewDetailResponse(
    val message: String,
    val data: GetAlarmReviewDetailData
)

data class GetAlarmReviewDetailData(
    val goods: ReviewDetailGoodsData,
    val review_comment: ArrayList<ReviewCommentData>
)