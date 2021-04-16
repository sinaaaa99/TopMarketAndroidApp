package com.example.topmarket.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmarket.DataClass.DataclassListShoped
import com.example.topmarket.R
import com.example.topmarket.adapter.Adapter_listShoped
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_marjoe.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopedActivity : AppCompatActivity(),Adapter_listShoped.listenerIds {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marjoe)

        recyclerView_listShoped.layoutManager = LinearLayoutManager(this)

        //service Call
        ApiService().getRetrofit().getlistshoped(0, 100)
            .enqueue(object : Callback<DataclassListShoped> {
                override fun onResponse(
                    call: Call<DataclassListShoped>,
                    response: Response<DataclassListShoped>
                ) {
                    val data = response.body()
                    if (data != null) {

                        recyclerView_listShoped.adapter =
                            Adapter_listShoped(data, this@ShopedActivity)
                        Log.d("sina",response.code().toString())
                    }else Log.d("sina",response.code().toString())

                }

                override fun onFailure(call: Call<DataclassListShoped>, t: Throwable) {
                    Log.d("sina",t.message.toString())
                }
            })


    }

    override fun orderUid(orderUid: String) {
        val intentorder = Intent(this, ShopedDerailActivity::class.java)
        intentorder.putExtra("orderUid",orderUid)
        startActivity(intentorder)
    }

    override fun factorUid(factorUid: String) {
        val intentfactor = Intent(this, FactorActivity::class.java)
        intentfactor.putExtra("factorUid",factorUid)
        startActivity(intentfactor)
    }
}