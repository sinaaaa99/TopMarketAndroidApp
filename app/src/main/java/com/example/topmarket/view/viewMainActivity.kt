package com.example.topmarket.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.example.topmarket.R
import com.example.topmarket.etc.Utility
import com.example.topmarket.etc.setfragment
import com.example.topmarket.model.ModelMainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.toast
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

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