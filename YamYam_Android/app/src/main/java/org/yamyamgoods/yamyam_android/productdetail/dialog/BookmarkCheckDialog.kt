package org.yamyamgoods.yamyam_android.productdetail.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_bookmark_check.*
import org.jetbrains.anko.toast
import org.yamyamgoods.yamyam_android.R

/**
 * Created By Yun Hyeok
 * on 7월 07, 2019
 */

class BookmarkCheckDialog(private val ctx: Context) : Dialog(ctx) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_bookmark_check)

        viewInit()
    }

    private fun viewInit() {
        btn_bookmark_check_dialog_nothing.setOnClickListener {
            dismiss()
        }
        btn_bookmark_check_dialog_go_bookmark.setOnClickListener {
            ctx.toast("호롤롤")
        }
    }
}
