package com.example.topmarket.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.R

class customView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    val textViewTitle: TextView
    val textViewAll: TextView
    val recyclerViewC: RecyclerView

    init {

        val view = inflate(context, R.layout.custom_view, this)

        textViewTitle = view.findViewById(R.id.textView_titles)
        textViewAll = view.findViewById(R.id.textView_all)
        recyclerViewC = view.findViewById(R.id.recyclerview_main)

        val taypeArray = context.obtainStyledAttributes(attrs, R.styleable.customView)
        val text = taypeArray.getText(R.styleable.customView_tittleText)
        taypeArray.recycle()

        textViewTitle.text = text

    }

}