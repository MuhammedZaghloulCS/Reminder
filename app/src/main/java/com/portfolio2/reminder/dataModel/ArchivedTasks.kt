package com.portfolio2.reminder.dataModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity()
data class ArchivedTasks(
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
