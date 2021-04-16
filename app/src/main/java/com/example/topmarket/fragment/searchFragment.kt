package com.example.topmarket.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmarket.DataClass.DataClassSearch
import com.example.topmarket.R
import com.example.topmarket.activity.detailActivity
import com.example.topmarket.adapter.Adapter_search
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.dsl.module.applicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class searchFragment : Fragment(), Adapter_search.searchsend {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val classdialog = AlertDialogLoad(activity!!)

        recyclerviewSearch.layoutManager = LinearLayoutManager(activity)

        imageView_search.setOnClickListener {


            if (editText_search.text.isEmpty()) {
                editText_search.error = "لطفا ابتدا اسم محصول را وارد کنید"
                editText_search.requestFocus()
                return@setOnClickListener
            }
            classdialog.start()

            ApiService().getRetrofit().searchProducts(0, editText_search.text.toString(), 100)
                .enqueue(object : Callback<DataClassSearch> {
                    override fun onResponse(
                        call: Call<DataClassSearch>,
                        response: Response<DataClassSearch>
                    ) {
                        val data = response.body()
                        if (data != null) {

                            recyclerviewSearch.adapter = Adapter_search(data, this@searchFragment)
                            classdialog.stop()
                        } else {
                            classdialog.stop()

                            Toast.makeText(
                                activity,
                                "متاسفانه چیزی یافت نشد",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    }

                    override fun onFailure(call: Call<DataClassSearch>, t: Throwable) {
                        classdialog.stop()
                        Toast.makeText(
                            activity,
                            "لطفا از اتصال خود به اینترنت مطمئن شوید",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }


    }

    override fun onclick(
        name: String,
        image: String,
        price: Int,
        discount: Int,
        decrib: String,
        Uid: String,
        percent: Int
    ) {
        val intent = Intent(activity, detailActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("image", image)
        intent.putExtra("price", price)
        intent.putExtra("priceDis", discount)
        intent.putExtra("dec", decrib)
        intent.putExtra("Uid", Uid)
        intent.putExtra("percent", percent)
        startActivity(intent)
    }
}