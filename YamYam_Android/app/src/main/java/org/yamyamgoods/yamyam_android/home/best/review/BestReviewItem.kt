package org.yamyamgoods.yamyam_android.home.best.review


data class BestReviewItem(
        var idx: Int,

        var userImageUrl: String,
        var userNickname: String,
        var date: String,
        var starCount: Int,

        var reviewContents: String,

        var imageUrl: List<String>,

        var thumbCount: Int,
        var commentsCount: Int
)