package com.example.topmarket.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.*
import com.example.topmarket.R
import com.example.topmarket.adapter.recycler_Basket
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_products22.*
import org.w3c.dom.CharacterData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class BasketActivity : AppCompatActivity(), recycler_Basket.changequantiti {

    lateinit var sharedPreferences: SharedPreferences
    var isnullS = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products22)

        val classdialog = AlertDialogLoad(this)
        classdialog.start()

        sharedPreferences = getSharedPreferences("DiscountHolder", Context.MODE_PRIVATE)

        recyclerViewBasket.layoutManager = LinearLayoutManager(this)

        ApiService().getRetrofit().getBasket().enqueue(object : Callback<DataclassBasket> {
            override fun onResponse(
                call: Call<DataclassBasket>,
                response: Response<DataclassBasket>
            ) {
                val data = response.body()
                if (data != null) {
                    val adapter = recycler_Basket(data, this@BasketActivity)

                    if (adapter.itemCount != 0) {
                        isnullS = false

                        Log.d("sina", isnullS.toString())
                    }
                    recyclerViewBasket.adapter = adapter
                    tex_withoutDis.text = data.totalPriceWithoutDiscount.toString()
                    txt_withDis.text = data.totalPriceWithDiscount.toString()
                    txt_ersal.text = data.deliveryPrice.toString()
                    txt_sood.text = data.profit.toString()
                    txt_payable.text = data.payablePrice.toString()

                    sharedPreferences.edit()
                        .putString("cartUid", data.cartUid)
                        .putBoolean("anbar", data.withoutDelivery)
                        .putInt("credit", data.credit)
                        .apply()

                    classdialog.stop()
                }

            }

            override fun onFailure(call: Call<DataclassBasket>, t: Throwable) {
                classdialog.stop()

                Toast.makeText(
                    this@BasketActivity,
                    "لطفا از اتصال خود به اینترنت مطمئن شوید",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        Img_Dis.setOnClickListener {
            if (Edit_Dis.length() == 0) {
                Edit_Dis.error = "لطفا کدتخفیف خود را وارد کنید"
                Edit_Dis.requestFocus()
                return@setOnClickListener
            }
            classdialog.start()

            ApiService().getRetrofit()
                .cheekDiscount(Edit_Dis.text.toString(), txt_withDis.text.toString().toLong())
                .enqueue(object : Callback<DataClassCheekDiscount> {
                    override fun onResponse(
                        call: Call<DataClassCheekDiscount>,
                        response: Response<DataClassCheekDiscount>
                    ) {
                        val data = response.body()
                        if (data != null) {
                            if (data.valid) {

                                classdialog.stop()

                                txt_payable.text = data.value.toString()
                                Toast.makeText(
                                    this@BasketActivity,
                                    "کد تخفیف اعمال شد",
                                    Toast.LENGTH_LONG
                                ).show()


                                sharedPreferences.edit()
                                    .putString("discountcode", Edit_Dis.text.toString())
                                    .apply()
                            } else {
                                classdialog.stop()

                                Toast.makeText(
                                    this@BasketActivity,
                                    "کد تخفیف معتبر نمیباشد",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<DataClassCheekDiscount>, t: Throwable) {
                        classdialog.stop()

                        Toast.makeText(
                            this@BasketActivity,
                            "لطفا از اتصال خود به اینترنت مطمئن شوید",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }

        btn_finalSabt.setOnClickListener {
            Log.d("sina", isnullS.toString())
            if (isnullS) {
                Toast.makeText(this, "سبد خرید شما خالی است!", Toast.LENGTH_SHORT).show()
            } else startActivity(Intent(this, AdresschoiceActivity::class.java))

        }


    }

    override fun onclick(Uid: String, quantiti: Int) {

        ApiService().getRetrofit().changequantiti(Uid, quantiti)
            .enqueue(object : Callback<DataClassChangequantiti> {
                override fun onResponse(
                    call: Call<DataClassChangequantiti>,
                    response: Response<DataClassChangequantiti>
                ) {
                    val data = response.body()
                    if (data != null) {
                        Toast.makeText(this@BasketActivity, "تغییرات اعمال شد", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                        startActivity(Intent(this@BasketActivity, BasketActivity::class.java))
                    }

                }

                override fun onFailure(call: Call<DataClassChangequantiti>, t: Throwable) {
                    Toast.makeText(
                        this@BasketActivity,
                        "لطفا از اتصال خود به اینترنت مطمئن شوید",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    override fun trashclick(Uid: String, quantiti: Int) {

        ApiService().getRetrofit().changequantiti(Uid, quantiti)
            .enqueue(object : Callback<DataClassChangequantiti> {
                override fun onResponse(
                    call: Call<DataClassChangequantiti>,
                    response: Response<DataClassChangequantiti>
                ) {
                    val data = response.body()
                    if (data != null) {
                        Toast.makeText(this@BasketActivity, "تغییرات اعمال شد", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                        startActivity(Intent(this@BasketActivity, BasketActivity::class.java))
                    }

                }

                override fun onFailure(call: Call<DataClassChangequantiti>, t: Throwable) {
                    Toast.makeText(
                        this@BasketActivity,
                        "لطفا از اتصال خود به اینترنت مطمئن شوید",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })

    }
}