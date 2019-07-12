package org.yamyamgoods.yamyam_android.network.get

data class GetAlarmListResponse(
        val message: String,
        val data: ArrayList<AlarmListData>
)

data class AlarmListData(
        val alarm_idx: Int,
        val alarm_target_idx: Int,
        //val alarm_check_flag: Int,
        val alarm_date_time: String,
        val alarm_message: String,
        val goods_review_idx: Int
)