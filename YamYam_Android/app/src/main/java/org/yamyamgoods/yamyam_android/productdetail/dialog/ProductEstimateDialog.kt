package org.yamyamgoods.yamyam_android.productdetail.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import org.yamyamgoods.yamyam_android.R

/**
 * Created By Yun Hyeok
 * on 7월 06, 2019
 */

class ProductEstimateDialog : Dialog {

    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, themeResId: Int) : super(ctx, themeResId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_product_detail_estimate)
    }
}