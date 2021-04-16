package com.example.topmarket.DataClass


import com.google.gson.annotations.SerializedName

data class DataClassAddProfile(
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String
)