package org.yamyamgoods.yamyam_android.mypage.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import kotlinx.android.synthetic.main.dialog_mypage_edit_user_image.*
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.mypage.MypageActivity
import java.lang.Exception

class DialogMypageChangeProfileImage(context: Context): Dialog(context) {

    private fun scanForActivity(ctx: Context?): Activity? {
        if ( ctx == null)
            return null
        if (ctx is Activity)
            return ctx
        if (ctx is ContextThemeWrapper)
            return scanForActivity(ctx.baseContext)
        return null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_mypage_edit_user_image)

        btn_dialog_mypage_close.setOnClickListener{
            try {
                dismiss()
            } catch (e: Exception) {
            }
        }
        btn_dialog_mypage_album.setOnClickListener{
            var mypageActivityCtx = scanForActivity(context)
            (mypageActivityCtx as MypageActivity).getPermission()
            dismiss()
        }
        btn_dialog_mypage_default.setOnClickListener{
            var mypageActivityCtx = scanForActivity(context)
            (mypageActivityCtx as MypageActivity).setProfileImageDefault()
            dismiss()
        }
    }
}