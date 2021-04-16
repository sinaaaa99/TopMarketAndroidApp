package com.example.topmarket.net

import com.example.storeproma.DataClassLogin
import com.example.storeproma.DataClassValidation
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

class ApiServiceLogin {

    fun getRetrofitLogin(): getApiLogin =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://topmarketapp.ir/app/")
            .build()
            .create(getApiLogin::class.java)

    //test base Url http://test.topmarketapp.ir:8130/app/
    //base Url https://topmarketapp.ir/app/


    interface getApiLogin {

        @FormUrlEncoded
        @Headers(
            "Content-Type:application/x-www-form-urlencoded"
        )
        @POST("api/v1/clients/auth/signIn")
        fun userlogin(

            @Field("mobileNumber") mobileNumber: String,
            @Field("inviteeCode") inviteeCode: String
        ): Call<DataClassLogin>


        @FormUrlEncoded
        @Headers(
            "Content-Type:application/x-www-form-urlencoded"
        )
        @POST("api/v1/clients/auth/validateCode")
        fun userlogincode(

            @Field("clientUid") clientUid: String,
            @Field("code") code: String
        ): Call<DataClassValidation>

    }
}