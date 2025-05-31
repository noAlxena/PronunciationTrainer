package com.alxena.pronunciationtrainer.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.databinding.ViewStudentBinding

class StudentAdapter(private val students: List<StudentDAO>, private val listener:(String)->Unit):
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var binding: ViewStudentBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewStudentBinding.inflate(inflater, parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        with(holder.binding){
            textName.text = students[position].first_name
            textName.setOnClickListener{
                listener(students[position].token)
            }
        }
    }
}