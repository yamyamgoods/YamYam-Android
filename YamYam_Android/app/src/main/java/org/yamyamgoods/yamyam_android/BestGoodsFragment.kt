package org.yamyamgoods.yamyam_android

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_best_goods.*

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
            adapter = BestGoodsRVAdapter(ctx, tempData())
            layoutManager = GridLayoutManager(ctx, 2)
        }
    }

    private fun tempData(): List<BestGoodsItem> {
        val result = ArrayList<BestGoodsItem>()
        result.add(
                BestGoodsItem(
                        0, false, R.drawable.img_goods1,
                        "레드프린팅&프레스", "도무송 스티커",
                        32900, 4.8, 10, 300)
        )
        result.add(
                BestGoodsItem(
                        0, true, R.drawable.img_goods2,
                        "레드프린팅&프레스", "도무송 스티커",
                        32900, 4.8, 10, 300)
        )
        result.add(
                BestGoodsItem(
                        0, true, R.drawable.img_goods3,
                        "레드프린팅&프레스", "도무송 스티커",
                        32900, 4.8, 10, 300)
        )
        result.add(
                BestGoodsItem(
                        0, false, R.drawable.img_goods4,
                        "레드프린팅&프레스", "도무송 스티커",
                        32900, 4.8, 10, 300)
        )
        result.add(
                BestGoodsItem(
                        0, false, R.drawable.img_goods1,
                        "레드프린팅&프레스", "도무송 스티커",
                        32900, 4.8, 10, 300)
        )
        result.add(
                BestGoodsItem(
                        0, true, R.drawable.img_goods2,
                        "레드프린팅&프레스", "도무송 스티커",
                        32900, 4.8, 10, 300)
        )
        result.add(
                BestGoodsItem(
                        0, false, R.drawable.img_goods3,
                        "레드프린팅&프레스", "도무송 스티커",
                        32900, 4.8, 10, 300)
        )
        return result
    }
}