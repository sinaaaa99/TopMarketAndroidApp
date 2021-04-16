package com.example.topmarket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataClassDetailShoped
import com.example.topmarket.DataClass.DataclassFactor
import com.example.topmarket.R
import kotlinx.android.synthetic.main.custom_shoped.view.*

class Adapter_Factor(val dataclass: DataclassFactor) :
    RecyclerView.Adapter<Adapter_Factor.viewholderr>() {

    inner class viewholderr(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val name = itemview.textView57
        val quantiti = itemview.textView67
        val priceDis = itemview.textView69
        val percentdiscount = itemview.textView81
        val pricewithout = itemview.textView71
        val price = itemview.textView73

        fun binding(position: Int) {

            name.text = dataclass.itemList[position].itemName
            pricewithout.text = dataclass.itemList[position].pricePerItem.toString()
            quantiti.text = dataclass.itemList[position].itemTotalCount.toString()
            priceDis.text = dataclass.itemList[position].priceWithDiscountPerItem.toString()
            price.text = dataclass.itemList[position].totalPrice.toString()
            percentdiscount.text = dataclass.itemList[position].discount.toString()


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholderr {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_shoped, parent, false)
        return viewholderr(view)
    }

    override fun onBindViewHolder(holder: viewholderr, position: Int) {
        holder.binding(position)
    }

    override fun getItemCount(): Int = dataclass.itemList.size
}