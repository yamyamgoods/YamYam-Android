package org.yamyamgoods.yamyam_android.dataclass

import java.io.Serializable

/**
 * Created By Yun Hyeok
 * on 7월 10, 2019
 */

data class SelectedOption(
    var optionName: String,
    var optionValue: String
) : Serializable