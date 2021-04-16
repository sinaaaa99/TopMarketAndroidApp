package com.example.topmarket.model

import com.example.topmarket.fragment.DasteFragment
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class ModelDasteFragment : KoinComponent {

    val DasteFragment: DasteFragment by inject()


}