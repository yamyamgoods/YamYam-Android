package org.yamyamgoods.yamyam_android.network.post

data class PostCommentWriteRequestData (
    val reviewIdx: Int,
    val contents: String,
    val userIdxForAlarm: Int,
    val recommentFlag: Int  //0: 댓글, 1: 대댓글
)