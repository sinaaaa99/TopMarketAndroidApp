package com.example.topmarket.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmarket.DataClass.DataclassFactor
import com.example.topmarket.R
import com.example.topmarket.adapter.Adapter_Factor
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_factor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FactorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factor)

        val factorUid = intent.getStringExtra("factorUid")

        recyclerViewFactor.layoutManager = LinearLayoutManager(this)

        ApiService().getRetrofit().getFactor(factorUid.toString())
            .enqueue(object : Callback<DataclassFactor> {
                override fun onResponse(
                    call: Call<DataclassFactor>,
                    response: Response<DataclassFactor>
                ) {
                    val data = response.body()
                    if (data != null) {
                        textView59.text = data.clientName
                        textView61.text = data.mobileNumber
                        textView63.text = data.address
                        textView65.text = data.factorNumber.toString()
                        textView75.text = data.creationDate.toString()
                        textView77.text = data.totalPriceWithDiscount.toString()
                        textView79.text = data.deliveryPrice.toString()

                        recyclerViewFactor.adapter = Adapter_Factor(data)

                    }

                }

                override fun onFailure(call: Call<DataclassFactor>, t: Throwable) {

                }

            })
    }
}