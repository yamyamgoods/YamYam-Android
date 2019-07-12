package org.yamyamgoods.yamyam_android.home.bookmark.dto

import org.yamyamgoods.yamyam_android.dataclass.SelectedOption
import java.io.Serializable

/**
 * Created By Yun Hyeok
 * on 7월 13, 2019
 */

data class Bookmark2ProductDetailDTO(
    val label: String,
    val selectedOptions: List<SelectedOption>
) : Serializable