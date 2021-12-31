package com.example.topmarket.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataclassBasket
import com.example.topmarket.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_products22.view.*
import kotlinx.android.synthetic.main.castom_basketbuy.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class recycler_Basket(
    val dataclass: DataclassBasket,
    val changer: changequantiti
) : RecyclerView.Adapter<recycler_Basket.viewholder>(), KoinComponent {

    val picasoo: Picasso by inject()

    inner class viewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val image = itemview.findViewById<ImageView>(R.id.imageView_basket)
        val name = itemview.findViewById<TextView>(R.id.textView_basket_name)
        val marked = itemview.findViewById<TextView>(R.id.textView_basket_price)
        val mony = itemview.findViewById<TextView>(R.id.textView_basket_priceDis)
        val persent = itemview.findViewById<TextView>(R.id.textView_basket_quantiti)
        val buttonsabt = itemview.button_basket_down
        val EditText = itemview.EditTextQuantitti
        val trash = itemview.imageView3


        fun setdata(pos: Int) {

            picasoo.load(dataclass.itemList[pos].iconLink)
                .into(image)

            name.text = dataclass.itemList[pos].productName
            marked.text = dataclass.itemList[pos].price.toString()
            mony.text = dataclass.itemList[pos].priceWithDiscount.toString()
            persent.text = dataclass.itemList[pos].quantity.toString()

            buttonsabt.setOnClickListener {
                changer.onclick(
                    dataclass.itemList[pos].productUid,
                    EditText.text.toString().toInt()
                )
                notifyDataSetChanged()
            }
            trash.setOnClickListener {
                changer.trashclick(
                    dataclass.itemList[pos].productUid,
                    0
                )
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        viewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.castom_basketbuy, parent, false)
        )

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.setdata(position)
    }

    override fun getItemCount() = dataclass.itemCount

    interface changequantiti {

        fun onclick(Uid: String, quantiti: Int)
        fun trashclick(Uid: String, quantiti: Int)
    }

}

