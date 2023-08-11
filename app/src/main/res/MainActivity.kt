package com.portfolio2.reminder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.portfolio2.reminder.databinding.ActivityMainBinding
import com.portfolio2.reminder.fragments.ArchiveFragment
import com.portfolio2.reminder.fragments.DoneFragment
import com.portfolio2.reminder.fragments.TasksFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navi.background=null
        //binding.navi.setupWithNavController(findNavController(R.id.fragmentContainerView))
        binding.navi.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.tasksFragmentMenu->
                {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,TasksFragment()).commit()
                }
                R.id.doneFragmentMenu->
                {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, DoneFragment()).commit()
                }
                R.id.archiveFragmentMenu->
                {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,ArchiveFragment()).commit()
                }

            }
            true
        }

        binding.newTask.setOnClickListener{

            startActivity(Intent(this,TaskContentActivity::class.java))
            finish()
        }


   }

    fun naviFragment(fragment: Fragment)
    {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,fragment).commit()

    }



}

