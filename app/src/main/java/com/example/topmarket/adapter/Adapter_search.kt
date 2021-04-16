package com.example.topmarket.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataClassSearch
import com.example.topmarket.R
import com.example.topmarket.activity.detailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.castom_search.view.*
import org.koin.dsl.module.Module

class Adapter_search(val dataclass: DataClassSearch, val listener: searchsend) :
    RecyclerView.Adapter<Adapter_search.viewholderSearch>() {

    inner class viewholderSearch(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val image = itemview.imageView_se
        val name = itemview.textView_se_name
        val price = itemview.textView_se_price
        val dis = itemview.textView_se_dis
        val layout = itemview.constraitSearch

        fun binding(position: Int) {
            Picasso.get().load(dataclass.data[position].iconLink).into(image)
            name.text = dataclass.data[position].name
            price.text = dataclass.data[position].price.toString()
            dis.text = dataclass.data[position].priceWithDiscount.toString()

            layout.setOnClickListener {

                listener.onclick(
                    dataclass.data[position].name,
                    dataclass.data[position].iconLink,
                    dataclass.data[position].price,
                    dataclass.data[position].priceWithDiscount,
                    dataclass.data[position].description,
                    dataclass.data[position].productUid,
                    dataclass.data[position].discountPercent
                )
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholderSearch {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.castom_search, parent, false)
        return viewholderSearch(view)
    }

    override fun onBindViewHolder(holder: viewholderSearch, position: Int) {
        holder.binding(position)
    }

    override fun getItemCount(): Int = dataclass.data.size

    interface searchsend {

        fun onclick(
            name: String,
            image: String,
            price: Int,
            discount: Int,
            decrib: String,
            Uid: String,
            percent: Int
        )
    }
}