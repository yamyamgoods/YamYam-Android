package org.yamyamgoods.yamyam_android.home.best.review


data class BestReviewItem(
        var idx: Int,

        var userImageUrl: String,
        var userNickname: String,
        var date: String,
        var starCount: Int,

        var reviewContents: String,

        var imageUrl: List<String>,

        var thumbFlag: Int,    // 0은 선택 안한 회색, 1은 선택한 노란색
        var thumbCount: Int,
        var commentsCount: Int
)