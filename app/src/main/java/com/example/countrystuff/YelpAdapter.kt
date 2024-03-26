package com.example.countrystuff

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class YelpAdapter(val yelps: List<YelpBusiness>) :RecyclerView.Adapter<YelpAdapter.ViewHolder>(){
    class ViewHolder(rootLayout: View) : RecyclerView.ViewHolder(rootLayout){
        val restaurantText: TextView = rootLayout.findViewById(R.id.restaurant_name)
        val categoryText: TextView = rootLayout.findViewById(R.id.Category)
        val rating: TextView = rootLayout.findViewById(R.id.rating)
        val icon: ImageView = rootLayout.findViewById(R.id.icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("VH", "inside onCreateViewHolder")
        val layoutInflater: LayoutInflater= LayoutInflater.from(parent.context)
        val rootLayout: View=layoutInflater.inflate(R.layout.yelpcardreview, parent, false)
        val viewHolder=ViewHolder(rootLayout)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return yelps.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentYelpBusiness=yelps[position]
        holder.restaurantText.text= currentYelpBusiness.restaurantName
        holder.categoryText.text= currentYelpBusiness.category
        holder.rating.text=currentYelpBusiness.rating.toString()
        Log.d("VH", "inside onBindViewHolder on position $position")
        if (currentYelpBusiness.icon.isNotEmpty()){
            Picasso.get().setIndicatorsEnabled(true)
            Picasso.get()
                .load(currentYelpBusiness.icon)
                .into(holder.icon)
        }

    }

}