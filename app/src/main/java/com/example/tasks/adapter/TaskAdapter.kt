package com.example.tasks.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.model.Task
import kotlinx.android.synthetic.main.task.view.*

class TaskAdapter(var activity: Activity, var data: ArrayList<Task>) :
    RecyclerView.Adapter<TaskAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date = itemView.date
        val title = itemView.taskTitle
        val details = itemView.Details
        val time = itemView.time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.MyViewHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.task, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TaskAdapter.MyViewHolder, position: Int) {
        holder.date.text = data[position].date
        holder.title.text = data[position].title
        holder.details.text = data[position].details
        holder.time.text = data[position].time
    }
}