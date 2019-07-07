package org.yamyamgoods.yamyam_android.reviewdetail

import android.os.Parcel
import android.os.Parcelable

data class ReviewBasicDTO(
        var userIamgeUrl: String,
        var userNickname: String,
        var date: String,
        var starCount: Int,
        var reviewContents: String,
        var imageUrl: List<String>,
        var thumbFlag: Int,
        var thumbCount: Int,
        var commentsCount: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userIamgeUrl)
        parcel.writeString(userNickname)
        parcel.writeString(date)
        parcel.writeInt(starCount)
        parcel.writeString(reviewContents)
        parcel.writeStringList(imageUrl)
        parcel.writeInt(thumbFlag)
        parcel.writeInt(thumbCount)
        parcel.writeInt(commentsCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewBasicDTO> {
        override fun createFromParcel(parcel: Parcel): ReviewBasicDTO {
            return ReviewBasicDTO(parcel)
        }

        override fun newArray(size: Int): Array<ReviewBasicDTO?> {
            return arrayOfNulls(size)
        }
    }
}
//: Serializable