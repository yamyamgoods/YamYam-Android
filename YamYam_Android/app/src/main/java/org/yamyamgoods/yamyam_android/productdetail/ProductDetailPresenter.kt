package org.yamyamgoods.yamyam_android.productdetail

import android.util.Log
import org.yamyamgoods.yamyam_android.network.NetworkServiceGoods
import org.yamyamgoods.yamyam_android.network.delete.DeleteBookmarkResponseData
import org.yamyamgoods.yamyam_android.network.get.GetProductDetailResponseData
import org.yamyamgoods.yamyam_android.network.get.GetProductOptionsResponseData
import org.yamyamgoods.yamyam_android.network.post.PostBookmarkRequestDTO
import org.yamyamgoods.yamyam_android.network.post.PostBookmarkResponseData
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

    override fun bookmarkRequest(body: PostBookmarkRequestDTO) {
        goodsRepository.postBookmarkRequest(token = userToken, body = body).enqueue(
            object : Callback<PostBookmarkResponseData> {
                override fun onFailure(call: Call<PostBookmarkResponseData>, t: Throwable) {
                    view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
                }

                override fun onResponse(
                    call: Call<PostBookmarkResponseData>,
                    response: Response<PostBookmarkResponseData>
                ) {
                    if (response.isSuccessful) {
                        Log.v("Malibin Debug", "북마크 성공")
                        return
                    }
                    Log.v("Malibin Debug", "북마크 실패")
                }

            }
        )
    }

    override fun bookmarkCancelRequest(goodsIdx: Int) {
        goodsRepository.deleteBookmarkRequest(token = userToken, goodsIdx = goodsIdx).enqueue(
            object : Callback<DeleteBookmarkResponseData> {
                override fun onFailure(call: Call<DeleteBookmarkResponseData>, t: Throwable) {
                    view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
                }

                override fun onResponse(
                    call: Call<DeleteBookmarkResponseData>,
                    response: Response<DeleteBookmarkResponseData>
                ) {
                    if (response.isSuccessful) {
                        Log.v("Malibin Debug", "북마크 해제 성공")
                        return
                    }
                    Log.v("Malibin Debug", "북마크 해제 실패")
                }
            }

        )
    }

    override fun getProductOptionData(goodsIdx: Int) {
        goodsRepository.getProductOptionsRequest(token = userToken, goodsIdx = goodsIdx).enqueue(
            object : Callback<GetProductOptionsResponseData> {
                override fun onFailure(call: Call<GetProductOptionsResponseData>, t: Throwable) {
                    view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
                }

                override fun onResponse(
                    call: Call<GetProductOptionsResponseData>,
                    response: Response<GetProductOptionsResponseData>
                ) {
                    if (response.isSuccessful) {
                        view.setProductOptionData(response.body()!!.data)
                    }
                }
            }
        )
    }
}