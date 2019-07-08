package org.yamyamgoods.yamyam_android.productdetail.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import org.yamyamgoods.yamyam_android.R

/**
 * Created By Yun Hyeok
 * on 7월 07, 2019
 */

class BookmarkCheckDialog(ctx: Context) : Dialog(ctx) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_bookmark_check)
    }
}
