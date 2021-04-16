package com.example.topmarket.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataClassSubCategory
import com.example.topmarket.R
import com.squareup.picasso.Picasso
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class recycler_SubCa(
    val dataclassSub: DataClassSubCategory,
    val context: Context,
    val listener:OnClickListener
) : RecyclerView.Adapter<recycler_SubCa.viewholder>(), KoinComponent {

    val picasso: Picasso by inject()

    inner class viewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val imageview = itemview.findViewById<ImageView>(R.id.category_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        viewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_item,
                parent, false
            )
        )

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        picasso.load(dataclassSub.data[position].iconUrl)
            .into(holder.imageview)

        holder.imageview.setOnClickListener {

           listener.onClick(dataclassSub.data[position].categoryUid)

//            getdata(dataclassSub.data[position].categoryUid)

        }
    }

    override fun getItemCount() = dataclassSub.data.size

    interface OnClickListener{
        fun onClick(id:String)
    }
}