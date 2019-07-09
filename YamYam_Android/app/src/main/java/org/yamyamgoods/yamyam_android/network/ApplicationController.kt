package org.yamyamgoods.yamyam_android.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApplicationController{
    private val baseURL = "http://13.209.245.84:3000/"
    lateinit var networkServiceGoods: NetworkServiceGoods
    lateinit var networkServiceStore: NetworkServiceStore
    lateinit var networkServiceUser: NetworkServiceUser

    init {
        buildNetwork()
    }

    fun buildNetwork(){
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        networkServiceGoods = retrofit.create(NetworkServiceGoods::class.java)
        networkServiceStore = retrofit.create(NetworkServiceStore::class.java)
        networkServiceUser = retrofit.create(NetworkServiceUser::class.java)
    }
}

