package org.yamyamgoods.yamyam_android.review.all

data class ReviewAllItem(
        var productIdx: Int,

        var reviewIdx: Int,

        var userImageUrl: String,
        var userNickname: String,
        var date: String,
        var starCount: Int,

        var reviewContents: String,

        var imageUrl: List<String>,

        var thumbCount: Int,
        var commentsCount: Int
)