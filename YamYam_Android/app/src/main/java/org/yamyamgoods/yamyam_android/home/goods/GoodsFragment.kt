package org.yamyamgoods.yamyam_android.home.goods

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_goods.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.goods.adapter.GoodsCategoryRecyclerViewAdapter
import org.yamyamgoods.yamyam_android.home.goods.data.GoodsCategoryData
import org.yamyamgoods.yamyam_android.home.HomeActivity

class GoodsFragment : Fragment(){
    var dataList: ArrayList<GoodsCategoryData> = ArrayList()
    lateinit var goodsCategoryRecyclerViewAdapter: GoodsCategoryRecyclerViewAdapter

//    interface IOnBackPressed{
//        fun onBackPressed():Boolean
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
        //Goods탭 처음에 기획전 화면을 띄우도록
        val transaction: FragmentTransaction = (context as HomeActivity).supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_goods_fragment_frag, GoodsExhibitionFragment()).commit()
    }

    private fun setRecyclerView(){
        dataList.add(GoodsCategoryData(1,"스티커"))
        dataList.add(GoodsCategoryData(2,"명함"))
        dataList.add(GoodsCategoryData(3,"팬시"))
        dataList.add(GoodsCategoryData(4,"포스터"))
        dataList.add(GoodsCategoryData(5,"버튼"))
        dataList.add(GoodsCategoryData(6,"카드"))
        goodsCategoryRecyclerViewAdapter = GoodsCategoryRecyclerViewAdapter(context!!,dataList)
        rv_goods_frag_category.adapter = goodsCategoryRecyclerViewAdapter
        rv_goods_frag_category.layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL,false) as RecyclerView.LayoutManager?
    }

//    override fun onBackPressed() {
//        (context as HomeActivity).onBackPressed()
//    }
}