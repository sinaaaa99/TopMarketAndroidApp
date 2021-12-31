package com.example.topmarket.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.topmarket.DataClass.DataClassSearch
import com.example.topmarket.DataClass.DataclassBasket
import com.example.topmarket.mvvm.model.MainRepository

class MainViewModel : ViewModel() {

    val mainRepository = MainRepository()

    //دریافت کردن اطلاعات سبد خرید
    fun getBasket(): LiveData<DataclassBasket> {
        return mainRepository.getBasket()
    }

    //سرچ محصول
    fun searchProducts(pruductName: String): LiveData<DataClassSearch> {
        return mainRepository.SearchProducts(0, pruductName, 50)
    }
}