package com.portfolio2.reminder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.portfolio2.reminder.dataModel.ArchivedTasks
import com.portfolio2.reminder.dataModel.DoneTasks
import com.portfolio2.reminder.dataModel.Task


@Database(entities =[Task::class,DoneTasks::class,ArchivedTasks::class], version = 1)
abstract class TasksDatabase: RoomDatabase() {
    abstract val myDao:TasksDao
    companion object{
        @Volatile
        private var INSTANCE:TasksDatabase?=null

        fun getInstance(context:Context): TasksDatabase {

                synchronized(this)
                {
                    return INSTANCE?: Room.databaseBuilder(context,TasksDatabase::class.java,"tasks-database").build().also {
                        INSTANCE=it
                    }
                }

        }


    }
}