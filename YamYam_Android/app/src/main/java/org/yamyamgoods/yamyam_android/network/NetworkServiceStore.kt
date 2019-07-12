package org.yamyamgoods.yamyam_android.network

import org.yamyamgoods.yamyam_android.network.delete.DeleteRegularStoreMarkResponseData
import org.yamyamgoods.yamyam_android.network.get.GetRegularStoreResponseData
import org.yamyamgoods.yamyam_android.network.get.GetSearchStoreResponse
import org.yamyamgoods.yamyam_android.network.get.GetStoreCategoryListResponseData
import org.yamyamgoods.yamyam_android.network.get.GetStoreRankingResponseData
import org.yamyamgoods.yamyam_android.network.post.PostRegularStoreMarkRequestDTO
import org.yamyamgoods.yamyam_android.network.post.PostRegularStoreMarkResponseData
import retrofit2.Call
import retrofit2.http.*

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


    // 단골 스토어로 등록 요청
    @POST("/store/scrap")
    fun postRegluarStoreMarkRequest(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") token: String?,
        @Body body: PostRegularStoreMarkRequestDTO
    ): Call<PostRegularStoreMarkResponseData>

    // 단골 스토어 해제 요청
    @DELETE("/store/scrap/{storeIdx}")
    fun deleteRegluarStoreMarkRequest(
        @Header("Content-Type") contentType: String = "application/json",
        @Header("Authorization") token: String?,
        @Path("storeIdx") storeIdx: Int
    ): Call<DeleteRegularStoreMarkResponseData>
}