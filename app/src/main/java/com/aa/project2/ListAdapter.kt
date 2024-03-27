package com.aa.project2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aa.project2.models.Product
import com.bumptech.glide.Glide

class ListAdapter(val items:List<Product>,val context:Context,val listener:Listener):RecyclerView.Adapter<ListAdapter.MyViewHolder>(){
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title: TextView
        var img:ImageView
        init {
            title = v.findViewById(R.id.t)
            img = v.findViewById(R.id.img)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView1 = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return  MyViewHolder(itemView1)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = items.get(position)
        holder.title.text = product.title

        Glide.with(context)
            .load(product.thumbnail)
            //.override(200,200)
            .into(holder.img)

        holder.itemView.setOnClickListener {
            System.out.println("-----click position "+position)
            listener.onSelect(position,product)
        }
    }
    interface Listener{
        fun onSelect(pos:Int,product: Product)
    }
}