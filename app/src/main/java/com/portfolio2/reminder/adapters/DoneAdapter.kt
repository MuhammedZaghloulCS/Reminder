package com.portfolio2.reminder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.portfolio2.reminder.R
import com.portfolio2.reminder.dataModel.DoneTasks
import com.portfolio2.reminder.dataModel.Task

 class DoneAdapter(var doneList:ArrayList<DoneTasks>, val context: Context,  val onItemClick:(pos:Int)->Unit): RecyclerView.Adapter<MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return MyViewHolder(view)
    }
    override fun getItemCount(): Int {
        return doneList.size

    }

    fun delete(i :Int)
    {
        doneList.removeAt(i)
        notifyDataSetChanged()
    }
    fun add(i :Int,task:DoneTasks)
    {
        doneList.add(i,task)
        notifyDataSetChanged()
    }
    fun add(task:DoneTasks)
    {
        doneList.add(task)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.done.visibility=View.GONE
        holder.ring.visibility=View.GONE
        val current= doneList[position]
        holder.title.text=current.title
        holder.date.text = current.day.toString()+"-"+current.month.toString()
        holder.time.text = current.hour.toString()+":"+current.minute.toString()
        holder.itemView.setOnClickListener{
            onItemClick(position)
        }    }
}