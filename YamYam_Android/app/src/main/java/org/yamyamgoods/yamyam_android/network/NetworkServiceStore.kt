package org.yamyamgoods.yamyam_android.network

import org.yamyamgoods.yamyam_android.network.get.GetRegularStoreResponseData
import org.yamyamgoods.yamyam_android.network.get.GetSearchStoreResponse
import org.yamyamgoods.yamyam_android.network.get.GetStoreCategoryListResponseData
import org.yamyamgoods.yamyam_android.network.get.GetStoreRankingResponseData
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
        @Header("Authorization") token: String,
        @Path("storeName") storeName: String,
        @Path("order") order: Int,
        @Query("searchAfter") searchAfter: List<Int>
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
}