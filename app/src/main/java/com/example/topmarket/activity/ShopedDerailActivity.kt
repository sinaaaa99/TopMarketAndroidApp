package com.example.topmarket.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.topmarket.DataClass.DataClassDetailShoped
import com.example.topmarket.R
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_shoped_derail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopedDerailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoped_derail)

        val orderUid = intent.getStringExtra("orderUid")

        ApiService().getRetrofit().getdetailshoped(orderUid.toString())
            .enqueue(object : Callback<DataClassDetailShoped> {
                override fun onResponse(
                    call: Call<DataClassDetailShoped>,
                    response: Response<DataClassDetailShoped>
                ) {
                    val data = response.body()
                    if (data != null) {

                        textView38.text = data.receiver.fullName
                        textView41.text = data.receiver.mobileNumber
                        textView43.text = data.receiver.address
                        if (data.delivery.withDelivery) {
                            textView45.text = "تحویل در محل"
                            textView47.text = data.delivery.deliveryPrice.toString()
                            textView49.text = data.delivery.deliveryDate.toString()
                            textView51.text = data.delivery.price.toString()
                            textView52.text = data.delivery.discount.toString()
                            textView54.text = data.delivery.payablePrice.toString()
                        } else if (!data.delivery.withDelivery) {
                            textView45.text = "تحویل در شرکت"
                            textView47.text = "-"
                            textView49.text = "-"
                            textView51.text = "-"
                            textView52.text = "-"
                            textView54.text = "-"
                        }
                    }

                }

                override fun onFailure(call: Call<DataClassDetailShoped>, t: Throwable) {

                }

            })
    }
}