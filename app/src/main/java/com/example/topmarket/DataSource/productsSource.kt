package com.example.topmarket.DataSource

import androidx.paging.PageKeyedDataSource
import com.example.topmarket.DataClass.DataClassproducts
import com.example.topmarket.net.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class productsSource(val categoryUid: String) : PageKeyedDataSource<Int, DataClassproducts.Data>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataClassproducts.Data>
    ) {

        ApiService().getRetrofit()
            .getProducts(pageNumber, 10, categoryUid)
            .enqueue(object : Callback<DataClassproducts> {
                override fun onResponse(
                    call: Call<DataClassproducts>,
                    response: Response<DataClassproducts>
                ) {
                    if (response.isSuccessful) {

                        val apiresponse = response.body()!!
                        val responseItem = apiresponse.data

                        responseItem.let {
                            callback.onResult(responseItem, null, pageNumber + 1)
                        }
                    }

                }

                override fun onFailure(call: Call<DataClassproducts>, t: Throwable) {

                }
            })

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, DataClassproducts.Data>
    ) {

        ApiService().getRetrofit()
            .getProducts(params.key, 10, categoryUid)
            .enqueue(object : Callback<DataClassproducts> {
                override fun onResponse(
                    call: Call<DataClassproducts>,
                    response: Response<DataClassproducts>
                ) {
                    if (response.isSuccessful) {

                        val apiresponse = response.body()!!
                        val responceitem = apiresponse.data

                        val key = if (params.key > 1) params.key - 1 else 0

                        responceitem.let {
                            callback.onResult(responceitem, key)
                        }
                    }

                }

                override fun onFailure(call: Call<DataClassproducts>, t: Throwable) {

                }
            })


    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, DataClassproducts.Data>
    ) {

        ApiService().getRetrofit()
            .getProducts(params.key, 10, categoryUid)
            .enqueue(object : Callback<DataClassproducts> {
                override fun onResponse(
                    call: Call<DataClassproducts>,
                    response: Response<DataClassproducts>
                ) {
                    if (response.isSuccessful) {

                        val apiresponse = response.body()!!
                        val responceitem = apiresponse.data

                        val key = params.key + 1

                        responceitem.let {
                            callback.onResult(responceitem, key)
                        }
                    }

                }

                override fun onFailure(call: Call<DataClassproducts>, t: Throwable) {

                }
            })

    }

    companion object {

        val totalPage = 6
        val pageNumber = 0
    }


}