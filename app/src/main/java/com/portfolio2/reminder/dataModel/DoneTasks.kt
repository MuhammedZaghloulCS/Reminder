package com.portfolio2.reminder.dataModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DoneTasks(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo
    var title:String,
    @ColumnInfo
    var description:String,
    @ColumnInfo
    var day:Int,
    @ColumnInfo
    var month:Int,
    @ColumnInfo
    var hour:Int,
    @ColumnInfo
    var minute:Int
    )
{

        fun equalize(done:ArrayList<DoneTasks>):ArrayList<Task>
        {
            val tasks= arrayListOf<Task>()
            done.forEach{ _ ->
                val task: Task= Task(0,"", "", 0, 0, 0, 0)
                task.title=this.title
                task.description=this.description
                task.day=this.day
                task.month=this.month
                task.hour=this.hour
                task.minute=this.minute
                task.ring=false
                tasks.add(task)
            }

            return tasks
        }


}
