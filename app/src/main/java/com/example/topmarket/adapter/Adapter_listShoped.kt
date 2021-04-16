package com.example.topmarket.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataclassListShoped
import com.example.topmarket.R
import com.example.topmarket.activity.ShopedDerailActivity
import kotlinx.android.synthetic.main.custom_listshoped.view.*

class Adapter_listShoped(
    val dataclass: DataclassListShoped, val listener: listenerIds
) : RecyclerView.Adapter<Adapter_listShoped.viewholder>() {

    inner class viewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        //textView
        val date = itemview.tx_date
        val price = itemview.tx_price
        val status = itemview.tx_status

        //button
        val btn_detaill = itemview.btn_11
        val btn_factor = itemview.btn_12

        fun binding(position: Int) {

            date.text = dataclass.orderList[position].creationDate
            price.text = dataclass.orderList[position].totalPrice.toString()
            status.text = dataclass.orderList[position].orderStatusPersianName

            btn_detaill.setOnClickListener {

                listener.orderUid(dataclass.orderList[position].orderUid)
            }

            btn_factor.setOnClickListener {
                listener.factorUid(dataclass.orderList[position].factorUid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_listshoped, parent, false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.binding(position)
    }

    override fun getItemCount(): Int = dataclass.size

    interface listenerIds {

        fun orderUid(orderUid: String)
        fun factorUid(factorUid: String)
    }
}