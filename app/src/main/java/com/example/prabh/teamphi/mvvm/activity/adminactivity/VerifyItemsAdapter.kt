package com.example.prabh.teamphi.mvvm.activity.adminactivity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.retrofit.model.VerifyItemsList
import com.example.prabh.teamphi.utility.Session
import kotlinx.android.synthetic.main.item_verify_recycler.view.*

class VerifyItemsAdapter(val itemsList: ArrayList<VerifyItemsList>, val adminLoginActivity: AdminLoginActivity) : RecyclerView.Adapter<VerifyItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_verify_recycler, parent, false))

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(itemsList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(items: VerifyItemsList) {
            val session=Session(adminLoginActivity)
            itemView.item_name_verify.text = items.itemsDetails?.itemName
            itemView.price_verify.text = items.itemsDetails?.price.toString()
            itemView.type_verify.text = items.itemsDetails?.itemType
            itemView.quantity_items_verify.text = items.itemsDetails?.quantity.toString()
            itemView.date_verify.text = items.itemsDetails?.purchaseDate?.subSequence(0, 10)
            itemView.requested_by_verify.text = items.assignedUserName
            itemView.accept.setOnClickListener {
                adminLoginActivity.adminLoginActivityViewModel.approveItem(session.getStringValue(Session.TOKEN),session.getStringValue(Session.USERNAME),items.taskId.toString(), items.itemsDetails?.id!!)
            }
        }
    }
}