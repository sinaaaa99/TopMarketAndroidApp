package com.example.topmarket.presenter

import android.util.Log
import android.widget.Toast
import com.example.topmarket.DataClass.DataClassCategory
import com.example.topmarket.adapter.recycler_AllCa
import com.example.topmarket.etc.Baselifesycle
import com.example.topmarket.fragment.HomeFragment
import com.example.topmarket.model.modelHomeFragment
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class presenterHomeFragment(
    val view: HomeFragment,
    val model: modelHomeFragment
) : Baselifesycle {
    override fun onCreate() {

        setDataOnCategory()

    }

    private fun setDataOnCategory() {

    }


    override fun onDestroy() {

    }
}
