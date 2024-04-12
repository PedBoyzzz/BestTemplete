package com.aa.project2.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aa.project2.R
import com.aa.project2.models.ImageItem
import java.io.File

class ListAdapter2(val list:List<ImageItem>):RecyclerView.Adapter<ListAdapter2.MyViewHolder>(){
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title: TextView
        var iv:ImageView
        init {
            title = v.findViewById(R.id.title)
            iv = v.findViewById(R.id.imageView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView1 = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item2, parent, false)
        return  MyViewHolder(itemView1)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val it = list.get(position)
        holder.title.text = it.name
        val file = File(it.name)
        val myBitmap = BitmapFactory.decodeFile(it.file.absolutePath)
        System.out.println("-----myBitmap name = "+myBitmap.width)
        holder.iv.setImageBitmap(myBitmap)
        holder.itemView.setOnClickListener {
            System.out.println("-----click position "+position)
        }
    }
    interface Listener{
        fun onSelect(pos:Int)
    }
}