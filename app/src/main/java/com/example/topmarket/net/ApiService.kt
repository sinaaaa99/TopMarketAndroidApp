package com.example.topmarket.net

import android.content.Context
import android.content.SharedPreferences
import com.example.topmarket.DataClass.*
import com.example.topmarket.androidWrapper.App
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


class ApiService() {

    val context: Context = App.applicationContext()
    private var tokensh: SharedPreferences =
        context.getSharedPreferences("tokenSh", Context.MODE_PRIVATE)

    val token = tokensh.getString("token", "")


    var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(newRequest)
    }.build()

    fun getRetrofit(): getApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://topmarketapp.ir/app/")
            .client(client)
            .build()
            .create(getApi::class.java)


    //test base Url http://test.topmarketapp.ir:8130/app/
    //base Url https://topmarketapp.ir/app/

    interface getApi {

        @GET("api/v1/clients/homePage")
        fun getAllCategory(): Call<DataClassCategory>


        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/category/subCategory/list")
        fun getSubCategory(

            @Field("categoryUid") categoryUid: String
        ): Call<DataClassSubCategory>

        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/products/productListForCategory")
        fun getProducts(

            @Field("pageNumber") pageNumber: Int,
            @Field("size") size: Int,
            @Field("categoryUid") categoryUid: String

        ): Call<DataClassproducts>


        @Headers("Content-Type:application/json")
        @POST("api/v1/cart/add")
        fun addShoping(
            @Body DataclassShoping: DataclassShoping
        ): Call<DataClassResponseShop>


        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/cart/myCart")
        fun getBasket(): Call<DataclassBasket>


        @Headers("Content-Type:application/json")
        @POST("api/v1/clients/profile/create")
        fun addprofile(
            @Body DataClassAddProfile: DataClassAddProfile
        ): Call<DataclassResponseAddProfile>

        @GET("api/v1/clients/profile/getProfile")
        fun getProfile(): Call<DataClassGetProfile>


        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/discount/check")
        fun cheekDiscount(

            @Field("discountCode") discountCode: String,
            @Field("value") value: Long

        ): Call<DataClassCheekDiscount>


        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/clients/address/create")
        fun AddAdress(

            @Field("address") address: String,
            @Field("postalCode") postalCode: String,
            @Field("title") title: String,

            ): Call<DataClassAddAdress>


        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/clients/address/list")
        fun showAddress(): Call<DataclassAdressList>

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/orders/orderDates")
        fun getdaterecive(): Call<DataClassDaytime>


        //pay darb sherkat etebar dis
        @Headers("Content-Type:application/json")
        @POST("api/v1/orders/create/withCredit")
        fun payEtebarSh(
            @Body DataClassSherkatdis: DataClassSherkatdis
        ): Call<DataclassPayEtear>

        //pay darb sherkat dis
        @Headers("Content-Type:application/json")
        @POST("api/v1/orders/create/payInPlace")
        fun payinplaceSh(
            @Body DataClassSherkatdis: DataClassSherkatdis
        ): Call<DataclassPayEtear>

        //pay darb sherkat etebar without dis
        @Headers("Content-Type:application/json")
        @POST("api/v1/orders/create/withCredit")
        fun payEtebarShb(
            @Body DataclasssherkatB: DataclasssherkatB
        ): Call<DataclassPayEtear>

        //pay darb sherkat without dis
        @Headers("Content-Type:application/json")
        @POST("api/v1/orders/create/payInPlace")
        fun payinplaceShb(
            @Body DataclasssherkatB: DataclasssherkatB
        ): Call<DataclassPayEtear>


        //req Buy pay Etebar dis
        @Headers("Content-Type:application/json")
        @POST("api/v1/orders/create/withCredit")
        fun payEtebarAdd(
            @Body DataClassRequestBuy: DataClassRequestBuy
        ): Call<DataclassPayEtear>

        //req Buy pay Etebar without dis
        @Headers("Content-Type:application/json")
        @POST("api/v1/orders/create/withCredit")
        fun payEtebarAddb(
            @Body DataClassReqBuyB
            : DataClassReqBuyB
        ): Call<DataclassPayEtear>

        //req Buy pay in place dis
        @Headers("Content-Type:application/json")
        @POST("api/v1/orders/create/payInPlace")
        fun payPlaceAdd(
            @Body DataClassRequestBuy: DataClassRequestBuy
        ): Call<DataclassPayEtear>

        //req Buy pay in place without dis
        @Headers("Content-Type:application/json")
        @POST("api/v1/orders/create/payInPlace")
        fun payPlaceAddB(
            @Body DataClassReqBuyB: DataClassReqBuyB
        ): Call<DataclassPayEtear>

        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/products/productListWithHugeDiscount")
        fun getTaxfifha(

            @Field("size") size: Int,
            @Field("pageNumber") pageNumber: Int,

            ): Call<DataclassTaxfif>

        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/cart/product/changeQuantity")
        fun changequantiti(

            @Field("productUid") productUid: String,
            @Field("quantity") quantity: Int,

            ): Call<DataClassChangequantiti>


        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/products/searchProduct")
        fun searchProducts(

            @Field("pageNumber") pageNumber: Int,
            @Field("productName") productName: String,
            @Field("size") size: Int,

            ): Call<DataClassSearch>


        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/orders/history")
        fun getlistshoped(

            @Field("pageNumber") pageNumber: Int,
            @Field("size") size: Int

        ): Call<DataclassListShoped>

        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/orders/orderInfo")
        fun getdetailshoped(
            @Field("orderUid") orderUid: String
        ): Call<DataClassDetailShoped>

        @FormUrlEncoded
        @Headers("Content-Type:application/x-www-form-urlencoded")
        @POST("api/v1/factors/factorDetail")
        fun getFactor(
            @Field("factorUid") factorUid: String
        ): Call<DataclassFactor>

        @Headers("Content-Type:application/x-www-form-urlencoded")
        @GET("api/v1/orders/return/conditions")
        fun getmarjoei(): Call<DataClassErjae>

        @Headers("Content-Type:application/json")
        @POST("api/v1/orders/return/request")
        fun sendmarjo(
            @Body DataClassRequsteMarjoei
            : DataClassRequsteMarjoei
        ): Call<DataclassResponseMarjoei>

    }
}