package com.example.topmarket.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmarket.DataClass.DataClassResponseShop
import com.example.topmarket.DataClass.DataclassShoping
import com.example.topmarket.DataSource.proudctsViewModel
import com.example.topmarket.R
import com.example.topmarket.adapter.Adapter_products
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.activity_products22.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductsActivity : AppCompatActivity(), Adapter_products.Onclickpru {

    lateinit var dataclassShoping: DataclassShoping

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val categoryID = intent.getStringExtra("categoryUid")
        Log.i("test", categoryID!!)


        val adapter = Adapter_products(this, this)
        recyclerview_products.layoutManager = LinearLayoutManager(this)


        val itemviewModel = ViewModelProviders.of(this)
            .get(proudctsViewModel::class.java)

        itemviewModel.productpagedList(categoryID!!)

        itemviewModel.productsPagedList.observe(this, Observer {
            adapter.submitList(it)
        })

        recyclerview_products.adapter = adapter

        buttonaddtoshop.setOnClickListener {

        }
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
                        AlertDialog.Builder(this@ProductsActivity)
                            .setTitle("خرید")
                            .setMessage("محصول با موفقیت به سبد خرید اضافه شد")
                            .setPositiveButton("مشاهده سبد خرید") { dialog, witch ->

                                startActivity(
                                    Intent(
                                        this@ProductsActivity,
                                        BasketActivity::class.java
                                    )
                                )
                            }
                            .setNegativeButton("ادامه ی خرید") { _, _ ->
                            }
                            .show()
                    } else Toast.makeText(
                        this@ProductsActivity,
                        "این تعداد محصول موجود نیست",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(call: Call<DataClassResponseShop>, t: Throwable) {
                }
            })
    }

}
