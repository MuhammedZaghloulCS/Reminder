package com.portfolio2.reminder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.portfolio2.reminder.adapters.DoneAdapter
import com.portfolio2.reminder.dataModel.Task
import com.portfolio2.reminder.databinding.FragmentArchiveBinding
import java.util.Date


class ArchiveFragment : Fragment() {

    lateinit var binding:FragmentArchiveBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentArchiveBinding.inflate(layoutInflater)
//        val archive=ArrayList<Task>()
//
//        archive.add(Task(0,"Archive"," Desc",15,7,10,0,true))
//
//
//
//        binding.rec.adapter= ArchiveAdapter(archive,requireContext())
//        {
//            Toast.makeText(binding.root.context, "$it", Toast.LENGTH_SHORT).show()
//        }
//        binding.rec.layoutManager= LinearLayoutManager(binding.root.context)


        return binding.root
    }

}