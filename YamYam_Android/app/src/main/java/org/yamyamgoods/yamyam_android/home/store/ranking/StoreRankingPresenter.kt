package org.yamyamgoods.yamyam_android.home.store.ranking

import android.util.Log
import org.yamyamgoods.yamyam_android.network.NetworkServiceStore
import org.yamyamgoods.yamyam_android.network.delete.DeleteRegularStoreMarkResponseData
import org.yamyamgoods.yamyam_android.network.get.GetStoreCategoryListResponseData
import org.yamyamgoods.yamyam_android.network.get.GetStoreRankingResponseData
import org.yamyamgoods.yamyam_android.network.post.PostRegularStoreMarkRequestDTO
import org.yamyamgoods.yamyam_android.network.post.PostRegularStoreMarkResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By Yun Hyeok
 * on 7월 11, 2019
 */
class StoreRankingPresenter : StoreRankingContract.Presenter {

    var userToken: String? = null

    lateinit var view: StoreRankingContract.View
    lateinit var storeRepository: NetworkServiceStore

    override fun start() {

    }

    override fun getStoreCategory() {
        storeRepository.getStoreCategoryListRequest().enqueue(object : Callback<GetStoreCategoryListResponseData> {

            override fun onFailure(call: Call<GetStoreCategoryListResponseData>, t: Throwable) {
                view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
            }

            override fun onResponse(
                call: Call<GetStoreCategoryListResponseData>,
                response: Response<GetStoreCategoryListResponseData>
            ) {
                if (response.isSuccessful) {
                    view.setStoreCategory(response.body()!!.data)
                }
            }
        })
    }

    override fun getStoreRankingList(storeCategoryIdx: Int, lastIdx: Int) {
        storeRepository.getStoreRankingRequest(
            token = userToken,
            lastIndex = lastIdx,
            storeCategoryIdx = storeCategoryIdx
        ).enqueue(object : Callback<GetStoreRankingResponseData> {

            override fun onFailure(call: Call<GetStoreRankingResponseData>, t: Throwable) {
                view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
            }

            override fun onResponse(
                call: Call<GetStoreRankingResponseData>,
                response: Response<GetStoreRankingResponseData>
            ) {
                Log.v("Malibin Debug", "서버응답 : ${response.message()}")
                if (response.isSuccessful) {
                    Log.v("Malibin Debug", "서버응답 : ${response.body()!!}")
                    view.setStoreRankingList(response.body()!!.data)
                }
            }
        })
    }

    override fun regularStoreMarkRequest(position: Int, body: PostRegularStoreMarkRequestDTO) {
        storeRepository.postRegluarStoreMarkRequest(token = userToken, body = body).enqueue(
            object : Callback<PostRegularStoreMarkResponseData> {

                override fun onFailure(call: Call<PostRegularStoreMarkResponseData>, t: Throwable) {
                    view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
                }

                override fun onResponse(
                    call: Call<PostRegularStoreMarkResponseData>,
                    response: Response<PostRegularStoreMarkResponseData>
                ) {
                    if (response.isSuccessful) {
                        view.setRegularStoreMarked(position)
                        return
                    }
                    view.showLoginRequiredDialog()
                }
            })
    }

    override fun regularStoreCancelRequest(position: Int, storeIdx: Int) {
        storeRepository.deleteRegluarStoreMarkRequest(token = userToken, storeIdx = storeIdx).enqueue(
            object : Callback<DeleteRegularStoreMarkResponseData> {

                override fun onFailure(call: Call<DeleteRegularStoreMarkResponseData>, t: Throwable) {
                    view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
                }

                override fun onResponse(
                    call: Call<DeleteRegularStoreMarkResponseData>,
                    response: Response<DeleteRegularStoreMarkResponseData>
                ) {
                    if (response.isSuccessful) {
                        view.setRegularStoreCanceled(position)
                        return
                    }
                    view.showLoginRequiredDialog()
                }
            })
    }
}