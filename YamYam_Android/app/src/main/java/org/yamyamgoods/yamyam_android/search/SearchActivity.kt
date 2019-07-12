package org.yamyamgoods.yamyam_android.search

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_search.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.search.goods.SearchResultGoodsFragment
import org.yamyamgoods.yamyam_android.search.store.SearchResultStoreFragment

class SearchActivity : AppCompatActivity() {

    var clicksearch: String = ""
    companion object {
        var instance : SearchActivity = SearchActivity()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val transaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_search_act_pager, SearchFirstFragment()).commit()
        setOnKeyListener()
        setOnClickListener()
    }

    override fun onResume() {
        super.onResume()
        clicksearch = instance.clicksearch
        Log.e("**searchAct", "clicksearch2 : $clicksearch")
    }


    fun setOnKeyListener() {
        et_search_act.setOnKeyListener { v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_ENTER && (event.action == KeyEvent.ACTION_DOWN)) {
                val searchText: String = et_search_act.text.toString()
                if(searchText != null) {
                    Log.e("**searchAct", "et_search_act : "+searchText)
                    val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fl_search_act_pager, SearchResultFragment()).commit()
                    SearchResultStoreFragment.instance.storeName = et_search_act.text.toString()
                    SearchResultGoodsFragment.instance.goodsName = et_search_act.text.toString()
                }
                return@setOnKeyListener  true
            }
            return@setOnKeyListener false
        }
    }

    private fun setOnClickListener() {
        btn_search_act_searchreset.setOnClickListener {
            et_search_act.text = null
        }
    }
}
