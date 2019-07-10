package org.yamyamgoods.yamyam_android.network

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.yamyamgoods.yamyam_android.network.get.GetAlarmListResponse
import org.yamyamgoods.yamyam_android.network.get.GetUserInfoResponse
import org.yamyamgoods.yamyam_android.network.get.GetJWTtokenExpiredResponse
import org.yamyamgoods.yamyam_android.network.get.GetMypageRecentlyViewedProductsResponse
import org.yamyamgoods.yamyam_android.network.post.PostKakaoLoginResponse
import org.yamyamgoods.yamyam_android.network.put.PutMypageEditNicknameRequest
import org.yamyamgoods.yamyam_android.network.put.PutMypageEditProfileImageRequest
import retrofit2.Call
import retrofit2.http.*

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

    // 내 정보
    @GET("/user/mypage")
    fun getUserInfoResponse(
            @Header("Content-Type") content_type: String,
            @Header("authorization") token: String
    ): Call<GetUserInfoResponse>

    // 닉네임 수정
    @PUT("/user/name")
    fun putMypageEditNicknameRequest(
            @Header("Content-Type") content_type: String,
            @Header("authorization") authorization: String,
            @Body body: PutMypageEditNicknameRequest
    ): Call<PutMypageEditNicknameRequest>

    // 최근 본 상품
    @GET("/user/goods/recent/{lastIndex}")
    fun getMypageRecentlyViewedProductsResponse(
            @Header("Content-Type") content_type: String,
            @Header("Authorization") authorization: String,
            @Path("lastIndex") flag: Int
    ): Call<GetMypageRecentlyViewedProductsResponse>

    // 프로필 사진 수정
    @Multipart
    @PUT("/user/profile")
    fun putMypageEditProfileImageRequest(
            @Header("Content-Type") content_type: String,
            @Header("Authorization") authorization: String,
            @Part img: MultipartBody.Part
    ): Call<PutMypageEditProfileImageRequest>

    //유저 알람 목록보기
    @GET("/user/alarm/list/{lastIndex}")
    fun getAlarmListResponse(
            @Header("Content-Type") content_type: String,
            @Header("Authorization") authorization: String,
            @Path("lastIndex") flag: Int
    ): Call<GetAlarmListResponse>
}