package org.yamyamgoods.yamyam_android.network

import org.yamyamgoods.yamyam_android.network.get.GetSearchStoreResponse
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
            @Path("storeName") storeName:String,
            @Path("order") order: Int,
            @Query("searchAfter") searchAfter: List<Int>
    ): Call<GetSearchStoreResponse>
}