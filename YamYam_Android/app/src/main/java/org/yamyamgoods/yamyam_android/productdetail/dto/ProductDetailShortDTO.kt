package org.yamyamgoods.yamyam_android.productdetail.dto

import android.os.Parcel
import android.os.Parcelable

/**
 * Created By Yun Hyeok
 * on 7월 12, 2019
 */

data class ProductDetailShortDTO(
    val goods_idx: Int,
    val goods_img: String,
    val store_name: String,
    val goods_name: String,
    val goods_price: String,
    val goods_rating: Float?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(goods_idx)
        parcel.writeString(goods_img)
        parcel.writeString(store_name)
        parcel.writeString(goods_name)
        parcel.writeString(goods_price)
        parcel.writeValue(goods_rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductDetailShortDTO> {
        override fun createFromParcel(parcel: Parcel): ProductDetailShortDTO {
            return ProductDetailShortDTO(parcel)
        }

        override fun newArray(size: Int): Array<ProductDetailShortDTO?> {
            return arrayOfNulls(size)
        }
    }
}