package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.ReviewData

data class GetReviewResponse (
    val message: String,
    val data: ReviewCountData
)

data class ReviewCountData(
    val photo_count: Int,
    val review_all_count: Int,
    val review_data: ArrayList<ReviewData>
)

data class GetBestReviewResponse(
    val message: String,
    val data: ArrayList<ReviewData>
)