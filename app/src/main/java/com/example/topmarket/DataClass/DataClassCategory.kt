package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassCategory(
    @SerializedName("cartSize")
    val cartSize: Int,
    @SerializedName("data")
    val `data`: List<Data>
) {
    data class Data(
        @SerializedName("active")
        val active: Boolean,
        @SerializedName("categoryName")
        val categoryName: String,
        @SerializedName("categoryUid")
        val categoryUid: String,
        @SerializedName("creationDate")
        val creationDate: Long,
        @SerializedName("iconListUrl")
        val iconListUrl: String,
        @SerializedName("iconUrl")
        val iconUrl: String,
        @SerializedName("withSubCategory")
        val withSubCategory: Boolean
    )
}