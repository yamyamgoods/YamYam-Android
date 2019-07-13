package org.yamyamgoods.yamyam_android.storedetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_store_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.network.ApplicationController
import org.yamyamgoods.yamyam_android.network.delete.DeleteRegularStoreMarkResponseData
import org.yamyamgoods.yamyam_android.network.post.PostRegularStoreMarkRequestDTO
import org.yamyamgoods.yamyam_android.network.post.PostRegularStoreMarkResponseData
import org.yamyamgoods.yamyam_android.storeweb.StoreWebActivity
import org.yamyamgoods.yamyam_android.util.HomeObject
import org.yamyamgoods.yamyam_android.util.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreDetailActivity : AppCompatActivity() {

    var s_idx: Int = -1
    lateinit var title: String
    lateinit var image: String
    lateinit var hashtag: List<String>
    var store_url: String? = null

    var isBookmarked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_detail)
        val transaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_act_store_detail, StoreDetailGoodsFragment()).commit()
        s_idx = intent.getIntExtra("s_idx", -1)
        StoreDetailGoodsFragment.instance.s_idx = s_idx
        title = intent.getStringExtra("title")
        image = intent.getStringExtra("image")
        hashtag = intent.getStringArrayListExtra("hashtag")
        store_url = intent.getStringExtra("store_url")

        isBookmarked = intent.getBooleanExtra("isBookmarked", false)
        btn_store_detail_act_bookmark.isSelected = isBookmarked

        setView()
        setOnClickListener()
        bookmarkButtonInit()
    }

    private fun setOnClickListener() {
        btn_store_detail_act_web_link.setOnClickListener {
            this.startActivity<StoreWebActivity>(
                "storeUrl" to store_url, "storeName" to title
            )
        }
    }

    private fun setView() {

        Glide.with(this)
            .load(image)
            .apply(RequestOptions.circleCropTransform())
            .into(iv_store_detail_act_image)
        tv_store_detail_act_store_name.text = title
        tv_act_store_detail_hashtag1.text = hashtag[0]
        if (hashtag.size > 1) {
            tv_act_store_detail_hashtag2.text = hashtag[1]
        } else {
            rl_act_store_detail_hashtag2.visibility = View.GONE
        }
    }

    private fun bookmarkButtonInit() {
        btn_store_detail_act_bookmark.setOnClickListener {
            if (isBookmarked) {
                requestBookmarkCancel()
                return@setOnClickListener
            }
            requestBookmark()
        }
    }

    private fun requestBookmark() {
        val body = PostRegularStoreMarkRequestDTO(s_idx)
        ApplicationController.networkServiceStore.postRegluarStoreMarkRequest(token = User.authorization, body = body)
            .enqueue(object :
                Callback<PostRegularStoreMarkResponseData> {
                override fun onFailure(call: Call<PostRegularStoreMarkResponseData>, t: Throwable) {
                    Log.v("Malibin Debug", "서버통신실패")
                }

                override fun onResponse(
                    call: Call<PostRegularStoreMarkResponseData>,
                    response: Response<PostRegularStoreMarkResponseData>
                ) {
                    setBookmarkOn()
                }
            })
    }

    private fun requestBookmarkCancel() {
        ApplicationController.networkServiceStore.deleteRegluarStoreMarkRequest(
            token = User.authorization,
            storeIdx = s_idx
        ).enqueue(object : Callback<DeleteRegularStoreMarkResponseData> {
            override fun onFailure(call: Call<DeleteRegularStoreMarkResponseData>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<DeleteRegularStoreMarkResponseData>,
                response: Response<DeleteRegularStoreMarkResponseData>
            ) {
                setBookmarkOff()
            }
        })
    }


    private fun setBookmarkOn() {
        btn_store_detail_act_bookmark.isSelected = true
        HomeObject.notifyStoreRankingTabChange()
        HomeObject.notifyRegularStoreTabChange()
        toast("단골 스토어가 등록되었습니다!")
    }

    private fun setBookmarkOff() {
        btn_store_detail_act_bookmark.isSelected = false
        HomeObject.notifyStoreRankingTabChange()
        HomeObject.notifyRegularStoreTabChange()
        toast("단골 스토어가 삭제되었습니다!")
    }

}
