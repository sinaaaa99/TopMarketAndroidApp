package com.example.topmarket.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.topmarket.DataClass.DataClassError
import com.example.topmarket.DataClass.DataClassSherkatdis
import com.example.topmarket.DataClass.DataclassPayEtear
import com.example.topmarket.DataClass.DataclasssherkatB
import com.example.topmarket.R
import com.example.topmarket.net.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_shekat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class shekatActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var dataclassdis: DataClassSherkatdis
    lateinit var dataclassdisb: DataclasssherkatB

    var pay = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shekat)

        sharedPreferences = getSharedPreferences("DiscountHolder", Context.MODE_PRIVATE)

        val cartUid = sharedPreferences.getString("cartUid", "")
        val discount = sharedPreferences.getString("discountcode", "")
        val credit = sharedPreferences.getInt("credit", 1)

        textview_mizanESh.text = credit.toString()

        radioGroup3.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_paySh_E -> pay = 0
                R.id.radio_paySh_M -> pay = 1
            }

            btnSh_nahaii.setOnClickListener {

                if (pay == 1) {
                    if (discount != null) {
                        if (discount.isNotEmpty()) {
                            dataclassdis = DataClassSherkatdis(cartUid!!, discount, 0)
                            ApiService().getRetrofit().payinplaceSh(dataclassdis)
                                .enqueue(object : Callback<DataclassPayEtear> {
                                    override fun onResponse(
                                        call: Call<DataclassPayEtear>,
                                        response: Response<DataclassPayEtear>
                                    ) {
                                        Log.d("sina", response.code().toString())
                                        val dataE = response.body()
                                        if (dataE != null) {
                                            startActivity(
                                                Intent(
                                                    this@shekatActivity,
                                                    MainActivity::class.java
                                                )
                                            )
                                            Toast.makeText(
                                                this@shekatActivity,
                                                "سفارش با موفقیت انجام شد",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                        } else {

                                            val gson = Gson()
                                            val type = object : TypeToken<DataClassError>() {}.type
                                            val errorResponse: DataClassError? =
                                                gson.fromJson(response.errorBody()!!.charStream(), type)

                                            if (errorResponse != null) {
                                                Toast.makeText(
                                                    this@shekatActivity, errorResponse.message,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<DataclassPayEtear>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(
                                            this@shekatActivity,
                                            "لطفا اتصال خود به اینترنت را چک کنید",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                        }
                        if (discount.isEmpty()) {
                            dataclassdisb = DataclasssherkatB(cartUid!!, 0)
                            ApiService().getRetrofit().payinplaceShb(dataclassdisb)
                                .enqueue(object : Callback<DataclassPayEtear> {
                                    override fun onResponse(
                                        call: Call<DataclassPayEtear>,
                                        response: Response<DataclassPayEtear>
                                    ) {
                                        val data = response.body()
                                        if (data != null) {
                                            startActivity(
                                                Intent(
                                                    this@shekatActivity,
                                                    MainActivity::class.java
                                                )
                                            )
                                            Toast.makeText(
                                                this@shekatActivity,
                                                "سفارش با موفقیت انجام شد",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                        } else {

                                            val gson = Gson()
                                            val type = object : TypeToken<DataClassError>() {}.type
                                            val errorResponse: DataClassError? =
                                                gson.fromJson(response.errorBody()!!.charStream(), type)

                                            if (errorResponse != null) {
                                                Toast.makeText(
                                                    this@shekatActivity, errorResponse.message,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        }

                                    }

                                    override fun onFailure(
                                        call: Call<DataclassPayEtear>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(
                                            this@shekatActivity,
                                            "لطفا اتصال خود به اینترنت را چک کنید",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })

                        }
                    }

                } else {
                    if (discount != null) {
                        if (discount.isNotEmpty()) {
                            dataclassdis = DataClassSherkatdis(cartUid!!, discount, 0)
                            ApiService().getRetrofit().payEtebarSh(dataclassdis)
                                .enqueue(object : Callback<DataclassPayEtear> {
                                    override fun onResponse(
                                        call: Call<DataclassPayEtear>,
                                        response: Response<DataclassPayEtear>
                                    ) {
                                        Log.d("sina", response.code().toString())
                                        val data = response.body()
                                        if (data != null) {
                                            startActivity(
                                                Intent(
                                                    this@shekatActivity,
                                                    MainActivity::class.java
                                                )
                                            )
                                            Toast.makeText(
                                                this@shekatActivity,
                                                "سفارش با موفقیت انجام شد",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {

                                            val gson = Gson()
                                            val type = object : TypeToken<DataClassError>() {}.type
                                            val errorResponse: DataClassError? =
                                                gson.fromJson(response.errorBody()!!.charStream(), type)

                                            if (errorResponse != null) {
                                                Toast.makeText(
                                                    this@shekatActivity, errorResponse.message,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<DataclassPayEtear>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(
                                            this@shekatActivity,
                                            "لطفا اتصال خود به اینترنت را چک کنید",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })
                        }
                        if (discount.isEmpty()) {
                            dataclassdisb = DataclasssherkatB(cartUid!!, 0)
                            ApiService().getRetrofit().payEtebarShb(dataclassdisb)
                                .enqueue(object : Callback<DataclassPayEtear> {
                                    override fun onResponse(
                                        call: Call<DataclassPayEtear>,
                                        response: Response<DataclassPayEtear>
                                    ) {
                                        Log.d("sin", response.code().toString())
                                        val data = response.body()
                                        if (data != null) {
                                            startActivity(
                                                Intent(
                                                    this@shekatActivity,
                                                    MainActivity::class.java
                                                )
                                            )
                                            Toast.makeText(
                                                this@shekatActivity,
                                                "سفارش با موفقیت انجام شد",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {

                                            val gson = Gson()
                                            val type = object : TypeToken<DataClassError>() {}.type
                                            val errorResponse: DataClassError? =
                                                gson.fromJson(response.errorBody()!!.charStream(), type)

                                            if (errorResponse != null) {
                                                Toast.makeText(
                                                    this@shekatActivity, errorResponse.message,
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<DataclassPayEtear>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(
                                            this@shekatActivity,
                                            "لطفا اتصال خود به اینترنت را چک کنید",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                })


                        }
                    }
                }
            }

        }


    }
}