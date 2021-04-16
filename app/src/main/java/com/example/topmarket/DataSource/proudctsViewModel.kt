package com.example.topmarket.DataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.topmarket.DataClass.DataClassproducts

class proudctsViewModel() : ViewModel() {


    lateinit var  productsPagedList: LiveData<PagedList<DataClassproducts.Data>>

    lateinit var liveDataSource: LiveData<productsSource>

    init {



    }

    fun productpagedList(categoryUid: String){
        val itemDataSourceFactory = productsDataSourceFactory(categoryUid)

        liveDataSource = itemDataSourceFactory.productsLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(productsSource.totalPage)
            .build()

        productsPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}