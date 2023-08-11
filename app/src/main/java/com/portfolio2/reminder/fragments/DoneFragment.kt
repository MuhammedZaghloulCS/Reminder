package com.portfolio2.reminder.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.portfolio2.reminder.dataModel.Task
import com.portfolio2.reminder.adapters.MyAdapter
import com.portfolio2.reminder.R
import com.portfolio2.reminder.adapters.DoneAdapter
import com.portfolio2.reminder.dataModel.DoneTasks
import com.portfolio2.reminder.database.TasksDatabase
import com.portfolio2.reminder.databinding.FragmentDoneBinding
import com.portfolio2.reminder.utils.SwipeGesture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Date

class DoneFragment : Fragment() {

    lateinit var binding: FragmentDoneBinding
    var tasks: List<DoneTasks> = emptyList()
    lateinit var adapter: DoneAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment

        binding= FragmentDoneBinding.inflate(layoutInflater)
        val job= GlobalScope.launch(Dispatchers.IO) {
            tasks= TasksDatabase.getInstance(requireContext()).myDao.getAllDoneTasks()
        }


        runBlocking {
            job.join()

            adapter= DoneAdapter(ArrayList(tasks), context = requireContext())            {
//            val intent= Intent(activity?.applicationContext,TaskContentActivity::class.java)
//            intent.putExtra("title",newTask.title)
//            intent.putExtra("description",newTask.description)
//            intent.putExtra("day",newTask.day)
//            intent.putExtra("month",newTask.month)
//            intent.putExtra("hour",newTask.hour)
//            intent.putExtra("minute",newTask.minute)
//            intent.putExtra("ring",newTask.ring)
//            activity?.startActivity(intent)
            }

            binding.rec.adapter= adapter

            binding.rec.layoutManager=LinearLayoutManager(binding.root.context)

            val swipeGesture=object : SwipeGesture(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when(direction)
                    {

                        ItemTouchHelper.LEFT->{

                            var end=false
                            val current=adapter.doneList[viewHolder.adapterPosition]
                            if (viewHolder.adapterPosition==adapter.itemCount-1)
                                end=true
                            adapter.delete(viewHolder.adapterPosition)
                            val builder= AlertDialog.Builder(requireContext())
                            builder.setMessage("Do you really want to delete the Task?")
                            builder.setCancelable(false)
                            builder.setPositiveButton("Yes") { dialog, id ->
                                GlobalScope.launch(Dispatchers.IO) {
                                    TasksDatabase.getInstance(requireContext()).myDao.deleteTaskById(
                                        viewHolder.adapterPosition
                                    )
                                }
                            }

                            builder.setNegativeButton("No"){dialog,id ->
                                dialog.dismiss()
                                if (end)
                                    adapter.add(current)
                                else
                                    adapter.add(viewHolder.adapterPosition,current)
                            }
                            val alert=builder.create()
                            alert.show()

                        }
                    }

                }
            }

            val touchHelper= ItemTouchHelper(swipeGesture)
            touchHelper.attachToRecyclerView(binding.rec)


        }


        return binding.root
    }

    companion object
}