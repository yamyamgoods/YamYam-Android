package org.yamyamgoods.yamyam_android.home.bookmark.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import org.yamyamgoods.yamyam_android.R

/**
 * Created By Yun Hyeok
 * on 7월 06, 2019
 */

class BookmarkEstimateDialog(private val ctx: Context, bookmarkIdx: Int) : Dialog(ctx) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_bookmark_estimate_check)

        window!!.setBackgroundDrawableResource(R.color.transparent)
        window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


}