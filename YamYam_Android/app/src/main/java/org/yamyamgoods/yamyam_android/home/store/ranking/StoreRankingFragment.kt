package org.yamyamgoods.yamyam_android.home.store.ranking

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_store_ranking.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.store.ranking.adapter.StoreRankingRVAdapter
import org.yamyamgoods.yamyam_android.util.TempData

class StoreRankingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_store_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewInit()
    }

    private fun viewInit() {
        rv_store_ranking_frag_list.apply {
            val ctx = activity!!.applicationContext
            adapter = StoreRankingRVAdapter(ctx, TempData.storeRankings())
            layoutManager = LinearLayoutManager(ctx)
            addItemDecoration(DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL))
        }
    }
}