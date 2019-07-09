package org.yamyamgoods.yamyam_android.home.best.goods

import org.yamyamgoods.yamyam_android.network.NetworkServiceGoods
import org.yamyamgoods.yamyam_android.network.get.GetBestGoodsItemResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By Yun Hyeok
 * on 7월 09, 2019
 */

class BestGoodsPresenter : BestGoodsContract.Presenter {

    lateinit var view: BestGoodsContract.View
    lateinit var goodsRepository: NetworkServiceGoods

    override fun start() {

    }

    override fun getBestGoodsData(token: String?, lastIndex: Int) {
        goodsRepository.getBestGoodsItemRequest(token = token, lastIndex = lastIndex).enqueue(
                object : Callback<GetBestGoodsItemResponseData> {

                    override fun onFailure(call: Call<GetBestGoodsItemResponseData>, t: Throwable) {
                        view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
                    }

                    override fun onResponse(call: Call<GetBestGoodsItemResponseData>, response: Response<GetBestGoodsItemResponseData>) {
                        // 2xx
                        if (response.isSuccessful) {
                            view.addBestGoodsList(response.body()!!.data)
                            return
                        }
                        response.errorBody()?.let{
                            view.showInvalidAccessToast(it.string())
                        }
                    }
                })
    }
}