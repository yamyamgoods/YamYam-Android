package org.yamyamgoods.yamyam_android.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.yamyamgoods.yamyam_android.network.delete.DeleteBookmarkResponseData
import org.yamyamgoods.yamyam_android.network.delete.DeleteReviewLikeResponseData
import org.yamyamgoods.yamyam_android.network.get.*
import org.yamyamgoods.yamyam_android.network.post.*
import retrofit2.Call
import retrofit2.http.*

interface NetworkServiceGoods {

    //굿즈탭 전체보기
    @GET("/goods")
    fun getGoodsTabResponse(
            @Header("Content-Type") content_type: String?,
            @Header("authorization") token: String?
    ): Call<GetGoodsTabResponse>

    //굿즈탭 카테고리 페이징
    @GET("/goods/category/{lastIndex}")
    fun getGoodsCategoryPagingResponse(
            @Header("Content-Type") content_type: String,
            @Header("authorization") token: String,
            @Path("lastIndex") lastIndex: Int
    ): Call<GetGoodsCategoryPagingResponse>

    //얌얌추천 기획전 굿즈 모두보기
    @GET("/goods/exhibition/{exhibitionIdx}/{lastIndex}")
    fun getExhibitionDetailResponse(
            @Header("Content-Type") content_type: String,
            @Header("authorization") token: String?,
            @Path("exhibitionIdx") exhibitionIdx: Int,
            @Path("lastIndex") lastIndex: Int
    ): Call<GetExhibitionDetailResponse>

    //카테고리에 따른 굿즈 모두보기
    @GET("/goods/category/{goodsCategoryIdx}/{order}/{lastIndex}")
    fun getCategoryDetailResponse(
            @Header("Content-Type") content_type: String,
            @Header("Authorization") token: String?,
            @Path("goodsCategoryIdx") goodsCategoryIdx: Int,
            @Path("order") order: Int,
            @Path("lastIndex") lastIndex: Int,
            @Query("priceStart") priceStart: Int?,
            @Query("priceEnd") priceEnd: Int?,
            @Query("minAmount") minAmount: Int?,
            @Query("options") options: List<Int>?
    ): Call<GetCategoryDetailResponse>

    //가격 범위 보기
    @GET("/goods/category/{goodsCategoryIdx}/priceRange")
    fun getPriceRangeResponse(
            @Header("Content-Type") content_type: String,
            @Path("goodsCategoryIdx") goodsCategoryIdx: Int,
            @Query("minAmount") minAmount: Int?
    ): Call<GetPriceRangeResponse>

    //굿즈 카테고리 하위 옵션 보기
    @GET("/goods/category/{goodsCategoryIdx}/options")
    fun getGoodsCategoryOptionsResponse(
            @Header("Content-Type") content_type: String,
            @Path("goodsCategoryIdx") goodsCategoryIdx: Int
    ): Call<GetGoodsCategoryOptionsResponse>

    //검색 굿즈 이름
    @GET("/search/goods/{goodsName}/{order}")
    fun getSearchGoodsResponse(
            @Header("Content-Type") content_type: String,
            @Header("Authorization") token: String,
            @Path("goodsName") goodsName: String,
            @Path("order") order: Int,
            @Query("searchAfter") searchAfter: List<Int>
    ): Call<GetSearchGoodsResponse>

    //베스트 굿즈 리스트 요청
    @GET("/goods/best/{lastIndex}")
    fun getBestGoodsItemRequest(
            @Header("Content-Type") contentType: String = "application/json",
            @Header("Authorization") token: String?,
            @Path("lastIndex") lastIndex: Int
    ): Call<GetBestGoodsItemResponseData>

    //베스트 굿즈 북마크 요청
    @POST("/goods/scrap")
    fun postBookmarkRequest(
            @Header("Content-Type") contentType: String = "application/json",
            @Header("Authorization") token: String?,
            @Body body: PostBookmarkRequestDTO
    ): Call<PostBookmarkResponseData>

    //베스트 굿즈 북마크 해제 요청
    @DELETE("/goods/{goodsIdx}/scrap")
    fun deleteBookmarkRequest(
            @Header("Content-Type") contentType: String = "application/json",
            @Header("Authorization") token: String?,
            @Path("goodsIdx") goodsIdx: Int
    ): Call<DeleteBookmarkResponseData>

    //리뷰 상세보기
    @GET("/goods/review/{reviewIdx}/detail")
    fun getReviewDetailResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String?,
        @Path("reviewIdx") reviewIdx:Int
    ): Call<GetReviewDetailResponse>

    //상품 상세보기 데이터 요청
    @GET("goods/{goodsIdx}/detail")
    fun getProductDetailRequest(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") token: String?,
        @Path("goodsIdx") goodsIdx: Int
    ): Call<GetProductDetailResponseData>

    //상품 견적 데이터 요청
    @GET("/goods/{goodsIdx}/options")
    fun getProductOptionsRequest(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") token: String?,
        @Path("goodsIdx") goodsIdx: Int
    ): Call<GetProductOptionsResponseData>

    //베스트 리뷰
    @GET("/goods/reviews/best/{lastIndex}")
    fun getBestReviewItemRequest(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String?,
        @Path("lastIndex") lastIndex: Int
    ): Call<GetReviewResponse>

    // 댓글 작성하기
    @POST("/goods/review/comment")
    fun postCommentWriteRequest(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authroization: String?,
        @Body requestBody: PostCommentWriteRequestData
    ): Call<PostCommentWriteRequestData>

    // 리뷰 좋아요
    @POST("/goods/review/like")
    fun postReviewLikeRequest(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String?,
        @Body requestBody: PostReviewLikeData
    ): Call<PostReviewLikeData>

    // 리뷰 작성 페이지 (옵션 나타내주는 부분)
    @DELETE("/goods/review/{reviewIdx}/like")
    fun deleteReviewLikeRequest(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String?,
        @Path("reviewIdx") reviewIdx: Int
    ): Call<DeleteReviewLikeResponseData>

    // 리뷰 작성 페이지 (옵션 나타내주는 부분)
    @GET("/goods/{goodsIdx}/options/name")
    fun getReviewWritePageRequest(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authroization: String?,
        @Path("goodsIdx") goodsIdx: Int
    ): Call<GetReviewWritePageResponse>

    // 리뷰 작성 하기
    @Multipart
    @POST("/goods/review")
    fun postReviewWriteRequest(
        @Header("Authorization") authroization: String?,
        @Part("goodsIdx") goodsIdx: Int,
        @Part("content") content: RequestBody,
        @Part("rating") rating : Int,
        @Part img: ArrayList<MultipartBody.Part>
    ): Call<PostReviewWriteResponse>

    // 리뷰 보기
    @GET("/goods/{goodsIdx}/reviews/{photoFlag}/{lastIndex}")
    fun getReviewResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") authorization: String?,
        @Path("goodsIdx") goodsIdx: Int,
        @Path("photoFlag") goodsFlag: Int,
        @Path("lastIdx") lastIdx: Int
    ): Call<GetReviewResponse>
}