package com.example.prabh.teamphi.mvvm.activity.taskactivity

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.activity.itemactivity.ItemActivity
import com.example.prabh.teamphi.retrofit.model.Tasks
import com.example.prabh.teamphi.utility.Session
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
            itemView.task_quantity.text = task.itemCount.toString()
            itemView.date.text = task.creationDate?.subSequence(0, 10)
            itemView.created_by.text = task.createdByUserName
            itemView.task.setOnClickListener {
                val intent = Intent(itemView.context, ItemActivity::class.java)
                intent.putExtra("TASK_ID", task.taskId)
                itemView.context.startActivity(intent)
            }
        }
    }
}