package org.yamyamgoods.yamyam_android.dataclass

import android.os.Parcel
import android.os.Parcelable

data class ReviewData(
    var goods_review_idx: Int,

    var user_img: String,
    var user_name: String,
    var goods_review_date: String,
    var goods_review_rating: Int,

    var goods_review_content: String,

    var goods_review_img: List<String>,

    var review_like_flag: Int,
    var goods_review_like_count: Int,
    var goods_review_cmt_count: Int,

    val goods_review_photo_flag: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(goods_review_idx)
        parcel.writeString(user_img)
        parcel.writeString(user_name)
        parcel.writeString(goods_review_date)
        parcel.writeInt(goods_review_rating)
        parcel.writeString(goods_review_content)
        parcel.writeStringList(goods_review_img)
        parcel.writeInt(review_like_flag)
        parcel.writeInt(goods_review_like_count)
        parcel.writeInt(goods_review_cmt_count)
        parcel.writeInt(goods_review_photo_flag)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewData> {
        override fun createFromParcel(parcel: Parcel): ReviewData {
            return ReviewData(parcel)
        }

        override fun newArray(size: Int): Array<ReviewData?> {
            return arrayOfNulls(size)
        }
    }
}