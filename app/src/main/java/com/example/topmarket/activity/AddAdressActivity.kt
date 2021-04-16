package com.example.topmarket.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.topmarket.DataClass.DataClassAddAdress
import com.example.topmarket.R
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_add_adress.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAdressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_adress)

        btn_AddAdress.setOnClickListener {

            if (editTextText_addAdresss.length() == 0) {
                editTextText_addAdresss.error = "لطفا یک آدرس وارد کنید"
                editTextText_addAdresss.requestFocus()
                return@setOnClickListener
            }

            ApiService().getRetrofit().AddAdress(
                editTextText_addAdresss.text.toString(),
                editTextText_codepost.text.toString(),
                editTextText_titleAdress.text.toString()
            ).enqueue(object : Callback<DataClassAddAdress> {
                override fun onResponse(
                    call: Call<DataClassAddAdress>,
                    response: Response<DataClassAddAdress>
                ) {
                    val data = response.body()
                    if (data != null) {
                        Toast.makeText(this@AddAdressActivity, "آدرس اضافه شد", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(
                            Intent(
                                this@AddAdressActivity,
                                AdresschoiceActivity::class.java
                            )
                        )
                    }

                }

                override fun onFailure(call: Call<DataClassAddAdress>, t: Throwable) {
                    Toast.makeText(
                        this@AddAdressActivity,
                        "لطفا اتصال خود به اینترنت را چک کنید",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}