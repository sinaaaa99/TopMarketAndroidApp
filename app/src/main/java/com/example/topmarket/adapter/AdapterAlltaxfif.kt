package com.example.topmarket.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataclassShoping
import com.example.topmarket.DataClass.DataclassTaxfif
import com.example.topmarket.R
import com.example.topmarket.activity.detailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.products_view.view.*

class AdapterAlltaxfif(
    val dataclass: DataclassTaxfif,
    val listener: senddata,
    val context: Context
) :
    RecyclerView.Adapter<AdapterAlltaxfif.viewholdertaxfifall>() {

    var producList: MutableList<DataclassShoping.Product> = arrayListOf()
    var listquantiti = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    var quantiti = 1


    inner class viewholdertaxfifall(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val image = itemview.imageView_prud
        val name = itemview.textView_prud_name
        val marked = itemview.customtextview_prud_mark
        val mony = itemview.textView_prud_mony
        val persent = itemview.txt_percent
        val btn_add = itemview.button_prud_add

        //        val btn_mosh = itemview.button_Moshahede
        val spinr = itemview.spinner_quantiti
//        val layoutroott = itemview.findViewById<Button>(R.id.cons)

        //invisables
        val btn_mojod = itemview.button_mojodkon
        val txt_mojod = itemview.textViewEtmam
        val out_of_stack_image = itemview.out_of_stack_image

        fun binding(position: Int) {

            Picasso.get().load(dataclass.data[position].iconLink).into(image)
            name.text = dataclass.data[position].name
            marked.text = dataclass.data[position].price.toString() + " تومان "
            marked.paintFlags = marked.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            mony.text = dataclass.data[position].priceWithDiscount.toString() + " تومان "
            persent.text = " % " + dataclass.data[position].discountPercent.toString()

//            listener.getsize(dataclass.data.size)

            spinr.adapter =
                ArrayAdapter(context, android.R.layout.simple_list_item_1, listquantiti)

            spinr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    quantiti = listquantiti[position]
                    Log.d("sina", "1:" + quantiti.toString())


                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }


            btn_add.setOnClickListener {

//                Log.d("sina","2:"+quantiti.toString())
                producList.add(
                    DataclassShoping.Product(
                        dataclass.data[position].productUid,
                        quantiti
                    )
                )
//                Log.d("sina",producList.toString())

                listener.onclick(producList)
                producList.clear()

//                listener.onclick(products.productUid,1)
            }

            image.setOnClickListener {
                val intent = Intent(context, detailActivity::class.java)
                intent.putExtra("image", dataclass.data[position].iconLink)
                intent.putExtra("name", dataclass.data[position].name)
                intent.putExtra("price", dataclass.data[position].price)
                intent.putExtra("priceDis", dataclass.data[position].priceWithDiscount)
                intent.putExtra("dec", dataclass.data[position].description)
                intent.putExtra("Uid", dataclass.data[position].productUid)
                intent.putExtra("percent", dataclass.data[position].discountPercent)
                context.startActivity(intent)
            }

            val pru_quan = dataclass.data[position].quantity
            if (pru_quan == 0) {
                out_of_stack_image.visibility = View.VISIBLE

                btn_add.visibility = View.GONE
                spinr.visibility = View.GONE
            } else {
                btn_add.visibility = View.VISIBLE
                spinr.visibility = View.VISIBLE

                btn_mojod.visibility = View.GONE
                txt_mojod.visibility = View.GONE
                out_of_stack_image.visibility = View.GONE
            }

            btn_mojod.setOnClickListener {
                Toast.makeText(
                    context,
                    "ممنون بابت اطلاع دادنتون،به زودی اضافه خواهد شد",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholdertaxfifall {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.products_view, parent, false)
        return viewholdertaxfifall(view)
    }

    override fun onBindViewHolder(holder: viewholdertaxfifall, position: Int) {
        holder.binding(position)
    }

    override fun getItemCount(): Int = dataclass.size

    interface senddata {

        fun getsize(size: Int)
        fun onclick(listdat: MutableList<DataclassShoping.Product>)
    }
}