package org.yamyamgoods.yamyam_android.network

import okhttp3.MultipartBody
import org.yamyamgoods.yamyam_android.network.get.*
import org.yamyamgoods.yamyam_android.network.post.PostKakaoLoginResponse
import org.yamyamgoods.yamyam_android.network.put.PutMypageEditNicknameRequest
import org.yamyamgoods.yamyam_android.network.put.PostMypageEditProfileImageRequest
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
    @POST("/user/profile")
    fun postMypageEditProfileImageRequest(
            @Header("Authorization") authorization: String,
            @Part img: MultipartBody.Part
    ): Call<PostMypageEditProfileImageRequest>

    //유저 알람 목록보기
    @GET("/user/alarm/list/{lastIndex}")
    fun getAlarmListResponse(
            @Header("Content-Type") content_type: String,
            @Header("Authorization") authorization: String,
            @Path("lastIndex") flag: Int
    ): Call<GetAlarmListResponse>

    // 알람 목록에서 리뷰 상세보기
    @GET("/user/alarm/{alarmIdx}/review/{reviewIdx}")
    fun getAlarmReviewDetail(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String,
        @Path("alarmIdx") alarmIdx: Int,
        @Path("reviewIdx") reviewIdx: Int
    ): Call<GetReviewDetailResponse>
}