package com.example.topmarket.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.topmarket.DataClass.DataClassErjae
import com.example.topmarket.R
import com.example.topmarket.etc.Utility
import com.example.topmarket.etc.setfragment
import com.example.topmarket.model.ModelMainActivity
import com.example.topmarket.net.ApiService
import com.example.topmarket.presenter.presenterMainActivity
import com.example.topmarket.view.viewMainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), setfragment, Utility {

    val model: ModelMainActivity by inject()
    private lateinit var presenter: presenterMainActivity
    var setmessage: String = ""
    private var doubleBackToExitPressedOnce = false
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = viewMainActivity(this, this, this)
        setContentView(view)

        sharedPreferences = getSharedPreferences("tokenSh", Context.MODE_PRIVATE)

        presenter = presenterMainActivity(view, model)
        presenter.onCreate()

        image_add_shopping.setOnClickListener {
            startActivity(Intent(this, BasketActivity::class.java))
        }

        fun setmessage() {
            ApiService().getRetrofit().getmarjoei()
                .enqueue(object : Callback<DataClassErjae> {
                    override fun onResponse(
                        call: Call<DataClassErjae>,
                        response: Response<DataClassErjae>
                    ) {
                        val data = response.body()
                        if (data != null) {

                            setmessage = data.message.toString()
                        }
                    }

                    override fun onFailure(call: Call<DataClassErjae>, t: Throwable) {

                    }
                })
        }
    }

    override fun addfragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fram, fragment)
            .commit()
    }

    override fun replacefragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fram, fragment)
            .commit()
    }


    override fun onpupupmenu(view: View) {

        val pupup = PopupMenu(this, view)
        pupup.menuInflater
            .inflate(R.menu.main_menu, pupup.menu)
        pupup.show()

        pupup.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.marjoMenu -> {
                    val view = LayoutInflater.from(this).inflate(R.layout.custom_marjoe, null)
                    AlertDialog.Builder(this)
                        .setView(view)
                        .setMessage(setmessage)
                        .setPositiveButton("باشه") { _, _ ->


                        }
                        .setCancelable(false)
                        .show()

                    true
                }
                R.id.logoutmenu -> {
                    sharedPreferences.edit().clear().apply()
                    startActivity(Intent(this, LoginnActivity::class.java))
                    finish()

                    true
                }
                else -> false

            }
        }

        /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu, menu)
            return true
    //        return super.onCreateOptionsMenu(menu)
        }*/

        /*override fun onOptionsItemSelected(item: MenuItem): Boolean {

            return when (item.itemId) {

                R.id.marjoMenu -> {
                    Toast.makeText(this@MainActivity, "chishod", Toast.LENGTH_LONG).show()
                    *//*val view = LayoutInflater.from(this).inflate(R.layout.custom_marjoe, null)
                AlertDialog.Builder(this)
                    .setView(view)
                    .setTitle("شرایط مرجوع کردن محصول")
                    .show()*//*

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }*/


    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "لطفا برای خروج دکمه برگشت را مجدد کلیک کنید", Toast.LENGTH_SHORT)
            .show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}