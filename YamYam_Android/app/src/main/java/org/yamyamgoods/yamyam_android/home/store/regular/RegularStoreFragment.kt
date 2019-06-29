package org.yamyamgoods.yamyam_android.home.store.regular

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_regular_store.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.store.regular.adapter.RegularStoreRVAdapter
import org.yamyamgoods.yamyam_android.util.TempData

class RegularStoreFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_regular_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewInit()
    }

    private fun viewInit(){
        rv_regular_store_frag_list.apply {
            val ctx = activity!!.applicationContext
            adapter = RegularStoreRVAdapter(ctx, TempData.regularStores())
            layoutManager = LinearLayoutManager(ctx)
        }
    }
}