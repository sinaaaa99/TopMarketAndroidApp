package com.example.topmarket.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.topmarket.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.castom_img.view.*

class AdapterViewpager : RecyclerView.Adapter<AdapterViewpager.ViewHolderViewpager>() {

    private val listviewpager = listOf(
        "https://s17.picofile.com/file/8428110834/photo_2021_03_16_01_15_22.jpg",
        "https://s17.picofile.com/file/8428110842/2.jpg",
        "https://s16.picofile.com/file/8428110850/4.jpg",
        "https://s17.picofile.com/file/8428110868/6.jpg"
    )

    inner class ViewHolderViewpager(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val image = itemview.imageView_viewpager


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderViewpager {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.castom_img, parent, false)
        return ViewHolderViewpager(view)
    }

    override fun onBindViewHolder(holder: ViewHolderViewpager, position: Int) {
        Picasso.get().load(listviewpager[position]).into(holder.image)
    }

    override fun getItemCount(): Int = listviewpager.size
}