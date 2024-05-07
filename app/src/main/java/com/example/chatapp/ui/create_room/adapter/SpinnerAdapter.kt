package com.example.chatapp.ui.create_room.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.SpinnerAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.chatapp.R
import com.example.chatapp.myDateBase.DataSpinner

class SpinnerAdapter(private val items: List<DataSpinner>) : BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.content_spinner, parent, false)

        val imageView: ImageView = view.findViewById(R.id.image1)
        val textView: TextView = view.findViewById(R.id.movies)

        val currentItem = items[position]
        imageView.setImageResource(currentItem.imageView?:0)
        textView.text = currentItem.text
        return view
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}