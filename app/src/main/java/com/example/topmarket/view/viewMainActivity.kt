package com.example.topmarket.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.example.topmarket.DataClass.DataClassCartNumber
import com.example.topmarket.DataClass.DataClassGetProfile
import com.example.topmarket.R
import com.example.topmarket.etc.Utility
import com.example.topmarket.etc.setfragment
import com.example.topmarket.model.ModelMainActivity
import com.example.topmarket.net.ApiService
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.toast
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("ViewConstructor")
class viewMainActivity(
    contextinstance: Context,
    private val setfragment: setfragment,
    private val utility: Utility
) : FrameLayout(contextinstance) {

    private val botomnav: BottomNavigationView
    private val imagemenu: AppCompatImageView

    init {

        val view = inflate(context, R.layout.activity_main, this)

        botomnav = view.findViewById(R.id.botn_nav_main)
        imagemenu = view.findViewById(R.id.imgmenu)

        /*ApiService().getRetrofit().
        checkCartNumber().enqueue(object : Callback<DataClassCartNumber> {
            override fun onResponse(
                call: Call<DataClassCartNumber>,
                response: Response<DataClassCartNumber>
            ) {
                val data = response.body()
                if (data != null) {
                    txt_cart_number.text = data.cartNumber.toString()
                    if(data.cartNumber > 0)
                        txt_cart_number.visibility = View.VISIBLE
                    else
                        txt_cart_number.visibility = View.INVISIBLE

                }

            }

            override fun onFailure(call: Call<DataClassCartNumber>, t: Throwable) {

            }
        })*/
    }

    fun showfragment(mainfragment: Fragment) {

        setfragment.addfragment(mainfragment)

        imagemenu.setOnClickListener { utility.onpupupmenu(imagemenu) }

    }

    fun clickonbottonnav(fragment: Map<String, Fragment>) {

        botomnav.setOnNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.home_menu_nav_item -> {

                    setfragment.replacefragment(
                        fragment[ModelMainActivity.KEY_HOME_FRAGMENT] ?: Fragment()
                    )
                    true
                }
                R.id.profile_menu_nav_item -> {
                    setfragment.replacefragment(
                        fragment[ModelMainActivity.KEY_PROFILE_FRAGMENT] ?: Fragment()
                    )
                    true
                }
                R.id.daste_menu_nav_item -> {
                    setfragment.replacefragment(
                        fragment[ModelMainActivity.KEY_DASTE_FRAGMENT] ?: Fragment()
                    )
                    true
                }
                R.id.search_menu_nav_item -> {
                    setfragment.replacefragment(
                        fragment[ModelMainActivity.KEY_SEARCH_FRAGMENT] ?: Fragment()
                    )
                    true
                }
                else -> false
            }
        }
    }
}