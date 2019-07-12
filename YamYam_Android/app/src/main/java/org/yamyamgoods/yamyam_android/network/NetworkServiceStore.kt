package org.yamyamgoods.yamyam_android.network

import org.yamyamgoods.yamyam_android.network.get.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkServiceStore {
    //검색 스토어 이름
    @GET("/search/store/{storeName}/{order}")
    fun getSearchStoreResponse(
        @Header("Content-Type") content_type: String,
        @Header("authorization") token: String?,
        @Path("storeName") storeName: String?,
        @Path("order") order: Int,
        @Query("searchAfter") searchAfter: String?
    ): Call<GetSearchStoreResponse>

    @GET("/store/category")
    fun getStoreCategoryListRequest(
        @Header("Content-Type") contentType: String = "application/json"
    ): Call<GetStoreCategoryListResponseData>

    // 스토어 랭킹 리스트 요청
    @GET("/store/rank/{lastIndex}")
    fun getStoreRankingRequest(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") token: String?,
        @Path("lastIndex") lastIndex: Int,
        @Query("storeCategoryIdx") storeCategoryIdx: Int
    ): Call<GetStoreRankingResponseData>

    // 단골 스토어 리스트 요청
    @GET("/store/scrap/{lastIndex}")
    fun getRegularStoreRequest(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") token: String?,
        @Path("lastIndex") lastIndex: Int
    ): Call<GetRegularStoreResponseData>

    //업체 굿즈 보기
    @GET("/store/{storeIdx}/goods/{order}/{lastIndex}")
    fun getStoreDetailGoodsResponse(
        @Header("Content-Type") content_type: String,
        @Header("Authorization") token: String?,
        @Path("storeIdx") storeIdx: Int,
        @Path("order") order: Int,
        @Path("lastIndex") lastIndex: Int,
        @Query("goodsCategoryIdx") goodsCategoryIdx: Int?,
        @Query("firstFlag") firstFlag: Int?
    ): Call<GetStoreDetailGoodsResponse>

    //업체 굿즈 카테고리 보기
    @GET("/store/{storeIdx}/category")
    fun getStoreDetailGoodsCategoryResponse(
        @Header("Content-Type") content_type: String,
        @Path("storeIdx") storeIdx: Int
    ): Call<GetStoreDetailGoodsCategoryResponse>
}