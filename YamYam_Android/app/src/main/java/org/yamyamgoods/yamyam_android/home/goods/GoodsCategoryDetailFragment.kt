package org.yamyamgoods.yamyam_android.home.goods


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_goods_category_detail.*
import org.yamyamgoods.yamyam_android.SortDialog
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.HomeActivity

class GoodsCategoryDetailFragment : Fragment() {

    var sort:String? = null
    var categoryIdx:Int = -1
    var sort_flag: String? = null
    companion object {
        var instance : GoodsCategoryDetailFragment = GoodsCategoryDetailFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_goods_category_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener(){
        btn_frag_goods_category_detail_sort.setOnClickListener {
            try{
                instance.sort = sort
                setSortDialog()
            } catch(e:Exception){
            }
        }
        btn_frag_goods_category_detail_price.setOnClickListener {
            categoryIdx = instance.categoryIdx
            setOptionDialog()
        }
        btn_frag_goods_category_detail_minQuantity.setOnClickListener {
            setOptionDialog()
        }
        btn_frag_goods_category_detail_variety.setOnClickListener {
            setOptionDialog()
        }
    }

    fun setSortDialog(){
        var sortDialog = SortDialog(context as HomeActivity)
        sortDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        sortDialog.setCanceledOnTouchOutside(false)
        sortDialog.show()

        sortDialog.setOnDismissListener {
            setSortFlag()
        }
    }

    fun setSortFlag(){
        if(instance.sort_flag==null){
            tv_frag_goods_category_detail_sort.text = "ayay"
        } else {
            tv_frag_goods_category_detail_sort.text = instance.sort_flag
            sort = instance.sort_flag
        }
        //updateDataList()
    }

    fun setOptionDialog(){
        var optionDialog = OptionDialog(context as HomeActivity)
        optionDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        optionDialog.setCanceledOnTouchOutside(false)
        optionDialog.show()

//        optionDialog.setOnDismissListener {
//            setSortFlag()
//        }
    }

    fun updateDataList(){

    }
}