package org.yamyamgoods.yamyam_android.util

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue


fun dp2px(dp: Float, ctx: Context) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, ctx.resources.displayMetrics).toInt()

fun px2dp(px: Int, ctx: Context) = px / ((ctx.resources.displayMetrics.densityDpi.toFloat()) /  DisplayMetrics.DENSITY_DEFAULT)