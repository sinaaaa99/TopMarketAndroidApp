package com.example.topmarket.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataClassproducts
import com.example.topmarket.DataClass.DataclassShoping
import com.example.topmarket.R
import com.example.topmarket.activity.detailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.products_view.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class Adapter_products(
    val listener: Onclickpru,
    val context: Context
) :
    PagedListAdapter<DataClassproducts.Data, Adapter_products.viewHolder>(USER_COMRATATOR),
    KoinComponent {
    var producList: MutableList<DataclassShoping.Product> = arrayListOf()
    var listquantiti = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    var quantiti = 1

    val picasoo: Picasso by inject()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.products_view, parent, false)
        return viewHolder(view)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val products = getItem(position)
        products?.let {
            holder.setdata(products)
        }

    }


    inner class viewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val image = itemview.findViewById<ImageView>(R.id.imageView_prud)
        val name = itemview.findViewById<TextView>(R.id.textView_prud_name)
        var marked = itemview.findViewById<TextView>(R.id.customtextview_prud_mark)
        val mony = itemview.findViewById<TextView>(R.id.textView_prud_mony)
        val persent = itemview.findViewById<TextView>(R.id.txt_percent)
        val btn_add = itemview.findViewById<Button>(R.id.button_prud_add)

        //        val layoutroott = itemview.findViewById<Button>(R.id.cons)
//        val btn_mosh = itemview.button_Moshahede
        val spinr = itemview.spinner_quantiti

        //invisables
        val btn_mojod = itemview.button_mojodkon
        val txt_mojod = itemview.textViewEtmam
        val out_of_stack_image = itemview.out_of_stack_image

        fun setdata(products: DataClassproducts.Data) {

            picasoo.load(products.iconLink)
                .into(image)

            name.text = products.name
            marked.text = products.price.toString() + " تومان "
            marked.paintFlags = marked.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            mony.text = products.priceWithDiscount.toString() + " تومان "
//            mony.paintFlags = mony.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            persent.text = " % " + products.discountPercent.toString()

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
//                    Log.d("sina","1:"+quantiti.toString())


                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }


            btn_add.setOnClickListener {

//                Log.d("sina","2:"+quantiti.toString())
                producList.add(DataclassShoping.Product(products.productUid, quantiti))

                Log.d("sina", producList.toString())

                listener.onclick(producList)
                producList.clear()

//                listener.onclick(products.productUid,1)
            }

            image.setOnClickListener {
                val intent = Intent(context, detailActivity::class.java)
                intent.putExtra("image", products.iconLink)
                intent.putExtra("name", products.name)
                intent.putExtra("price", products.price)
                intent.putExtra("priceDis", products.priceWithDiscount)
                intent.putExtra("dec", products.description)
                intent.putExtra("Uid", products.productUid)
                intent.putExtra("percent", products.discountPercent)
                context.startActivity(intent)
            }


            val pru_quan = products.quantity
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

    companion object {

        private val USER_COMRATATOR = object : DiffUtil.ItemCallback<DataClassproducts.Data>() {
            override fun areItemsTheSame(
                oldItem: DataClassproducts.Data,
                newItem: DataClassproducts.Data
            ): Boolean =
                oldItem.code == newItem.code

            override fun areContentsTheSame(
                oldItem: DataClassproducts.Data,
                newItem: DataClassproducts.Data
            ): Boolean = newItem == oldItem

        }
    }

    interface Onclickpru {
        fun onclick(listdat: MutableList<DataclassShoping.Product>)
    }
}