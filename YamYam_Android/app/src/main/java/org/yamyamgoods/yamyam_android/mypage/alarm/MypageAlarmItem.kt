package org.yamyamgoods.yamyam_android.mypage.alarm

data class MypageAlarmItem (
        var reviewIdx: Int, // 이동할 리뷰 idx
        var commentIx: Int, //이동할 답글 idx

        var alarmIdx: Int,

        var isRead: Boolean,

        var reviewFlag: Int,
        // 0이면 리뷰에 답글이 달렸습니다:
        // 1이면 댓글에 답글이 달렸습니다:

        var contents: String,
        var date: String
)