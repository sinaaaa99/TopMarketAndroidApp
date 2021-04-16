package com.example.topmarket.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.topmarket.DataClass.DataClassAddProfile
import com.example.topmarket.DataClass.DataclassResponseAddProfile
import com.example.topmarket.R
import com.example.topmarket.fragment.profileFragment
import com.example.topmarket.net.ApiService
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    lateinit var dataclass: DataClassAddProfile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        btnprofile.setOnClickListener {

            if (edit_name_profile.length() == 0) {
                edit_name_profile.error = "لطفا نام خود را وارد کنید"
                edit_last_profile.requestFocus()
                return@setOnClickListener
            }
            if (edit_last_profile.length() == 0) {
                edit_last_profile.error = "لطفا نام خانوادگی خود را وارد کنید"
                edit_last_profile.requestFocus()
                return@setOnClickListener
            }

            val name = edit_name_profile.text.toString()
            val last = edit_last_profile.text.toString()
            val email = edit_email_profile.text.toString()

            dataclass = DataClassAddProfile(email, name, last)

            ApiService().getRetrofit().addprofile(dataclass)
                .enqueue(object : Callback<DataclassResponseAddProfile> {
                    override fun onResponse(
                        call: Call<DataclassResponseAddProfile>,
                        response: Response<DataclassResponseAddProfile>
                    ) {
                        val data = response.body()
                        if (data != null) {

                            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
                            finish()
                        }

                    }

                    override fun onFailure(call: Call<DataclassResponseAddProfile>, t: Throwable) {
                        Toast.makeText(
                            this@ProfileActivity,
                            "لطفا اتصال خود را به نت چک کنید",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

        }


    }
}