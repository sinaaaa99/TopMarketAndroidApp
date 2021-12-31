package com.example.topmarket.etc

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class AboutDialog(context: Context) {

    val Define= AlertDialog.Builder(context)
        .setTitle("درباره فروشگاه")
        .setMessage("شما میتوانید از میان اجناس موجود سبد خرید خود را کامل نموده و سفارش خود را تکمیل کنید." +
                "پرداخت پس از تحویل اجناس صورت میگیرد" +
                "برای کارمندان شرکت های طرف قرارداد امکان پرداخت اعتباری فراهم است و درهنگام نهایی کردن خرید میتوانید گزینه پرداخت اعتباری را انتخاب کنید.")
        .setCancelable(false)
        .setPositiveButton("متوجه شدم", DialogInterface.OnClickListener { dialog, which ->

        })
        .create()


    fun start(){
        Define.show()
    }

    fun stop(){
        Define.cancel()
    }
}