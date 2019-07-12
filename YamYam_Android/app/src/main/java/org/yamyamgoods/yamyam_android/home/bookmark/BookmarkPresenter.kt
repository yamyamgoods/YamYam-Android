package org.yamyamgoods.yamyam_android.home.bookmark

import org.yamyamgoods.yamyam_android.network.NetworkServiceGoods
import org.yamyamgoods.yamyam_android.network.get.GetBookmarkListResponseData
import org.yamyamgoods.yamyam_android.network.get.GetDeleteBookmarkResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By Yun Hyeok
 * on 7월 12, 2019
 */

class BookmarkPresenter : BookmarkContract.Presenter {

    var userToken: String? = null
    lateinit var view: BookmarkContract.View
    lateinit var goodsRepository: NetworkServiceGoods


    override fun start() {

    }

    override fun getBookmarkData(lastIndex: Int) {
        goodsRepository.getBookmarkListRequest(token = userToken, lastIndex = lastIndex).enqueue(
            object : Callback<GetBookmarkListResponseData> {
                override fun onFailure(call: Call<GetBookmarkListResponseData>, t: Throwable) {
                    view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
                }

                override fun onResponse(
                    call: Call<GetBookmarkListResponseData>,
                    response: Response<GetBookmarkListResponseData>
                ) {
                    if (response.isSuccessful) {
                        view.addBookmarkData(response.body()!!.data)
                    }
                }
            }
        )
    }

    override fun deleteBookmark(scrapIdx: Int, position: Int) {
        goodsRepository.getDeleteBookmarkRequest(token = userToken, scrapIdx = scrapIdx).enqueue(
            object : Callback<GetDeleteBookmarkResponseData> {
                override fun onFailure(call: Call<GetDeleteBookmarkResponseData>, t: Throwable) {
                    view.showServerFailToast("서버 통신에 실패하였습니다. 인터넷 연결을 확인해주세요.", t)
                }

                override fun onResponse(
                    call: Call<GetDeleteBookmarkResponseData>,
                    response: Response<GetDeleteBookmarkResponseData>
                ) {
                    if (response.isSuccessful) {
                        view.deleteBookmarkData(position)
                    }
                }
            }
        )
    }
}
