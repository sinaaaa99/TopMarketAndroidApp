package com.example.topmarket.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataclassAdressList
import com.example.topmarket.R
import com.example.topmarket.activity.PayPalActivity
import kotlinx.android.synthetic.main.custom_adress.view.*

class Adapter_adress(val dataclass: DataclassAdressList, val context: Context) :
    RecyclerView.Adapter<Adapter_adress.viewholderA>() {

    inner class viewholderA(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val btn = itemview.btn_adressA
        val title = itemview.txt_titleA
        val addres = itemview.txt_adressA
        val posti = itemview.txt_postA

        fun binding(pos: Int) {

            title.text = dataclass.data[pos].title
            addres.text = dataclass.data[pos].address
            posti.text = dataclass.data[pos].postalCode

            btn.setOnClickListener {
                val intent = Intent(context, PayPalActivity::class.java)
                intent.putExtra("adressUid", dataclass.data[pos].addressUid)
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholderA {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_adress, parent, false)
        return viewholderA(view)
    }

    override fun onBindViewHolder(holder: viewholderA, position: Int) {
        holder.binding(position)

    }

    override fun getItemCount(): Int = dataclass.data.size

}