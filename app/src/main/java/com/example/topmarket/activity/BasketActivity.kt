package com.example.topmarket.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmarket.DataClass.*
import com.example.topmarket.R
import com.example.topmarket.adapter.recycler_Basket
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.mvvm.viewmodel.MainViewModel
import com.example.topmarket.mvvm.viewmodel.ViewModelBasket
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_products22.*
import kotlinx.android.synthetic.main.activity_products22.view.*
import kotlinx.android.synthetic.main.castom_basketbuy.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BasketActivity : AppCompatActivity(), recycler_Basket.changequantiti {

    lateinit var sharedPreferences: SharedPreferences
    var isnullS = true

    lateinit var mainViewModel: MainViewModel
    lateinit var viewModelBasket: ViewModelBasket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products22)


        //ساختن ویو مدل
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModelBasket = ViewModelProvider(this).get(ViewModelBasket::class.java)

        val classdialog = AlertDialogLoad(this)
        classdialog.start()


        sharedPreferences = getSharedPreferences("DiscountHolder", Context.MODE_PRIVATE)

        recyclerViewBasket.layoutManager = LinearLayoutManager(this)

        mainViewModel.getBasket().observe(this, {

            val data = it

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

        viewModelBasket.ChangeQunt(Uid, quantiti).observe(this, {
            if (it != null) {
                Toast.makeText(this@BasketActivity, "تغییرات اعمال شد", Toast.LENGTH_SHORT)
                    .show()
                finish()
                startActivity(Intent(this@BasketActivity, BasketActivity::class.java))
            }

        })
    }

    override fun trashclick(Uid: String, quantiti: Int) {

        viewModelBasket.DeleteProduct(Uid, quantiti).observe(this, {
            if (it != null) {
                Toast.makeText(this@BasketActivity, "تغییرات اعمال شد", Toast.LENGTH_SHORT)
                    .show()
                finish()
                startActivity(Intent(this@BasketActivity, BasketActivity::class.java))
            }

        })


    }
}