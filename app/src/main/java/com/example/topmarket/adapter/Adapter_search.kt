package com.example.topmarket.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataClassSearch
import com.example.topmarket.R
import com.example.topmarket.activity.detailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.castom_search.view.*
import org.koin.dsl.module.Module

class Adapter_search(val listener: searchsend) :
    RecyclerView.Adapter<Adapter_search.viewholderSearch>() {


    val differCallBack = object : DiffUtil.ItemCallback<DataClassSearch.Data>() {
        override fun areItemsTheSame(
            oldItem: DataClassSearch.Data,
            newItem: DataClassSearch.Data
        ): Boolean {
            return oldItem.productUid == newItem.productUid
        }

        override fun areContentsTheSame(
            oldItem: DataClassSearch.Data,
            newItem: DataClassSearch.Data
        ): Boolean {
            return oldItem == newItem
        }


    }

    val dataclass = AsyncListDiffer(this, differCallBack)

    inner class viewholderSearch(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val image = itemview.imageView_se
        val name = itemview.textView_se_name
        val price = itemview.textView_se_price
        val dis = itemview.textView_se_dis
        val layout = itemview.constraitSearch

        fun binding(position: Int) {
            Picasso.get().load(dataclass.currentList[position].iconLink).into(image)
            name.text = dataclass.currentList[position].name
            price.text = dataclass.currentList[position].price.toString() + " تومان "
            price.paintFlags = price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            dis.text = dataclass.currentList[position].priceWithDiscount.toString() + " تومان "

            layout.setOnClickListener {

                listener.onclick(
                    dataclass.currentList[position].name,
                    dataclass.currentList[position].iconLink,
                    dataclass.currentList[position].price,
                    dataclass.currentList[position].priceWithDiscount,
                    dataclass.currentList[position].description,
                    dataclass.currentList[position].productUid,
                    dataclass.currentList[position].discountPercent
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

    override fun getItemCount(): Int = dataclass.currentList.size

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