package com.example.topmarket.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.DataClass.DataClassCategory
import com.example.topmarket.R
import com.example.topmarket.activity.subActivity
import com.squareup.picasso.Picasso
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class recycler_AllCa(
    val dataclass: DataClassCategory,
    val listener: ListenerAllCategory
) : RecyclerView.Adapter<recycler_AllCa.viewholder>(), KoinComponent {

    val picasso: Picasso by inject()

    inner class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageview = itemView.findViewById<ImageView>(R.id.category_item)

        fun setclick(pos: Int) {

            imageview.setOnClickListener {

                val categoryuid = dataclass.data[pos].categoryUid

                listener.onClick(categoryuid)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        viewholder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.category_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        picasso.load(dataclass.data[position].iconUrl)
            .into(holder.imageview)

        holder.setclick(position)
    }

    override fun getItemCount() = dataclass.data.size

    interface ListenerAllCategory {
        fun onClick(pos: String)
    }
}