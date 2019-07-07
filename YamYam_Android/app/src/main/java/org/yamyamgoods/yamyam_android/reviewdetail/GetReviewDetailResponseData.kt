package org.yamyamgoods.yamyam_android.reviewdetail

data class GetReviewDetailResponseData (
        var productInfo: ProductShortInfo,
        var comments: List<ReviewCommentItem>
)