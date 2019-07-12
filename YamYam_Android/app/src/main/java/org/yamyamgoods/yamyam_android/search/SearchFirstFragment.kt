package org.yamyamgoods.yamyam_android.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_search_first.*

import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.search.goods.SearchResultGoodsFragment
import org.yamyamgoods.yamyam_android.search.store.SearchResultStoreFragment

class SearchFirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_search_first, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        val transaction: FragmentTransaction = (context as SearchActivity).supportFragmentManager.beginTransaction()

        recommend1.setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend1.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = "스티커"
            SearchResultGoodsFragment.instance.goodsName = tv_recommend1.text.toString()
            et_search_act.setText(tv_recommend1.text.toString())
        }
        (recommend2 as RelativeLayout).setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend2.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = tv_recommend2.text.toString()
            SearchResultGoodsFragment.instance.goodsName = tv_recommend2.text.toString()
        }
        (recommend3 as RelativeLayout).setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend3.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = tv_recommend3.text.toString()
            SearchResultGoodsFragment.instance.goodsName = tv_recommend3.text.toString()
        }
        (recommend4 as RelativeLayout).setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend4.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = tv_recommend4.text.toString()
            SearchResultGoodsFragment.instance.goodsName = tv_recommend4.text.toString()
        }
        (recommend5 as RelativeLayout).setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend5.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = tv_recommend5.text.toString()
            SearchResultGoodsFragment.instance.goodsName = tv_recommend5.text.toString()
        }
        (recommend6 as RelativeLayout).setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend6.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = tv_recommend6.text.toString()
            SearchResultGoodsFragment.instance.goodsName = tv_recommend6.text.toString()
        }
        (recommend7 as RelativeLayout).setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend7.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = tv_recommend7.text.toString()
            SearchResultGoodsFragment.instance.goodsName = tv_recommend7.text.toString()
        }
        (recommend8 as RelativeLayout).setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend8.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = tv_recommend8.text.toString()
            SearchResultGoodsFragment.instance.goodsName = tv_recommend8.text.toString()
        }
        (recommend9 as RelativeLayout).setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend9.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = tv_recommend9.text.toString()
            SearchResultGoodsFragment.instance.goodsName = tv_recommend9.text.toString()
        }
        (recommend10 as RelativeLayout).setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend10.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = tv_recommend10.text.toString()
            SearchResultGoodsFragment.instance.goodsName = tv_recommend10.text.toString()
        }
        (recommend11 as RelativeLayout).setOnClickListener {
            SearchActivity.instance.clicksearch =  tv_recommend11.text.toString()
            transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
            SearchResultStoreFragment.instance.storeName = tv_recommend11.text.toString()
            SearchResultGoodsFragment.instance.goodsName = tv_recommend11.text.toString()
        }
    }
}
