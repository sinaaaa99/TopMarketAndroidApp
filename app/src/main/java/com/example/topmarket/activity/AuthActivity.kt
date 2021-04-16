package com.example.topmarket.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.storeproma.DataClassValidation
import com.example.topmarket.R
import com.example.topmarket.etc.AlertDialogLoad
import com.example.topmarket.net.ApiServiceLogin
import kotlinx.android.synthetic.main.activity_auth.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthActivity : AppCompatActivity() {

    lateinit var tokenSheared: SharedPreferences
    var openApp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val classdialog = AlertDialogLoad(this)

        tokenSheared = getSharedPreferences("tokenSh", Context.MODE_PRIVATE)

        openApp = tokenSheared.getBoolean("openApp", false)
        if (openApp) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


        val userId = intent.getStringExtra("clientid").toString()

        Bvalidcode.setOnClickListener {

            classdialog.start()

            ApiServiceLogin().getRetrofitLogin()
                .userlogincode(userId, edittextvalidcode.text.toString())
                .enqueue(object : Callback<DataClassValidation> {
                    override fun onResponse(
                        call: Call<DataClassValidation>,
                        response: Response<DataClassValidation>
                    ) {
                        val success = response.body()
                        if (success != null) {

                            classdialog.stop()

                            tokenSheared.edit()
                                .putString("token", success.token)
                                .putBoolean("openApp", true)
                                .apply()

                            tokenSheared.getString("token", "hi").toString()

                            startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                            finish()
                        }else{
                            classdialog.stop()
                            Toast.makeText(this@AuthActivity,"کد وارد شده صحیح نیست",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<DataClassValidation>, t: Throwable) {

                        classdialog.stop()
                        Toast.makeText(this@AuthActivity,"لطفا اینترنت خود را چک کنید",Toast.LENGTH_SHORT).show()

                    }
                })

        }

        btn_backlogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}