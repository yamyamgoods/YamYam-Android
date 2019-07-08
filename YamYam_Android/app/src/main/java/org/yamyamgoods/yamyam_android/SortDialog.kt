package org.yamyamgoods.yamyam_android

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.dialog_sort.*
import org.yamyamgoods.yamyam_android.home.HomeActivity
import org.yamyamgoods.yamyam_android.home.goods.GoodsCategoryDetailFragment

class SortDialog(context:Context) : Dialog(context), View.OnClickListener {

    var sort: String?=null
    var sort_flag: String? = null

    override fun onClick(v: View?) {
        try{
            dismiss()
        } catch(e:Exception){
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_sort)
        sort = GoodsCategoryDetailFragment.instance.sort
        setView()
        setOnClickListner()
    }

    fun setView(){
        if(sort=="인기순") {
            tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.MainYellow))
            tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.darkgray))
            iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
            iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            tv_dialog_sort_popular.text = "아진짜조같네"
            sort_flag="인기순"
        } else if(sort=="고가순") {
            tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.MainYellow))
            tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.darkgray))
            iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
            iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            sort_flag="고가순"
        } else if(sort=="저가순"){
            tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.MainYellow))
            iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
            sort_flag="저가순"
        }
    }
    fun setOnClickListner(){
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
            sort_flag="인기순"
        }
        rl_dialog_sort_price_high.setOnClickListener {
            tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.MainYellow))
            tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.darkgray))
            iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
            iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            sort_flag="고가순"
        }
        rl_dialog_sort_price_low.setOnClickListener{
            tv_dialog_sort_popular.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_high.setTextColor(context.resources.getColor(R.color.darkgray))
            tv_dialog_sort_price_low.setTextColor(context.resources.getColor(R.color.MainYellow))
            iv_dialog_sort_popular.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_high.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkk))
            iv_dialog_sort_price_low.setImageDrawable(context.resources.getDrawable(R.drawable.icon_checkkky))
            sort_flag="저가순"
        }

        btn_dialog_sort_save.setOnClickListener {
            GoodsCategoryDetailFragment.instance.sort_flag = sort_flag
            dismiss()
        }


    }
}
