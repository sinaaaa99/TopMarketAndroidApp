package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassGetProfile(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("invitationCode")
    val invitationCode: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("mobileNumber")
    val mobileNumber: String,
    @SerializedName("walletValue")
    val walletValue: Int
)