package org.yamyamgoods.yamyam_android.productdetail.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import org.yamyamgoods.yamyam_android.R

/**
 * Created By Yun Hyeok
 * on 7월 08, 2019
 */

class ProductDetailImageFragment : Fragment() {

    lateinit var imageUrl: String
    lateinit var imageView : ImageView

    companion object {
        @JvmStatic
        fun getInstance(imageUrl: String) =
                ProductDetailImageFragment().apply {
                    arguments = Bundle().apply {
                        putString("goods_review_img", imageUrl)
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_product_detail_image, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        variableInit(view)
        insertImage()
    }

    private fun variableInit(view: View){
        arguments?.let{
            imageUrl = it.getString("goods_review_img")!!
        }
        imageView = view.findViewById(R.id.iv_product_detail_frag_image)
    }

    private fun insertImage() = Glide
            .with(this)
            .load(imageUrl)
            .into(imageView)
}