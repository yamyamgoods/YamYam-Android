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
}