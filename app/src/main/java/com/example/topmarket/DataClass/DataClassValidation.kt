package com.example.storeproma


import com.google.gson.annotations.SerializedName

data class DataClassValidation(
    @SerializedName("token")
    val token: String,
    @SerializedName("userUid")
    val userUid: String
)