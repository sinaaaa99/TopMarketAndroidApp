package com.example.topmarket.DataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.topmarket.DataClass.DataClassproducts

class productsDataSourceFactory(private val categoryUid: String):
    DataSource.Factory<Int, DataClassproducts.Data>() {

    val productsLiveDataSource = MutableLiveData<productsSource>()

    override fun create(): DataSource<Int, DataClassproducts.Data> {

        val productsSource = productsSource(categoryUid)

        productsLiveDataSource.postValue(productsSource)

        return productsSource
    }
}