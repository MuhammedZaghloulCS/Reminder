package com.portfolio2.reminder.adapters

import android.content.Context
import android.provider.Settings.Global
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.portfolio2.reminder.R
import com.portfolio2.reminder.dataModel.DoneTasks
import com.portfolio2.reminder.dataModel.Task
import com.portfolio2.reminder.database.TasksDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


 class MyViewHolder(item: View):RecyclerView.ViewHolder(item){
    var done=item.findViewById<ImageView>(R.id.done1)
    var archive=item.findViewById<ImageView>(R.id.archived1)
    var title=item.findViewById<TextView>(R.id.title1)
    var date=item.findViewById<TextView>(R.id.date1)
    var ring=item.findViewById<ImageView>(R.id.ring)
    var time=item.findViewById<TextView>(R.id.time)



}

 class MyAdapter(var list:ArrayList<Task>, open val context: Context, open val onItemClick:(pos:Int)->Unit): RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size

    }
    fun delete(i :Int)
    {
        list.removeAt(i)
        notifyDataSetChanged()
    }
    fun add(i :Int,task:Task)
    {
        list.add(i,task)
        notifyDataSetChanged()
    }
    fun add(task:Task)
    {
        list.add(task)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var current= list[position]
        holder.title.text=current.title
        holder.date.text = current.day.toString()+"-"+current.month.toString()
        holder.time.text = current.hour.toString()+":"+current.minute.toString()
        holder.itemView.setOnClickListener{
            onItemClick(position)
        }
        holder.ring.setOnClickListener{
            if(current.ring)
            {
                holder.ring.setImageResource(R.drawable.baseline_alarm_off_24)
                list[position].ring=false
            }
            else
            {
                holder.ring.setImageResource(R.drawable.baseline_alarm_on_24)
                list[position].ring=true
            }

        }

         holder.done.setOnClickListener{
            val done:DoneTasks=DoneTasks(current.id,current.title,current.description,
                current.day,current.month,
                current.hour,current.minute)
            val db= TasksDatabase.getInstance(context).myDao
            GlobalScope.launch(Dispatchers.IO) {
                db.doneTask(done)
                withContext(Dispatchers.Main)
                {
                    Toast.makeText(context, "done", Toast.LENGTH_SHORT).show()

                }
                db.deleteTaskById(current.id)

            }
             list.remove(current)
             notifyDataSetChanged()
        }

    }
}