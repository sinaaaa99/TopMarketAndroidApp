package com.example.topmarket.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.topmarket.DataClass.*
import com.example.topmarket.R
import com.example.topmarket.adapter.AdapterTimeSet
import com.example.topmarket.net.ApiService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_pay_pal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayPalActivity : AppCompatActivity(), AdapterTimeSet.listener1 {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var dataclassReqPay: DataClassRequestBuy
    lateinit var adapterr: AdapterTimeSet

    //without discount
    lateinit var dataclassReqPayb: DataClassReqBuyB
    var pay = 0
    var time = 0
    var dayfromNow: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_pal)

        sharedPreferences = getSharedPreferences("DiscountHolder", Context.MODE_PRIVATE)

        val cartUid = sharedPreferences.getString("cartUid", "")
        val discount = sharedPreferences.getString("discountcode", "")
        val adressUid = intent.getStringExtra("adressUid")
        val credit = sharedPreferences.getInt("credit", 1)

        textviewEtebarPay.text = credit.toString()

        radioGroup_pay.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {

                R.id.radio_payAdd -> pay = 0
                R.id.radio_placAdd -> pay = 1
            }
        }
        radioGroup_time.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radiomorning -> time = 0
                R.id.radioevening -> time = 1
            }
        }

        button_sabtAdddd.setOnClickListener {
            /*Log.d("pay", "1:" + cartUid)
            Log.d("pay", "2:" + discount)
            Log.d("pay", "3:" + pay)
            Log.d("pay", "4:" + time)
            Log.d("pay", "5:" + adressUid)
            Log.d("pay", "6:" + dayfromNow)*/
            //Etebari
            if (pay == 0) {
                if (discount != null) {
                    if (discount.isNotEmpty()) {
                        dataclassReqPay =
                            DataClassRequestBuy(
                                adressUid!!,
                                cartUid!!,
                                dayfromNow,
                                time,
                                discount,
                                1
                            )

                        ApiService().getRetrofit()
                            .payEtebarAdd(dataclassReqPay)
                            .enqueue(object : Callback<DataclassPayEtear> {
                                override fun onResponse(
                                    call: Call<DataclassPayEtear>,
                                    response: Response<DataclassPayEtear>
                                ) {
                                    val data = response.body()
                                    if (data != null) {
                                        Toast.makeText(
                                            this@PayPalActivity,
                                            "سفارش با موفقیت انجام شد",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        startActivity(
                                            Intent(
                                                this@PayPalActivity,
                                                MainActivity::class.java
                                            )
                                        )
                                    } else {

                                        val gson = Gson()
                                        val type = object : TypeToken<DataClassError>() {}.type
                                        val errorResponse: DataClassError? =
                                            gson.fromJson(response.errorBody()!!.charStream(), type)

                                        if (errorResponse != null) {
                                            Toast.makeText(
                                                this@PayPalActivity, errorResponse.message,
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
                                        this@PayPalActivity,
                                        "لطفا اتصال خود به اینترنت را چک کنید",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                    }
                }
                if (discount != null) {
                    if (discount.isEmpty()) {
                        dataclassReqPayb =
                            DataClassReqBuyB(adressUid!!, cartUid!!, dayfromNow, time, 1)
                        ApiService().getRetrofit().payEtebarAddb(dataclassReqPayb)
                            .enqueue(object : Callback<DataclassPayEtear> {
                                override fun onResponse(
                                    call: Call<DataclassPayEtear>,
                                    response: Response<DataclassPayEtear>
                                ) {
                                    Log.d("ffff", "1" + response.code().toString())
                                    val data = response.body()
                                    if (data != null) {
                                        Log.d("ffff", "2" + response.code().toString())
                                        Toast.makeText(
                                            this@PayPalActivity,
                                            "سفارش با موفقیت انجام شد",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        startActivity(
                                            Intent(
                                                this@PayPalActivity,
                                                MainActivity::class.java
                                            )
                                        )
                                    } else {

                                        val gson = Gson()
                                        val type = object : TypeToken<DataClassError>() {}.type
                                        val errorResponse: DataClassError? =
                                            gson.fromJson(response.errorBody()!!.charStream(), type)

                                        if (errorResponse != null) {
                                            Toast.makeText(
                                                this@PayPalActivity, errorResponse.message,
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
                                        this@PayPalActivity,
                                        "لطفا اتصال خود به اینترنت را چک کنید",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                    }
                }

            }
            //place
            if (pay == 1) {
                if (discount != null) {
                    if (discount.isNotEmpty()) {
                        dataclassReqPay =
                            DataClassRequestBuy(
                                adressUid!!,
                                cartUid!!,
                                dayfromNow,
                                time,
                                discount,
                                1
                            )

                        ApiService().getRetrofit()
                            .payPlaceAdd(dataclassReqPay)
                            .enqueue(object : Callback<DataclassPayEtear> {
                                override fun onResponse(
                                    call: Call<DataclassPayEtear>,
                                    response: Response<DataclassPayEtear>
                                ) {
//                                    Log.d("ffff", "badbakht" + response.code().toString())
                                    val data = response.body()
                                    if (data != null) {
                                        Toast.makeText(
                                            this@PayPalActivity,
                                            "سفارش با موفقیت انجام شد",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        startActivity(
                                            Intent(
                                                this@PayPalActivity,
                                                MainActivity::class.java
                                            )
                                        )
                                    } else {

                                        val gson = Gson()
                                        val type = object : TypeToken<DataClassError>() {}.type
                                        val errorResponse: DataClassError? =
                                            gson.fromJson(response.errorBody()!!.charStream(), type)

                                        if (errorResponse != null) {
                                            Toast.makeText(
                                                this@PayPalActivity, errorResponse.message,
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
                                        this@PayPalActivity,
                                        "لطفا اتصال خود به اینترنت را چک کنید",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })

                    }
                }
                if (discount != null) {
                    if (discount.isEmpty()) {
                        dataclassReqPayb =
                            DataClassReqBuyB(adressUid!!, cartUid!!, dayfromNow, time, 1)
                        ApiService().getRetrofit()
                            .payPlaceAddB(dataclassReqPayb)
                            .enqueue(object : Callback<DataclassPayEtear> {
                                override fun onResponse(
                                    call: Call<DataclassPayEtear>,
                                    response: Response<DataclassPayEtear>
                                ) {
                                    val data = response.body()
                                    if (data != null) {

                                        Log.d("kamshansi", dayfromNow.toString())

                                        Toast.makeText(
                                            this@PayPalActivity,
                                            "سفارش با موفقیت انجام شد",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        startActivity(
                                            Intent(
                                                this@PayPalActivity,
                                                MainActivity::class.java
                                            )
                                        )
                                    } else {

                                        val gson = Gson()
                                        val type = object : TypeToken<DataClassError>() {}.type
                                        val errorResponse: DataClassError? =
                                            gson.fromJson(response.errorBody()!!.charStream(), type)

                                        if (errorResponse != null) {
                                            Toast.makeText(
                                                this@PayPalActivity, errorResponse.message,
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
                                        this@PayPalActivity,
                                        "لطفا اتصال خود به اینترنت را چک کنید",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })

                    }
                }


            }
        }





        ApiService().getRetrofit().getdaterecive()
            .enqueue(object : Callback<DataClassDaytime> {
                override fun onResponse(
                    call: Call<DataClassDaytime>,
                    response: Response<DataClassDaytime>
                ) {
                    val data = response.body()
                    if (data != null) {
                        adapterr = AdapterTimeSet(data, this@PayPalActivity)
                        spinnerlistdate.adapter = adapterr

                        spinnerlistdate.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    dayfromNow = data.data[position].daysFromNow
//                                    Toast.makeText(this@PayPalActivity, dayfromNow.toString(), Toast.LENGTH_SHORT).show()

                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }
                            }
                    }

                }

                override fun onFailure(call: Call<DataClassDaytime>, t: Throwable) {

                }
            })
    }

    override fun setonclick(x: Int) {
        dayfromNow = x
        Toast.makeText(this, "تعداد روز تا تحویل:$x", Toast.LENGTH_SHORT).show()
    }
}