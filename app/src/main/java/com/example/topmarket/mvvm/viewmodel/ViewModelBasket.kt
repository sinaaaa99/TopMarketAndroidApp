package com.example.topmarket.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.topmarket.DataClass.DataClassChangequantiti
import com.example.topmarket.mvvm.model.MainRepository

class ViewModelBasket : ViewModel() {

    val mainRepository = MainRepository()

    //حذف کردن محصول از سبد خرید
    fun DeleteProduct(Uid: String, quantiti: Int): LiveData<DataClassChangequantiti> {
        return mainRepository.DeleteProduct(Uid, quantiti)
    }

    //تغییر تعداد محصول سبد
    fun ChangeQunt(Uid: String, quantiti: Int): LiveData<DataClassChangequantiti> {
        return mainRepository.ChangeQunt(Uid, quantiti)

    }
}