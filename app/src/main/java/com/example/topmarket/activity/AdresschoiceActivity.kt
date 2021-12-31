package com.example.topmarket.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmarket.DataClass.DataclassAdressList
import com.example.topmarket.R
import com.example.topmarket.adapter.Adapter_adress
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_adresschoice.*
import org.jetbrains.anko.view
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdresschoiceActivity : AppCompatActivity() {
    var withdelivary = true
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adresschoice)

        AlertDialog.Builder(this)
            .setTitle("توجه")
            .setMessage("لطفا یک آدرس را برای ارسال مرسوله انتخاب کنید")
            .setPositiveButton("باشه") { _, _ ->

            }
            .show()

        sharedPreferences = getSharedPreferences("DiscountHolder", Context.MODE_PRIVATE)

        withdelivary = sharedPreferences.getBoolean("anbar", true)
        if (!withdelivary) {
//            constarct_anbar.visibility = View.GONE
        }

        floatingActionButton2.setOnClickListener {
            startActivity(Intent(this, AddAdressActivity::class.java))
            finish()
        }
        /*button_nextPay.setOnClickListener {
            startActivity(Intent(this, shekatActivity::class.java))
        }*/

        recyclerviewlistdata.layoutManager = LinearLayoutManager(this)

        ApiService().getRetrofit().showAddress()
            .enqueue(object : Callback<DataclassAdressList> {
                override fun onResponse(
                    call: Call<DataclassAdressList>,
                    response: Response<DataclassAdressList>
                ) {
                    val data = response.body()
                    if (data != null) {
                        recyclerviewlistdata.adapter =
                            Adapter_adress(data, this@AdresschoiceActivity)
                    }
                }

                override fun onFailure(call: Call<DataclassAdressList>, t: Throwable) {

                }
            })


    }
}