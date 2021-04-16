package com.example.topmarket.model

import androidx.fragment.app.Fragment
import com.example.topmarket.fragment.DasteFragment
import com.example.topmarket.fragment.HomeFragment
import com.example.topmarket.fragment.profileFragment
import com.example.topmarket.fragment.searchFragment
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ModelMainActivity : KoinComponent {

    val homeFragment:HomeFragment by inject()
    val profileFragment:profileFragment by inject()
    val searchFragment:searchFragment by inject()
    val dasteFragment:DasteFragment by inject()

    companion object{

        val KEY_HOME_FRAGMENT="homeFragment"
        val KEY_PROFILE_FRAGMENT="profileFragment"
        val KEY_SEARCH_FRAGMENT="searchFragment"
        val KEY_DASTE_FRAGMENT="dasteFragment"
    }

    private val homefragment: HomeFragment by inject()

    fun getfragmenthome() = homefragment

    fun getallfragment()= mapOf<String,Fragment>(

        KEY_HOME_FRAGMENT to homeFragment,
        KEY_PROFILE_FRAGMENT to profileFragment,
        KEY_DASTE_FRAGMENT to dasteFragment,
        KEY_SEARCH_FRAGMENT to searchFragment
    )
}