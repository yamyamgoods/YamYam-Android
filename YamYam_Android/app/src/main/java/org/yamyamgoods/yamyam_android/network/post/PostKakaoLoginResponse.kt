package org.yamyamgoods.yamyam_android.network.post

import org.yamyamgoods.yamyam_android.network.LoginData

data class PostKakaoLoginResponse(
        val status: Int,
        val message: String,
        val data: LoginData
)

