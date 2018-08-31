package com.example.prabh.teamphi.mvvm.activity.itemactivity

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.retrofit.model.Item
import kotlinx.android.synthetic.main.item_recycler.view.*

class ItemsAdapter(val items: ArrayList<Item>) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(bill_items: Item) {
            itemView.item_name.text = bill_items.itemName
            itemView.price.text = bill_items.price
            itemView.type.text = bill_items.itemType
            itemView.quantity_items.text = bill_items.quantity
            itemView.date.text = bill_items.purchaseDate?.subSequence(0, 10)
            if (!bill_items.isApproved!!) {
                itemView.rectangle_is_approved.setBackgroundColor(Color.parseColor("#FFB71717"))
            } else {
                itemView.rectangle_is_approved.setBackgroundColor(Color.parseColor("#7ad47a"))
            }
        }
    }

}