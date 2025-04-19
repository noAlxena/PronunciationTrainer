package com.alxena.pronunciationtrainer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alxena.pronunciationtrainer.data.model.LessonGradeDAO
import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.databinding.ViewGradeBinding

class GradeAdapter(private val grades: List<LessonGradeDAO>):
    RecyclerView.Adapter<GradeAdapter.GradeViewHolder>()
{
    class GradeViewHolder(var binding: ViewGradeBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewGradeBinding.inflate(inflater, parent, false)
        return GradeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return grades.size
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        with(holder.binding){
            textGrade.text = "${grades[position].grade}"
        }
    }
}