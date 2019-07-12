package org.yamyamgoods.yamyam_android.search.goods

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.dialog_sort.*
import org.yamyamgoods.yamyam_android.R

class SortSearchGoodsDialog(context: Context) : Dialog(context), View.OnClickListener {

    var sort: String? = null
    var sort_flag: String? = null

    override fun onClick(v: View?) {
        try {
            dismiss()
        } catch (e: Exception) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_sort)
        sort = SearchResultGoodsFragment.instance.sort
        setView()
        setOnClickListner()
    }

    fun setView() {
        when (sort) {
            "인기순" -> {
                tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.MainYellow))
                tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.darkgray))
                tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.darkgray))
                iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
                iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
                iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
                sort_flag = "인기순"
            }
            "고가순" -> {
                tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.darkgray))
                tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.MainYellow))
                tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.darkgray))
                iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
                iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
                iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
                sort_flag = "고가순"
            }
            "저가순" -> {
                tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.darkgray))
                tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.darkgray))
                tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.MainYellow))
                iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
                iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
                iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
                sort_flag = "저가순"
            }
        }
    }

    fun setOnClickListner() {
        btn_dialog_sort_close.setOnClickListener {
            dismiss()
        }
        rl_dialog_sort_popular.setOnClickListener {
            tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.MainYellow))
            tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.MainYellow))
            tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.darkgray))
            iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
            iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            sort_flag = "인기순"
        }
        rl_dialog_sort_price_high.setOnClickListener {
            tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.MainYellow))
            tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.darkgray))
            iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
            iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            sort_flag = "고가순"
        }
        rl_dialog_sort_price_low.setOnClickListener {
            tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.MainYellow))
            iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
            sort_flag = "저가순"
        }

        btn_dialog_sort_confirm.setOnClickListener {
            SearchResultGoodsFragment.instance.sort_flag = sort_flag
            dismiss()
        }
    }
}
