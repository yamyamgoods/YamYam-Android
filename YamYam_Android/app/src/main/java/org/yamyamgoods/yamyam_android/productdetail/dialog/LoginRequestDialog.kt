package org.yamyamgoods.yamyam_android.productdetail.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_login_request.*
import org.yamyamgoods.yamyam_android.LoginActivity
import org.yamyamgoods.yamyam_android.R

/**
 * Created By Yun Hyeok
 * on 7월 07, 2019
 */

class LoginRequestDialog(private val ctx: Context) : Dialog(ctx) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_login_request)

        window!!.setBackgroundDrawableResource(R.color.transparent)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        viewInit()
    }

    private fun viewInit() {
        btn_login_request_dialog_go_login.setOnClickListener {
            val intent = Intent(ctx.applicationContext, LoginActivity::class.java)
                .apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) }
            ctx.startActivity(intent)
            dismiss()
        }

        btn_login_request_dialog_close.setOnClickListener {
            dismiss()
        }
    }
}