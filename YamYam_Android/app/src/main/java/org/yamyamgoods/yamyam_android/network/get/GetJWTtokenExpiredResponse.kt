package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.network.LoginData

data class GetJWTtokenExpiredResponse(
        val message: String,
        val data: LoginData
)