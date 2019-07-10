package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.ReviewData

data class GetReviewResponse (
    val message: String,
    val data: ArrayList<ReviewData>
)