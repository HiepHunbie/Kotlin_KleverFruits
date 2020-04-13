package com.example.kleverfruits.model.order

import android.os.Parcel
import android.os.Parcelable
import com.example.kleverfruits.model.order.orderDetail.DataOrderDetail

data class DataOrder (
    val order_code : String,
    val date : String,
    val total_product : String,
    val total_cost : Int,
    val status : Int,
    val status_ship : Int,
    val dataOrderDetail : List<DataOrderDetail>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(DataOrderDetail)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(order_code)
        parcel.writeString(date)
        parcel.writeString(total_product)
        parcel.writeInt(total_cost)
        parcel.writeInt(status)
        parcel.writeInt(status_ship)
        parcel.writeTypedList(dataOrderDetail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataOrder> {
        override fun createFromParcel(parcel: Parcel): DataOrder {
            return DataOrder(parcel)
        }

        override fun newArray(size: Int): Array<DataOrder?> {
            return arrayOfNulls(size)
        }
    }
}