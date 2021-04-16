package com.example.topmarket.presenter

import com.example.topmarket.etc.Baselifesycle
import com.example.topmarket.model.ModelMainActivity
import com.example.topmarket.view.viewMainActivity

class presenterMainActivity(
    val view: viewMainActivity,
    val model: ModelMainActivity
) : Baselifesycle {
    override fun onCreate() {

        fragmenthomehandler()

        setallfragment()

    }

    private fun setallfragment() {
        view.clickonbottonnav(model.getallfragment())
    }

    fun fragmenthomehandler(){

        view.showfragment(model.getfragmenthome())
    }



    override fun onDestroy() {

    }

}