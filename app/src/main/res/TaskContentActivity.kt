package com.portfolio2.reminder


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.portfolio2.reminder.dataModel.Task
import com.portfolio2.reminder.database.TasksDatabase
import com.portfolio2.reminder.databinding.ActivityTaskContentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.hours

class TaskContentActivity : AppCompatActivity() {
    lateinit var binding: ActivityTaskContentBinding
    var timeEdited=false
    var dateEdited=false
    val task:Task by lazy {
         Task(0,"", "", 0, 0, 0, 0)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTaskContentBinding.inflate(layoutInflater)

        if (intent?.extras != null) {

            intent?.getBooleanExtra("ring",false)!!
            val title=intent?.getStringExtra("title").toString()
            val desc=intent?.getStringExtra("description").toString()
            binding.taskTitle.setText(title)
            binding.description.setText(desc)

        }
        
        setContentView(binding.root)
        binding.datePick.setOnClickListener{
            openDatePicker()

        }

        binding.timePick.setOnClickListener{
            openTimePicker()
        }
    }

    private fun openDatePicker(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dialog =DatePickerDialog(this,DatePickerDialog.OnDateSetListener { _, _, monthOfYear, dayOfMonth ->

            task.day=dayOfMonth
            task.month=monthOfYear
            dateEdited=true
            Toast.makeText(applicationContext, "$monthOfYear-$dayOfMonth", Toast.LENGTH_SHORT).show()


        }, year, month, day)
        dialog.show()
    }

    private fun openTimePicker(){


        val hours =SimpleDateFormat("HH").format(Date())
        val minu =SimpleDateFormat("mm").format(Date())

        val dialog =
            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minutes ->

                task.hour=hour
                task.minute=minutes
                Toast.makeText(applicationContext, "$hour:$minutes", Toast.LENGTH_SHORT).show()
                timeEdited=true

        }, hours.toInt(), minu.toInt(), false)
        dialog.show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.save_action,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.save->{
                if (binding.taskTitle.text.isEmpty())
                {
                    Toast.makeText(applicationContext,
                        "The task not Titled".uppercase(Locale.getDefault()), Toast.LENGTH_SHORT).show()
                }
                else if (!timeEdited||!dateEdited)
                    Toast.makeText(applicationContext,
                        "specify time and date please".uppercase(Locale.getDefault()), Toast.LENGTH_SHORT).show()

                else{
                    task.title=binding.taskTitle.text.toString()
                    task.description=binding.description.text.toString()

                    GlobalScope.launch(Dispatchers.IO) {
                        TasksDatabase.getInstance(applicationContext).myDao.addNewTask(task)
                    }

                    val intent=Intent(this,MainActivity::class.java)
//                    intent.putExtra("title",task.title)
//                    intent.putExtra("description",task.description)
//                    intent.putExtra("day",task.day)
//                    intent.putExtra("month",task.month)
//                    intent.putExtra("hour",task.hour)
//                    intent.putExtra("minute",task.minute)
//                    intent.putExtra("ring",task.ring)
                    startActivity(intent)
                    finish()
                }

            }
        }


        return false
    }
}