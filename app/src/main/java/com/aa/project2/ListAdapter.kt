package com.aa.project2

import android.content.Context
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aa.project2.models.Product
import com.bumptech.glide.Glide
import java.util.ArrayList
import java.util.Locale

class ListAdapter(val items:List<Product>,val context:Context,val listener:Listener):Filterable,RecyclerView.Adapter<ListAdapter.MyViewHolder>(){
    lateinit var productsFilter:List<Product>
    init {
        productsFilter = items
    }
    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var title: TextView
        var img:ImageView
        var button:Button
        init {
            title = v.findViewById(R.id.t)
            img = v.findViewById(R.id.img)
            button = v.findViewById(R.id.button2)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView1 = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return  MyViewHolder(itemView1)
    }

    override fun getItemCount(): Int {
        return productsFilter.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = productsFilter.get(position)
        holder.title.text = product.title

        Glide.with(context)
            .load(product.thumbnail)
            //.override(200,200)
            .into(holder.img)
        holder.button.visibility = View.GONE
        holder.button.setOnClickListener {

        }

        holder.itemView.setOnClickListener {
            System.out.println("-----click position "+position)
            listener.onSelect(position,product)
        }

    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    productsFilter = items
                } else {
                    val resultList = ArrayList<Product>()
                    for (row in items) {
                        if (row.title.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    productsFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = productsFilter
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                productsFilter = results?.values as ArrayList<Product>
                notifyDataSetChanged()
            }

        }
    }

    interface Listener{
        fun onSelect(pos:Int,product: Product)
    }
}