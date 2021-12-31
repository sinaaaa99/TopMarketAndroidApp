package com.example.topmarket.etc

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class ADialogPermission(context: Context) {

    val Define=AlertDialog.Builder(context)
        .setTitle("دسترسی ها")
        .setMessage("این برنامه هیچ دسترسی نیاز ندارد")
        .setPositiveButton("متوجه شدم",DialogInterface.OnClickListener { dialog, which ->

        })
        .create()


    fun start(){
        Define.show()
    }

    fun stop(){
        Define.cancel()
    }

}