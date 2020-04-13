package com.example.kleverfruits.model.order.orderDetail

import android.os.Parcel
import android.os.Parcelable

data class DataOrderDetail (
    val url : String,
    val name : String,
    val cost : String,
    val number : Int,
    val status : Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(name)
        parcel.writeString(cost)
        parcel.writeInt(number)
        parcel.writeInt(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataOrderDetail> {
        override fun createFromParcel(parcel: Parcel): DataOrderDetail {
            return DataOrderDetail(parcel)
        }

        override fun newArray(size: Int): Array<DataOrderDetail?> {
            return arrayOfNulls(size)
        }
    }
}