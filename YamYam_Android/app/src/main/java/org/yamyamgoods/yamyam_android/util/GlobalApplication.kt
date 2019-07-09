package org.yamyamgoods.yamyam_android.util

import android.app.Application
import com.kakao.auth.KakaoSDK
import org.yamyamgoods.yamyam_android.network.NetworkServiceGoods
import org.yamyamgoods.yamyam_android.network.NetworkServiceStore
import org.yamyamgoods.yamyam_android.network.NetworkServiceUser
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class GlobalApplication(): Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        // KakaoSDK 초기화
        KakaoSDK.init(KakaoSDKAdapter())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    companion object {
        private var instance: GlobalApplication? = null
        val globalApplicationContext: GlobalApplication
            get() {
                if(instance == null) {
                    throw IllegalAccessException("This Application does not inherit com.kakao.GlobalApplication")
                }
                return instance as GlobalApplication
            }
    }

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
            networkServiceGoods = retrofit.create(
                NetworkServiceGoods::class.java)
            networkServiceStore = retrofit.create(
                NetworkServiceStore::class.java)
            networkServiceUser = retrofit.create(
                NetworkServiceUser::class.java)
        }
    }
}