package org.yamyamgoods.yamyam_android.network

import org.yamyamgoods.yamyam_android.network.get.GetUserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface NetworkServiceUser {
    @GET("/user/mypage")
    fun getUserInfoResponse(
            @Header("Content-Type") content_type: String,
            @Header("authorization") token: String
    ): Call<GetUserInfoResponse>
}