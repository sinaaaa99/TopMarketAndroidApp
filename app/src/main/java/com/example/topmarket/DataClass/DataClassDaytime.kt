package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassDaytime(
    @SerializedName("data")
    val `data`: List<Data>
) {
    data class Data(
        @SerializedName("day")
        val day: String,
        @SerializedName("daysFromNow")
        val daysFromNow: Int,
        @SerializedName("eveningDelivery")
        val eveningDelivery: Boolean,
        @SerializedName("morningDelivery")
        val morningDelivery: Boolean,
        @SerializedName("time")
        val time: Long
    )
}