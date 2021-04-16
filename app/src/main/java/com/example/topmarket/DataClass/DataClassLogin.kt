package com.example.storeproma


import com.google.gson.annotations.SerializedName

data class DataClassLogin(
    @SerializedName("clientUid")
    val clientUid: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("message")
    val message: String
)