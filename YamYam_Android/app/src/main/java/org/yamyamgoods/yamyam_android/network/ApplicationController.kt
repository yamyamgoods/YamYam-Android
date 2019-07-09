package org.yamyamgoods.yamyam_android.network

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationController: Application() {
    private val baseURL = "http://13.209.245.84:3000/"
    lateinit var networkServiceGoods: NetworkServiceGoods
    lateinit var networkServiceStore: NetworkServiceStore
    lateinit var networkServiceUser: NetworkServiceUser

    companion object{
        lateinit var instance: ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
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