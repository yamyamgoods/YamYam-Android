package org.yamyamgoods.yamyam_android.network

import org.yamyamgoods.yamyam_android.network.get.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkServiceGoods {

    //굿즈탭 전체보기
    @GET("/goods")
    fun getGoodsResponse(
            @Header("Content-Type") content_type: String,
            @Header("Authorization") token: String
    ): Call<GetGoodsTabResponse>

    //굿즈탭 카테고리 페이징
    @GET("/goods/category/{lastIndex}")
    fun getGoodsCategoryPagingResponse(
            @Header("Content-Type") content_type: String,
            @Header("Authorization") token: String,
            @Path("lastIndex") lastIndex: Int
    ): Call<GetGoodsCategoryPagingResponse>

    //얌얌추천 기획전 굿즈 모두보기
    @GET("/goods/exhibition/{exhibitionIdx}/{lastIndex}")
    fun getExhibitionDetailResponse(
            @Header("Content-Type") content_type: String,
            @Header("Authorization") token: String,
            @Path("exhibitionIdx") exhibitionIdx: Int,
            @Path("lastIndex") lastIndex: Int
    ): Call<GetExhibitionDetailResponse>

    //카테고리에 따른 굿즈 모두보기
    @GET("/goods/category/{goodsCategoryIdx}/{order}/{lastIdx}")
    fun getCategoryDetailResponse(
            @Header("Content-Type") content_type: String,
            @Header("Authorization") token: String,
            @Path("goodsCategoryIdx") goodsCategoryIdx: Int,
            @Path("order") order: Int,
            @Path("lastIndex") lastIndex: Int,
            @Query("priceStart") priceStart: Int,
            @Query("priceEnd") priceEnd: Int,
            @Query("minAmount") minAmount: Int,
            @Query("options") options: List<Int>
    ): Call<GetCategoryDetailResponse>

    //가격 범위 보기
    @GET("/goods/category/{goodsCategoryIdx}/priceRange")
    fun getPriceRangeResponse(
            @Header("Content-Type") content_type: String,
            @Path("goodsCategoryIdX") goodsCategoryIdx: Int,
            @Query("minAmount") minAmount: Int
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
            @Header("Authorization") token: String,
            @Path("lastIndex") lastIndex: Int
    )
}