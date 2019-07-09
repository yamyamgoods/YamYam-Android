package org.yamyamgoods.yamyam_android.network.get

data class GetUserInfoResponse(
    val message: String,
    val data: UserData?
)

data class UserData(
        val user_id: Int,
        val user_name: String,
        val user_email: String,
        val user_img: String,
        val user_point: Int,
        val alarm_flag: Int
)