package org.yamyamgoods.yamyam_android

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_best_goods.*
import org.yamyamgoods.yamyam_android.util.TempData

class BestGoodsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_best_goods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewInit()
    }

    private fun viewInit() {
        val ctx = activity!!.applicationContext

        rv_best_goods_frag_list.apply{
            adapter = BestGoodsRVAdapter(ctx, TempData.bestGoods())
            layoutManager = GridLayoutManager(ctx, 2)
        }
    }

}