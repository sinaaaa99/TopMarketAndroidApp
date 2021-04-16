package com.example.topmarket.di

import com.example.topmarket.fragment.DasteFragment
import com.example.topmarket.fragment.HomeFragment
import com.example.topmarket.fragment.profileFragment
import com.example.topmarket.fragment.searchFragment
import com.example.topmarket.model.ModelDasteFragment
import com.example.topmarket.model.ModelMainActivity
import com.example.topmarket.model.modelHomeFragment
import com.example.topmarket.presenter.PresenterDasteFragment
import com.example.topmarket.presenter.presenterHomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import org.koin.dsl.module.module

val fragmentmodules = module {

    single { HomeFragment() }
    single { profileFragment() }
    single { searchFragment() }
    single { DasteFragment() }
}

val modelmodules = module {

    single { ModelMainActivity() }
    single { modelHomeFragment() }
    single { ModelDasteFragment() }
}

val Appmadules = module {

    single { BottomNavigationView(get()) }
    single { Picasso.get() }
}

val presentermudules = module {

    single { presenterHomeFragment(get() as HomeFragment, get() as modelHomeFragment) }
    single { PresenterDasteFragment(get() as DasteFragment, get() as ModelDasteFragment) }
}


