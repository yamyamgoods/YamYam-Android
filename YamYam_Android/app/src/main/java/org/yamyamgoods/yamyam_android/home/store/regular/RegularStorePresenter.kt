package org.yamyamgoods.yamyam_android.home.store.regular

import org.yamyamgoods.yamyam_android.network.NetworkServiceStore
import org.yamyamgoods.yamyam_android.network.get.GetRegularStoreResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By Yun Hyeok
 * on 7월 11, 2019
 */

class RegularStorePresenter : RegularStoreContract.Presenter {

    var userToken: String? = null

    lateinit var view: RegularStoreFragment
    lateinit var storeRepository: NetworkServiceStore

    override fun start() {

    }

    override fun getRegularStoreList(lastIdx: Int) {
        storeRepository.getRegularStoreRequest(token = userToken, lastIndex = lastIdx).enqueue(
            object : Callback<GetRegularStoreResponseData> {
                override fun onFailure(call: Call<GetRegularStoreResponseData>, t: Throwable) {
                    view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
                }

                override fun onResponse(
                    call: Call<GetRegularStoreResponseData>,
                    response: Response<GetRegularStoreResponseData>
                ) {
                    if (response.isSuccessful) {
                        view.addRegularStoreList(response.body()!!.data)
                        return
                    }
                    view.setNoRegularStoreList()
                }
            }
        )
    }
}