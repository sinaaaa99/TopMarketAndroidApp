package com.example.topmarket.mvvm.model

import androidx.lifecycle.MutableLiveData
import com.example.topmarket.DataClass.DataClassChangequantiti
import com.example.topmarket.DataClass.DataClassSearch
import com.example.topmarket.DataClass.DataclassBasket
import com.example.topmarket.net.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainResponse {

    //دریافت کردن اطلاعات سبد خرید
    fun getBasket(): MutableLiveData<DataclassBasket> {

        val dataclassbasket = MutableLiveData<DataclassBasket>()

        ApiService().getRetrofit().getBasket().enqueue(object : Callback<DataclassBasket> {
            override fun onResponse(
                call: Call<DataclassBasket>,
                response: Response<DataclassBasket>
            ) {
                dataclassbasket.value = response.body()

            }

            override fun onFailure(call: Call<DataclassBasket>, t: Throwable) {

            }


        })
        return dataclassbasket
    }


    //حذف کردن محصول از سبد خرید
    fun deleteProduct(Uid: String, quantiti: Int): MutableLiveData<DataClassChangequantiti> {
        val data = MutableLiveData<DataClassChangequantiti>()

        ApiService().getRetrofit().changequantiti(Uid, quantiti)
            .enqueue(object : Callback<DataClassChangequantiti> {
                override fun onResponse(
                    call: Call<DataClassChangequantiti>,
                    response: Response<DataClassChangequantiti>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<DataClassChangequantiti>, t: Throwable) {

                }

            })

        return data

    }

    //تغییر تعداد محصول سبد
    fun ChangeQunt(Uid: String, quantiti: Int): MutableLiveData<DataClassChangequantiti> {
        val data = MutableLiveData<DataClassChangequantiti>()

        ApiService().getRetrofit().changequantiti(Uid, quantiti)
            .enqueue(object : Callback<DataClassChangequantiti> {
                override fun onResponse(
                    call: Call<DataClassChangequantiti>,
                    response: Response<DataClassChangequantiti>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<DataClassChangequantiti>, t: Throwable) {

                }


            })
        return data

    }

    //چک کردن تخفیف


    //سرچ محصول
    fun SearchProducts(pageNumber:Int, pruductName:String, size:Int): MutableLiveData<DataClassSearch> {
        val dataclassbasket = MutableLiveData<DataClassSearch>()

        ApiService().getRetrofit().searchProducts(pageNumber,pruductName,size)
            .enqueue(object :Callback<DataClassSearch>{
                override fun onResponse(
                    call: Call<DataClassSearch>,
                    response: Response<DataClassSearch>
                ) {
                    if (response.isSuccessful){

                        dataclassbasket.value=response.body()
                    }
                }

                override fun onFailure(call: Call<DataClassSearch>, t: Throwable) {

                }

            })

        return dataclassbasket


    }

}