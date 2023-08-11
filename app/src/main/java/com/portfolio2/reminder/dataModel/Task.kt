package com.portfolio2.reminder.dataModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity
data class Task(
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
    var minute:Int,
    @ColumnInfo
    var ring:Boolean=false
)
{
    fun equalize(done:DoneTasks):DoneTasks
    {
        done.title=this.title
        done.description=this.description
        done.day=this.day
        done.month=this.month
        done.hour=this.hour
        done.minute=this.minute
        return done
    }
}
