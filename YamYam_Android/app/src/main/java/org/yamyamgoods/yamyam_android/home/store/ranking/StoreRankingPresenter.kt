package org.yamyamgoods.yamyam_android.home.store.ranking

import org.yamyamgoods.yamyam_android.network.NetworkServiceStore
import org.yamyamgoods.yamyam_android.network.get.GetStoreCategoryListResponseData
import org.yamyamgoods.yamyam_android.network.get.GetStoreRankingResponseData
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

            }
        })
    }
}