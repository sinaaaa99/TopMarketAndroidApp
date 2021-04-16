package com.example.topmarket.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.topmarket.DataClass.DataClassResponseShop
import com.example.topmarket.DataClass.DataclassShoping
import com.example.topmarket.R
import com.example.topmarket.net.ApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class detailActivity : AppCompatActivity() {

    var productAdd: MutableList<DataclassShoping.Product> = arrayListOf()
    lateinit var dataclassShoping: DataclassShoping

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        val price = intent.getIntExtra("price", 0)
        val pricDis = intent.getIntExtra("priceDis", 0)
        val dec = intent.getStringExtra("dec")
        val Uid = intent.getStringExtra("Uid")
        val percent = intent.getIntExtra("percent", 0)

        Picasso.get().load(image).into(imageView_D_img)
        textView25.text = name
        textviewpricedetail.text = price.toString()
        textView93.text = pricDis.toString()
        textView_detail.text = dec
        textView_de_perc.text = percent.toString()
        textView87.text = pricDis.toString()

        button_add_detail.setOnClickListener {

            productAdd.add(DataclassShoping.Product(Uid!!, 1))
            dataclassShoping = DataclassShoping(productAdd)

            ApiService().getRetrofit().addShoping(dataclassShoping)
                .enqueue(object : Callback<DataClassResponseShop> {
                    override fun onResponse(
                        call: Call<DataClassResponseShop>,
                        response: Response<DataClassResponseShop>
                    ) {
                        if (response.body() != null) {
                            AlertDialog.Builder(this@detailActivity)
                                .setTitle("خرید")
                                .setMessage("محصول با موفقیت به سبد خرید اضافه شد")
                                .setPositiveButton("مشاهده سبد خرید") { dialog, witch ->

                                    startActivity(
                                        Intent(
                                            this@detailActivity,
                                            BasketActivity::class.java
                                        )
                                    )
                                }
                                .setNegativeButton("ادامه ی خرید") { _, _ ->
                                }
                                .show()
                        } else {
                            Toast.makeText(
                                this@detailActivity,
                                "این محصول موجود نیست",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<DataClassResponseShop>, t: Throwable) {
                        Toast.makeText(
                            this@detailActivity,
                            "لطفا اتصال خود به اینترنت را چک کنید",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }
}