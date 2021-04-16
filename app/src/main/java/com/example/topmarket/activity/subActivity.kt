package com.example.topmarket.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmarket.DataClass.DataClassCategory
import com.example.topmarket.DataClass.DataClassSubCategory
import com.example.topmarket.R
import com.example.topmarket.adapter.recycler_AllCa
import com.example.topmarket.adapter.recycler_SubCa
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class subActivity : AppCompatActivity() , recycler_SubCa.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val classdialog = AlertDialogLoad(this)
        classdialog.start()


        recyclerviewtest.layoutManager =
            GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)


        val categoryuid = intent.getStringExtra("intenetUid")

        ApiService().getRetrofit().getSubCategory(categoryuid.toString())
            .enqueue(object : Callback<DataClassSubCategory> {
                override fun onResponse(
                    call: Call<DataClassSubCategory>,
                    response: Response<DataClassSubCategory>
                ) {
                    val data = response.body()

                    if (data != null) {

                        classdialog.stop()

                        recyclerviewtest.adapter = recycler_SubCa(data, this@subActivity,this@subActivity)
                    }

                }

                override fun onFailure(call: Call<DataClassSubCategory>, t: Throwable) {
                    classdialog.stop()
                    Toast.makeText(
                        this@subActivity,
                        "لطفا از اتصال خود به اینترنت مطمئن شوید",
                        Toast.LENGTH_LONG
                    ).show()

                }
            })


    }

    override fun onClick(id: String) {
//        Log.i("test" , "onclick:${id}")
        val intent = Intent(this, ProductsActivity::class.java)
        intent.putExtra("categoryUid", id)
        startActivity(intent)
    }
}