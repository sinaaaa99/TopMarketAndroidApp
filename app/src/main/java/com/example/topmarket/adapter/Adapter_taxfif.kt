package com.example.topmarket.adapter

import android.content.Context
import android.graphics.Paint
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataclassTaxfif
import com.example.topmarket.R
import com.example.topmarket.androidWrapper.App
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.castom_taxfif.view.*

class Adapter_taxfif(val dataclass: DataclassTaxfif, val layouttclick: layoutclick) :
    RecyclerView.Adapter<Adapter_taxfif.viewholderTax>() {

    inner class viewholderTax(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val image = itemview.imageView_taxfif
        val price = itemview.textView_taxfif_price
        val dicount = itemview.textView_taxfif_dis
        val name = itemview.textView_taxfif_name
        val layout = itemview.layouttaxfifha
        val persentDis = itemview.textView84

        fun binding(position: Int) {

            Picasso.get().load(dataclass.data[position].iconLink)
                .into(image)


            /*val context: Context = App.applicationContext()
            Glide.with(context)
                .load(dataclass.data[position].iconLink.toString())
                .into(image)*/

            price.text = dataclass.data[position].price.toString() + " تومان "
            price.paintFlags = price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            dicount.text = dataclass.data[position].priceWithDiscount.toString() + " تومان "
            name.text = dataclass.data[position].name
            persentDis.text = dataclass.data[position].discountPercent.toString()

            /*val x=dataclass.data[position].priceWithDiscount.toString()

            val span = SpannableString(x)
            span.setSpan(StrikethroughSpan(), 0, x.length, Spanned.SPAN_MARK_MARK)
            dicount.text = span.toString()*/

            layout.setOnClickListener {

                layouttclick.onclick(
                    dataclass.data[position].name,
                    dataclass.data[position].iconLink,
                    dataclass.data[position].price,
                    dataclass.data[position].priceWithDiscount,
                    dataclass.data[position].description,
                    dataclass.data[position].productUid,
                    dataclass.data[position].discountPercent,
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholderTax {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.castom_taxfif, parent, false)
        return viewholderTax(view)
    }

    override fun onBindViewHolder(holder: viewholderTax, position: Int) {
        holder.binding(position)
    }

    override fun getItemCount(): Int = dataclass.data.size

    interface layoutclick {
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