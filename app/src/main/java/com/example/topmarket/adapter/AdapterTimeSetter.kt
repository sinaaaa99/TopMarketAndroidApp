package com.example.topmarket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.topmarket.DataClass.DataClassDaytime
import com.example.topmarket.R
import kotlinx.android.synthetic.main.custom_time.view.*

class AdapterTimeSetter(
    val dataclass: DataClassDaytime,
    val listener: AdapterTimeSetter.listener2
) : BaseAdapter() {

    override fun getCount(): Int = dataclass.data.size

    override fun getItem(position: Int): DataClassDaytime.Data = dataclass.data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.custom_time, null)

        val data1 = getItem(position)
        val textview = view.textView_showTime
        textview.text = data1.day

        listener.setonclick(data1.daysFromNow)

        return view
    }

    interface listener2 {
        fun setonclick(x: Int)
    }

}