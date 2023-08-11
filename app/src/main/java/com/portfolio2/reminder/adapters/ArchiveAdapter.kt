//package com.portfolio2.reminder.adapters
//
//import android.content.Context
//import android.view.View
//import com.portfolio2.reminder.R
//import com.portfolio2.reminder.dataModel.ArchivedTasks
//import com.portfolio2.reminder.dataModel.DoneTasks
//import com.portfolio2.reminder.dataModel.Task
//
//class ArchiveAdapter(val archiveList:ArrayList<DoneTasks>,
//                     override val context: Context, override val onItemClick: (pos: Int) -> Unit):DoneAdapter(archiveList,context,onItemClick) {
//
//    override fun getItemCount(): Int {
//        return archiveList.size
//    }
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val current = list[position]
//        holder.done.visibility= View.INVISIBLE
//        holder.archive.setImageResource(R.drawable.baseline_unarchive_24)
//        holder.title.text = current.title
//        holder.date.text = current.day.toString()+"-"+current.month.toString()
//        holder.time.text = current.hour.toString()+":"+current.minute.toString()
//        holder.itemView.setOnClickListener {
//            onItemClick(position)
//        }
//    }
//}