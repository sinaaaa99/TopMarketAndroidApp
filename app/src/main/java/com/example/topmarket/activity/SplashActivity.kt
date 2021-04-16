package com.example.topmarket.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.topmarket.DataClass.DataClassDaytime
import com.example.topmarket.R
import com.example.topmarket.adapter.AdapterTimeSet
import com.example.topmarket.adapter.AdapterTimeSetter
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_pay_pal.*
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity(), AdapterTimeSetter.listener2 {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        ApiService().getRetrofit().getdaterecive()
            .enqueue(object : Callback<DataClassDaytime> {
                override fun onResponse(
                    call: Call<DataClassDaytime>,
                    response: Response<DataClassDaytime>
                ) {
                    val data = response.body()
                    if (data != null) {

                        spinersina.adapter = AdapterTimeSetter(data, this@SplashActivity)
                    }

                }

                override fun onFailure(call: Call<DataClassDaytime>, t: Throwable) {

                }
            })
    }

    override fun setonclick(x: Int) {

        spinnerlistdate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val dayfromNow = x

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        Toast.makeText(this, "تعداد روز تا تحویل:$x", Toast.LENGTH_SHORT).show()
    }
}