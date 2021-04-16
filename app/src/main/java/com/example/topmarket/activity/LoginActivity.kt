package com.example.topmarket.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.storeproma.DataClassLogin
import com.example.topmarket.R
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.net.ApiService
import com.example.topmarket.net.ApiServiceLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    var isOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val classdialog = AlertDialogLoad(this)

        sharedPreferences = getSharedPreferences("tokenSh", Context.MODE_PRIVATE)


        val phonenum = findViewById<EditText>(R.id.edittextphonenum)
        val invatecode = findViewById<EditText>(R.id.edittextinavtecode)
        val btnvoorod = findViewById<Button>(R.id.btnvoorod)

        isOpen = sharedPreferences.getBoolean("openApp", false)
        if (isOpen) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }



        btnvoorod.setOnClickListener {
            //  Log.i("number",Ephonenumber)
            Log.i("number", invatecode.text.toString())
            Log.i("number", "Ephonenumber")
            // Ephonenumber = phonenum.text.toString()
            //var Einvatecode = invatecode.text.toString().trim()

            if (phonenum.length() == 0) {
                phonenum.error = "لطفا شماره تلفن همراه خود را وارد کنید"
                phonenum.requestFocus()
                return@setOnClickListener
            }

            classdialog.start()

            ApiServiceLogin().getRetrofitLogin()
                .userlogin(phonenum.text.toString(), invatecode.text.toString())
                .enqueue(object : Callback<DataClassLogin> {
                    override fun onResponse(
                        call: Call<DataClassLogin>,
                        response: Response<DataClassLogin>
                    ) {
                        val show = response.body()
                        if (show != null) {

                            val clientid = show.clientUid.toString()
                            val intentID = Intent(this@LoginActivity, AuthActivity::class.java)
                            intentID.putExtra("clientid", clientid)

                            /*sharedPreferences.edit()
                                .putBoolean("true", true)
                                .apply()*/
                            startActivity(intentID)
                            finish()

                            classdialog.stop()

//                            Toast.makeText(this@LoginActivity, "success", Toast.LENGTH_LONG).show()
                        } else {

                            classdialog.stop()

                            Toast.makeText(
                                this@LoginActivity, "لطفا شماره تلفن صحیحی وارد کنید",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<DataClassLogin>, t: Throwable) {

//                        Log.d("maintag",t.message.toString())

                        classdialog.stop()

                        Toast.makeText(
                            this@LoginActivity,
                            "لطفا اتصال خود به اینترنت را چک کنید",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    }
                })
        }

    }
}