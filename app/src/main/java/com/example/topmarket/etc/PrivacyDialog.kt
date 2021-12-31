package com.example.topmarket.etc

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class PrivacyDialog(context: Context) {

    val Define = AlertDialog.Builder(context)
        .setTitle("حریم شخصی")
        .setMessage("برای ثبت نام در برنامه افلند تنها شماره تلفن الزامی است. شماره موبایل شما برای ارسال رمز ورود برنامه مورد استفاده قرار میگیرد. \n" +
                "\n" +
                "پس از ثبت نام در قسمت پروفایل شما میتوانید به صورت اختیاری نام، نام خانوادگی و ایمیل خود را ثبت کنید. تمام این موارد برای ارتباط بهتر با شما در سیستم نگهداری می شود.")
        .setPositiveButton("متوجه شدم", DialogInterface.OnClickListener { dialog, which ->

        })
        .create()


    fun start() {
        Define.show()
    }

    fun stop() {
        Define.cancel()
    }
}