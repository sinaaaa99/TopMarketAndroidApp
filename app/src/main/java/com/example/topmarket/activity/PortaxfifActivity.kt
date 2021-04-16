package com.example.topmarket.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmarket.DataClass.DataClassResponseShop
import com.example.topmarket.DataClass.DataclassShoping
import com.example.topmarket.DataClass.DataclassTaxfif
import com.example.topmarket.R
import com.example.topmarket.adapter.AdapterAlltaxfif
import com.example.topmarket.adapter.Adapter_taxfif
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_portaxfif.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PortaxfifActivity : AppCompatActivity(), AdapterAlltaxfif.senddata {

    lateinit var dataclassShoping: DataclassShoping

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portaxfif)

        val classdialog = AlertDialogLoad(this)
        classdialog.start()

        recyclerviewPorAll.layoutManager = LinearLayoutManager(this)



        ApiService().getRetrofit().getTaxfifha(200, 0)
            .enqueue(object : Callback<DataclassTaxfif> {
                override fun onResponse(
                    call: Call<DataclassTaxfif>,
                    response: Response<DataclassTaxfif>
                ) {
                    val data = response.body()
                    if (data != null) {

                        recyclerviewPorAll.adapter =
                            AdapterAlltaxfif(data, this@PortaxfifActivity, this@PortaxfifActivity)

                        classdialog.stop()
                    }else {
                        classdialog.stop()
                        Toast.makeText(
                            this@PortaxfifActivity,
                            "لطفا از اتصال خود به اینترنت مطمئن شوید",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }

                override fun onFailure(call: Call<DataclassTaxfif>, t: Throwable) {
                    classdialog.stop()

                    Toast.makeText(
                        this@PortaxfifActivity,
                        "لطفا اینترنت دستگاه خود را روشن کنید",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

    }

    override fun getsize(size: Int) {

    }

    override fun onclick(listdat: MutableList<DataclassShoping.Product>) {
        dataclassShoping = DataclassShoping(listdat)

        ApiService().getRetrofit().addShoping(dataclassShoping)
            .enqueue(object : Callback<DataClassResponseShop> {
                override fun onResponse(
                    call: Call<DataClassResponseShop>,
                    response: Response<DataClassResponseShop>
                ) {
                    if (response.body() != null) {
                        AlertDialog.Builder(this@PortaxfifActivity)
                            .setTitle("خرید")
                            .setMessage("محصول با موفقیت به سبد خرید اضافه شد")
                            .setPositiveButton("مشاهده سبد خرید") { dialog, witch ->

                                startActivity(
                                    Intent(
                                        this@PortaxfifActivity,
                                        BasketActivity::class.java
                                    )
                                )
                            }
                            .setNegativeButton("ادامه ی خرید") { _, _ ->
                            }
                            .show()
                    } else Toast.makeText(
                        this@PortaxfifActivity,
                        "این تعداد محصول موجود نیست",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<DataClassResponseShop>, t: Throwable) {
                }
            })
    }
}