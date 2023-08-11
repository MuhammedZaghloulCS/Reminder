package com.portfolio2.reminder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.portfolio2.reminder.dataModel.ArchivedTasks
import com.portfolio2.reminder.dataModel.DoneTasks
import com.portfolio2.reminder.dataModel.Task


@Dao
interface TasksDao {

    //New Tasks
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewTask(task: Task)
    @Query("select * from Task")
    suspend fun getAllTasks():List<Task>
    @Query("DELETE FROM Task WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Int)

    //Done Tasks
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun doneTask(doneTask: DoneTasks)

    @Query("Select * from DoneTasks")
    suspend fun getAllDoneTasks():List<DoneTasks>

//    @Query("DELETE FROM DoneTasks WHERE id = :taskId")
//      suspend fun deleteDoneTaskById(taskId: Int)


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun archiveTask(task: ArchivedTasks)
//

//
//    @Query("select * from ArchivedTasks")
//    suspend fun getArchivedAllTasks():List<Task>


//    @Query("DELETE FROM ArchivedTasks WHERE id = :taskId")
//    suspend fun deleteArchivedTaskById(taskId: Int)

}