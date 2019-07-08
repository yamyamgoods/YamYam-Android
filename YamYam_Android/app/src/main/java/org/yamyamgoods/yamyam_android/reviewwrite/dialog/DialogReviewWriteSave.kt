package org.yamyamgoods.yamyam_android.reviewwrite.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_review_write_save_reivew.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.reviewwrite.ReviewWriteActivity






class DialogReviewWriteSave(context: Context): Dialog(context){


        var rw_ctx: ReviewWriteActivity = ReviewWriteActivity.reviewWriteCtx as ReviewWriteActivity


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_review_write_save_reivew)

        btn_dialog_review_write_check.setOnClickListener{
            try {
                dismiss()
                // 서버와 통신하여 +50 point
                rw_ctx.finish()

            } catch (e: Exception) {
            }
        }
    }
}