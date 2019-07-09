package org.yamyamgoods.yamyam_android.network.get

import org.yamyamgoods.yamyam_android.dataclass.LoginData

data class GetJWTtokenExpiredResponse(
        val message: String,
        val data: LoginData
)