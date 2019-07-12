package org.yamyamgoods.yamyam_android.mypage.recent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_recently_viewed_products.*
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.mypage.adapter.MypageProductRVAdapter
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.NetworkServiceUser
import org.yamyamgoods.yamyam_android.network.get.GetMypageRecentlyViewedProductsResponse
import org.yamyamgoods.yamyam_android.network.get.RecentlyViewedProducts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class RecentlyViewedProductsActivity : AppCompatActivity() {

    val networkService: NetworkServiceUser by lazy {
        ApplicationController.networkServiceUser
    }

    lateinit var mypageProductRVAdapter : MypageProductRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recently_viewed_products)

       // viewInit()
        getMypageRecentlyReviewedProductsRequest(-1)

    }

//    private fun viewInit() {
//        rv_recently_viewed_products_product_list.apply {
//            adapter = RecentlyViewedProductsRVAdapter(this@RecentlyViewedProductsActivity, TempData.mypageProducts())
//            layoutManager = GridLayoutManager(this@RecentlyViewedProductsActivity, 3)
//        }
//    }

    private fun getMypageRecentlyReviewedProductsRequest(lastIndex: Int){
        networkService.getMypageRecentlyViewedProductsResponse(
                "application/json",
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWR4IjoxLCJpYXQiOjE1NjIzMTUzNjYsImV4cCI6MTU2MzYyOTM2Nn0.ZkDGasoDPHTrGvy7yFOT9cPjTQ7gnnUOqekY_zYrAuc",
                lastIndex
        ).enqueue(object: Callback<GetMypageRecentlyViewedProductsResponse> {
            override fun onFailure(call: Call<GetMypageRecentlyViewedProductsResponse>, t: Throwable) {
                Log.e("현주", t.toString())
            }

            override fun onResponse(call: Call<GetMypageRecentlyViewedProductsResponse>, response: Response<GetMypageRecentlyViewedProductsResponse>) {
                if (response.isSuccessful){
                    Log.v("현주", "최근 본 상품 response : ${response.body()}")
                    response.body()?.let{
                        var tmp: ArrayList<RecentlyViewedProducts> = response.body()!!.data!!
                        mypageProductRVAdapter = MypageProductRVAdapter(this@RecentlyViewedProductsActivity!!, tmp)
                        rv_recently_viewed_products_product_list.apply {
                            adapter = MypageProductRVAdapter (this@RecentlyViewedProductsActivity, tmp)
                            layoutManager = GridLayoutManager(this@RecentlyViewedProductsActivity, 3)
                        }
                        //mypageProductRVAdapter.dataList = tmp
                        mypageProductRVAdapter.notifyDataSetChanged()
                    }
                }

                response.errorBody()?.let{
                    val type: Type = object : TypeToken<GetMypageRecentlyViewedProductsResponse>() {}.type
                    val gson: Gson = GsonBuilder().create()
                    val responseJson: GetMypageRecentlyViewedProductsResponse = gson.fromJson(it.string().toString(), type)

                    if (response.code() == 401) {
                        if (responseJson.message == "jwt must be provided")
                            toast("로그인을 해주세요.")
                        if (responseJson.message == "jwt expired")
                            toast("로그인이 만료되었습니다.")
                    }
                }
            }
        })
    }

    // 페이징하는 부분 onScrolled
    /*fun RecyclerView.onScrollToEnd(linearLayoutManager: LinearLayoutManager, onScrollNearEnd: (Unit) -> Unit)  = addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            if (linearLayoutManager.childCount + linearLayoutManager.findFirstVisibleItemPosition() >= linearLayoutManager.itemCount - 5) {  //if near fifth item from end
                onScrollNearEnd(Unit)
            }
        }
    })*/

}
