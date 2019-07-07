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
    lateinit var goodsExhibitionRecyclerViewAdapter: GoodsExhibitionRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_goods_exhibition, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
        //getGoodsExhibitionResponse()
    }

    private fun setRecyclerView(){
        val ctx = activity!!.applicationContext
        // 나중에 통신으로 대체
        dataList.add(GoodsExhibitionData(1, R.drawable.exhibition_thumnail_1, "유니크한 나만의 키링✨/소량제작 기획전\uD83D\uDC96/","투명 뽀짝 간즤 아크릴 ✨/다 하나쯤은 갖고 있잖아요"))
        dataList.add(GoodsExhibitionData(2,R.drawable.exhibition_thumnail_2,"뱃지 만들기,/얌얌과 함께라면/걱정없어요\uD83D\uDE4B\u200D♂","투명 뽀짝 간즤 아크릴 ✨/다 하나쯤은 갖고 있잖아요"))
        dataList.add(GoodsExhibitionData(3, R.drawable.exhibition_thumnail_3, "작고작은 파우치도💖/어쩌구 파우치도 오케!/","투명 뽀짝 간즤 아크릴 ✨/다 하나쯤은 갖고 있잖아요"))


        //goodsExhibitionRecyclerViewAdapter = GoodsExhibitionRecyclerViewAdapter(context!!,dataList)
        rv_frag_goods_exhibition_list.apply{
            adapter = GoodsExhibitionRecyclerViewAdapter(dataList)
            rv_frag_goods_exhibition_list.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL,false)
        }
    }

    private fun getGoodsExhibitionResponse(){
        //통신
    }
}
