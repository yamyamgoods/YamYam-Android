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