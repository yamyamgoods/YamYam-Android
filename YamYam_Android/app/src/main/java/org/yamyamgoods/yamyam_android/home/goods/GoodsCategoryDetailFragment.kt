package org.yamyamgoods.yamyam_android.home.goods


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import org.yamyamgoods.yamyam_android.R

class GoodsCategoryDetailFragment : Fragment() {

    lateinit var c_name:String
    companion object {
        var instance: GoodsCategoryDetailFragment = GoodsCategoryDetailFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_goods_category_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //tv_goods_category_detail_name.text = instance.c_name
    }


}
