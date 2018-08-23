package com.example.prabh.teamphi.mvvm.activity.Task

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.retrofit.model.Tasks
import kotlinx.android.synthetic.main.task_recycler.view.*


class TaskAdapter(val tasks: ArrayList<Tasks>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_recycler, parent, false))

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(tasks[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(task: Tasks) {
            itemView.task_name.text = task.taskName
            itemView.cost.text = task.totalCost
            itemView.task_quantity.text = task.items?.size.toString()
            itemView.date.text=task.creationDate
        }
    }
}