package org.yamyamgoods.yamyam_android.productdetail

import org.yamyamgoods.yamyam_android.network.NetworkServiceGoods
import org.yamyamgoods.yamyam_android.network.get.GetProductDetailResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By Yun Hyeok
 * on 7월 10, 2019
 */

class ProductDetailPresenter : ProductDetailContract.Presenter {

    var userToken: String? = null

    lateinit var view: ProductDetailContract.View
    lateinit var goodsRepository: NetworkServiceGoods

    override fun start() {

    }

    override fun getProductDetailData(goodsIdx: Int) {
        goodsRepository.getProductDetailRequest(token = userToken, goodsIdx = goodsIdx).enqueue(
            object : Callback<GetProductDetailResponseData> {

                override fun onFailure(call: Call<GetProductDetailResponseData>, t: Throwable) {
                    view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
                }

                override fun onResponse(
                    call: Call<GetProductDetailResponseData>,
                    response: Response<GetProductDetailResponseData>
                ) {
                    if (response.isSuccessful) {
                        view.setProductDetailData(response.body()!!.data)
                    }
                }
            }
        )
    }
}