package org.yamyamgoods.yamyam_android.productdetail.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_bookmark_check.*
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.home.bookmark.BookmarkActivity

/**
 * Created By Yun Hyeok
 * on 7월 07, 2019
 */

class BookmarkCheckDialog(private val ctx: Context) : Dialog(ctx) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_bookmark_check)

        window!!.setBackgroundDrawableResource(R.color.transparent)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        viewInit()
    }

    private fun viewInit() {
        btn_bookmark_check_dialog_nothing.setOnClickListener {
            dismiss()
        }
        btn_bookmark_check_dialog_go_bookmark.setOnClickListener {
            ctx.startActivity<BookmarkActivity>()
            dismiss()
        }
    }
}
