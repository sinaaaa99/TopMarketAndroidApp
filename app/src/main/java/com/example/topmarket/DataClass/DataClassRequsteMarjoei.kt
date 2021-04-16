package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassRequsteMarjoei(
    @SerializedName("orderUid")
    val orderUid: String,
    @SerializedName("returnProductList")
    val returnProductList: List<ReturnProduct>
) {
    data class ReturnProduct(
        @SerializedName("productUid")
        val productUid: String,
        @SerializedName("quantity")
        val quantity: Int,
        @SerializedName("returnReason")
        val returnReason: Int,
        @SerializedName("returnReasonDescription")
        val returnReasonDescription: String
    )
}