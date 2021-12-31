package com.example.topmarket.mvvm.model

import androidx.lifecycle.LiveData
import com.example.topmarket.DataClass.DataClassChangequantiti
import com.example.topmarket.DataClass.DataClassSearch
import com.example.topmarket.DataClass.DataclassBasket
import com.example.topmarket.net.ApiService

class MainRepository {

    val mainResponse = MainResponse()

    //دریافت کردن اطلاعات سبد خرید
    fun getBasket(): LiveData<DataclassBasket> {
        return mainResponse.getBasket()
    }

    //حذف کردن محصول از سبد خرید
    fun DeleteProduct(Uid: String, quantiti: Int): LiveData<DataClassChangequantiti> {
        return mainResponse.deleteProduct(Uid, quantiti)
    }

    //تغییر تعداد محصول سبد
    fun ChangeQunt(Uid: String, quantiti: Int): LiveData<DataClassChangequantiti> {
        return mainResponse.ChangeQunt(Uid, quantiti)
    }


    //سرچ محصول
    fun SearchProducts(pageNumber:Int, pruductName:String, size:Int):LiveData<DataClassSearch>{
        return mainResponse.SearchProducts(pageNumber,pruductName,size)
    }
}