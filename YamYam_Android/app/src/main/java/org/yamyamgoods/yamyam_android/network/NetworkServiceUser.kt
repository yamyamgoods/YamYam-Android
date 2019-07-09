package org.yamyamgoods.yamyam_android.network

import org.yamyamgoods.yamyam_android.network.get.GetJWTtokenExpiredResponse
import org.yamyamgoods.yamyam_android.network.post.PostKakaoLoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkServiceUser {

    //카카오 로그인
    @POST("/user/signin/kakao")
    fun postKakaoLoginResponse(
            @Header("Content-Type") content_type: String?,
            @Header("accesstoken") token: String?
    ): Call<PostKakaoLoginResponse>

    //JWT Token 만료시 요청
    @GET("/user/jwttoken")
    fun getJWTtokenExpiredResponse(
            @Header("Content-Type") content_type: String,
            @Header("authorization") token: String,
            @Header("refreshtoken") rfToken: String
    ): Call<GetJWTtokenExpiredResponse>
}