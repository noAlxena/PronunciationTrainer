package com.alxena.pronunciationtrainer.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.data.model.GroupLoginDAO
import com.alxena.pronunciationtrainer.databinding.ViewStudentGroupBinding

class StudentGroupAdapter(private val groups: List<GroupLoginDAO>,
                          private val listener:(String, String)->Unit):
    RecyclerView.Adapter<StudentGroupAdapter.StudentGroupViewHolder>()
{
    class StudentGroupViewHolder(var binding: ViewStudentGroupBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentGroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewStudentGroupBinding.inflate(inflater, parent, false)
        return StudentGroupViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return groups.size
    }

    override fun onBindViewHolder(holder: StudentGroupViewHolder, position: Int) {
        with(holder){
            binding.textName.text = groups[position].name
            if(groups[position].declined && groups[position].accepted)
            {

            }
            if(groups[position].declined)
            {
                binding.textName.setBackgroundColor(binding.textName.context.getColor(R.color.button_bad))
            }
            else
            {
                if(groups[position].accepted)
                {
                    binding.textName.setBackgroundColor(binding.textName.context.getColor(R.color.button_complete))
                    binding.textName.setOnClickListener{
                        listener(groups[position].group_token, groups[position].login_token)
                    }
                }
                else
                {
                    binding.textName.setBackgroundColor(binding.textName.context.getColor(R.color.button_medium))
                }
            }
        }
    }
}