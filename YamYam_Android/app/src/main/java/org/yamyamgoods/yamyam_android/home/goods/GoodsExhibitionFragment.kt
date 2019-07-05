package org.yamyamgoods.yamyam_android.home.goods


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_goods_exhibition.*

import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.goods.adapter.GoodsExhibitionRecyclerViewAdapter
import org.yamyamgoods.yamyam_android.home.goods.data.GoodsExhibitionData
import org.yamyamgoods.yamyam_android.home.goods.data.GoodsExhibitionDetailData

class GoodsExhibitionFragment : Fragment() {

    var dataList : ArrayList<GoodsExhibitionData> = ArrayList()
    var dataListDetail1 : ArrayList<GoodsExhibitionDetailData> = ArrayList()
    var dataListDetail2 : ArrayList<GoodsExhibitionDetailData> = ArrayList()
    var dataListDetail3 : ArrayList<GoodsExhibitionDetailData> = ArrayList()
    lateinit var goodsExhibitionRecyclerViewAdapter: GoodsExhibitionRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_goods_exhibition, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
        getGoodsExhibitionResponse()
    }

    private fun setRecyclerView(){
        // 나중에 통신으로 대체
        dataList.add(GoodsExhibitionData(
                1, "what.png", "첫번째 기획전", 10,dataListDetail1))
        dataList.add(GoodsExhibitionData(
                2, "what.png", "두번째 기획전",20,dataListDetail2))
        dataList.add(GoodsExhibitionData(
                3, "what.png", "세번째 기획전", 30,dataListDetail3))

        dataListDetail1.add(GoodsExhibitionDetailData(
                1,"https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwiH_d725pHjAhUiy4sBHXADC-IQjRx6BAgBEAU&url=http%3A%2F%2Fwww.10x10.co.kr%2Fshopping%2Fcategory_prd.asp%3Fitemid%3D2037791&psig=AOvVaw02qK1AwBINdEju4kaY_KDk&ust=1562004655581264",
                "기획전1_제품1스토어", "기획전1_제품1이름","11,000",1.1,11))
        dataListDetail1.add(GoodsExhibitionDetailData(
                1,"https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjk2MX_5pHjAhUlG6YKHYpOB4QQjRx6BAgBEAU&url=%2Furl%3Fsa%3Di%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fm.bunjang.co.kr%252Fproducts%252F66633551%252F%253Fref%253D%2525EC%2525B9%2525B4%2525ED%252585%25258C%2525EA%2525B3%2525A0%2525EB%2525A6%2525AC%2525EC%252584%2525A0%2525ED%252583%25259D%2525EA%2525B2%2525B0%2525EA%2525B3%2525BC%26psig%3DAOvVaw02qK1AwBINdEju4kaY_KDk%26ust%3D1562004655581264&psig=AOvVaw02qK1AwBINdEju4kaY_KDk&ust=1562004655581264",
                "기획전1_제품2스토어","기획전1_제품2이름","12,000",1.2,12))
        dataListDetail1.add(GoodsExhibitionDetailData(
                1,"https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjEn7OC55HjAhWvBKYKHUfyBxkQjRx6BAgBEAU&url=%2Furl%3Fsa%3Di%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttp%253A%252F%252Fm.bunjang.co.kr%252Fproducts%252F92868982%252F%253Fref%253D%2525EC%2525B9%2525B4%2525ED%252585%25258C%2525EA%2525B3%2525A0%2525EB%2525A6%2525AC%2525EC%252584%2525A0%2525ED%252583%25259D%2525EA%2525B2%2525B0%2525EA%2525B3%2525BC%26psig%3DAOvVaw02qK1AwBINdEju4kaY_KDk%26ust%3D1562004655581264&psig=AOvVaw02qK1AwBINdEju4kaY_KDk&ust=1562004655581264",
                "기획전1_제품3스토어","기획전1_제품3이름","13,000",1.3,13))
        dataListDetail2.add(GoodsExhibitionDetailData(
                2,"https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwiH_d725pHjAhUiy4sBHXADC-IQjRx6BAgBEAU&url=http%3A%2F%2Fwww.10x10.co.kr%2Fshopping%2Fcategory_prd.asp%3Fitemid%3D2037791&psig=AOvVaw02qK1AwBINdEju4kaY_KDk&ust=1562004655581264",
                "기획전2_제품1스토어", "기획전2_제품1이름","21,000",2.1,21))
        dataListDetail2.add(GoodsExhibitionDetailData(
                2,"https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjk2MX_5pHjAhUlG6YKHYpOB4QQjRx6BAgBEAU&url=%2Furl%3Fsa%3Di%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fm.bunjang.co.kr%252Fproducts%252F66633551%252F%253Fref%253D%2525EC%2525B9%2525B4%2525ED%252585%25258C%2525EA%2525B3%2525A0%2525EB%2525A6%2525AC%2525EC%252584%2525A0%2525ED%252583%25259D%2525EA%2525B2%2525B0%2525EA%2525B3%2525BC%26psig%3DAOvVaw02qK1AwBINdEju4kaY_KDk%26ust%3D1562004655581264&psig=AOvVaw02qK1AwBINdEju4kaY_KDk&ust=1562004655581264",
                "기획전2_제품2스토어","기획전2_제품2이름","22,000",2.2,22))
        dataListDetail2.add(GoodsExhibitionDetailData(
                2,"https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjEn7OC55HjAhWvBKYKHUfyBxkQjRx6BAgBEAU&url=%2Furl%3Fsa%3Di%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttp%253A%252F%252Fm.bunjang.co.kr%252Fproducts%252F92868982%252F%253Fref%253D%2525EC%2525B9%2525B4%2525ED%252585%25258C%2525EA%2525B3%2525A0%2525EB%2525A6%2525AC%2525EC%252584%2525A0%2525ED%252583%25259D%2525EA%2525B2%2525B0%2525EA%2525B3%2525BC%26psig%3DAOvVaw02qK1AwBINdEju4kaY_KDk%26ust%3D1562004655581264&psig=AOvVaw02qK1AwBINdEju4kaY_KDk&ust=1562004655581264",
                "기획전2_제품3스토어","기획전2_제품3이름","23,000",2.3,23))
        dataListDetail3.add(GoodsExhibitionDetailData(
                3,"https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwiH_d725pHjAhUiy4sBHXADC-IQjRx6BAgBEAU&url=http%3A%2F%2Fwww.10x10.co.kr%2Fshopping%2Fcategory_prd.asp%3Fitemid%3D2037791&psig=AOvVaw02qK1AwBINdEju4kaY_KDk&ust=1562004655581264",
                "기획전3_제품1스토어", "기획전3_제품1이름","31,000",3.1,31))
        dataListDetail3.add(GoodsExhibitionDetailData(
                3,"https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjk2MX_5pHjAhUlG6YKHYpOB4QQjRx6BAgBEAU&url=%2Furl%3Fsa%3Di%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fm.bunjang.co.kr%252Fproducts%252F66633551%252F%253Fref%253D%2525EC%2525B9%2525B4%2525ED%252585%25258C%2525EA%2525B3%2525A0%2525EB%2525A6%2525AC%2525EC%252584%2525A0%2525ED%252583%25259D%2525EA%2525B2%2525B0%2525EA%2525B3%2525BC%26psig%3DAOvVaw02qK1AwBINdEju4kaY_KDk%26ust%3D1562004655581264&psig=AOvVaw02qK1AwBINdEju4kaY_KDk&ust=1562004655581264",
                "기획전3_제품2스토어","기획전3_제품2이름","32,000",3.2,32))
        dataListDetail3.add(GoodsExhibitionDetailData(
                3,"https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjEn7OC55HjAhWvBKYKHUfyBxkQjRx6BAgBEAU&url=%2Furl%3Fsa%3Di%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttp%253A%252F%252Fm.bunjang.co.kr%252Fproducts%252F92868982%252F%253Fref%253D%2525EC%2525B9%2525B4%2525ED%252585%25258C%2525EA%2525B3%2525A0%2525EB%2525A6%2525AC%2525EC%252584%2525A0%2525ED%252583%25259D%2525EA%2525B2%2525B0%2525EA%2525B3%2525BC%26psig%3DAOvVaw02qK1AwBINdEju4kaY_KDk%26ust%3D1562004655581264&psig=AOvVaw02qK1AwBINdEju4kaY_KDk&ust=1562004655581264",
                "기획전3_제품3스토어","기획전3_제품3이름","33,000",3.3,33))
        goodsExhibitionRecyclerViewAdapter = GoodsExhibitionRecyclerViewAdapter(context!!,dataList)
        rv_frag_goods_exhibition_list.adapter = goodsExhibitionRecyclerViewAdapter
        rv_frag_goods_exhibition_list.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false) as RecyclerView.LayoutManager?
    }

    private fun getGoodsExhibitionResponse(){
        //통신
    }
}
