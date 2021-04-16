package com.example.topmarket.etc

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.topmarket.R

class AlertDialogLoad(context: Context) {

    val view = LayoutInflater.from(context).inflate(R.layout.custom_dialogload, null)

    val createAlart = AlertDialog.Builder(context)
        .setView(view)
        .setCancelable(false)
        .create()

    fun start() {
        createAlart.show()
    }

    fun stop() {
        createAlart.cancel()
    }
}