package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataclassAdressList(
    @SerializedName("clientFullName")
    val clientFullName: String,
    @SerializedName("clientMobileNumber")
    val clientMobileNumber: String,
    @SerializedName("data")
    val `data`: List<Data>
) {
    data class Data(
        @SerializedName("address")
        val address: String,
        @SerializedName("addressUid")
        val addressUid: String,
        @SerializedName("postalCode")
        val postalCode: String,
        @SerializedName("title")
        val title: String
    )
}