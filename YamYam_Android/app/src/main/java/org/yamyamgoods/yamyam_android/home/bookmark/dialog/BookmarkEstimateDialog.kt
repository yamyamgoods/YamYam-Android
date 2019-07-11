package org.yamyamgoods.yamyam_android.home.bookmark.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import org.yamyamgoods.yamyam_android.R

/**
 * Created By Yun Hyeok
 * on 7월 06, 2019
 */

class BookmarkEstimateDialog(private val ctx: Context) : Dialog(ctx){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_bookmark_estimate_check)

        window!!.setBackgroundDrawableResource(R.color.transparent)
    }


}