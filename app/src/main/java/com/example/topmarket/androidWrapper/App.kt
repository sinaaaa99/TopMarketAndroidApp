package com.example.topmarket.androidWrapper

import android.app.Application
import android.content.Context
import com.example.topmarket.di.fragmentmodules
import com.example.topmarket.di.modelmodules
import com.example.topmarket.di.Appmadules
import com.example.topmarket.di.presentermudules
import org.koin.android.ext.android.startKoin

open class App : Application() {

    init {
        instance = this
    }
    companion object {
        private var instance: App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }


    override fun onCreate() {
        super.onCreate()

        val context: Context = applicationContext()

        startKoin(
            this,
            listOf(fragmentmodules, modelmodules, Appmadules, presentermudules)
        )

    }
}