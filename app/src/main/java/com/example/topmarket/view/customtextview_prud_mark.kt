package com.example.topmarket.view

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class customtextview_prud_mark(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    init {
        drawline()
        textSize = 14f
    }

    fun setCustomText(value: String) {

        text = value

        drawline()
    }

    private fun drawline() {

        val span = SpannableString(text)
        span.setSpan(StrikethroughSpan(), 0, text.length, Spanned.SPAN_MARK_MARK)
        text = span.toString()
    }
}