package org.yamyamgoods.yamyam_android.reviewdetail

data class ReviewCommentItem (
        var reviewInx:Int,
        var commentIdx: Int,

        var userImageUrl: String,
        var userNickname: String,
        var date:String,

        var commentContents: String
)