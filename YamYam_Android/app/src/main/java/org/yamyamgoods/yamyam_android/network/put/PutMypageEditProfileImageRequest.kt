package org.yamyamgoods.yamyam_android.network.put

import okhttp3.MultipartBody

data class PutMypageEditProfileImageRequest (
        val img: MultipartBody.Part
)