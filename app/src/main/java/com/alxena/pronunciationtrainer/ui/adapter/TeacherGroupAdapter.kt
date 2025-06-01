package com.alxena.pronunciationtrainer.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.data.model.GroupDAO
import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.databinding.ViewTeacherGroupBinding

class TeacherGroupAdapter(private val groups: List<GroupDAO>,
                          private val viewListener:(String)->Unit,
                          private val inviteListener:(String)->Unit,
                          private val invitedListener:(String)->Unit,
                          private val exportListener:(String)->Unit):
    RecyclerView.Adapter<TeacherGroupAdapter.TeacherGroupViewHolder>()
{
    class TeacherGroupViewHolder(var binding: ViewTeacherGroupBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherGroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewTeacherGroupBinding.inflate(inflater, parent, false)
        return TeacherGroupViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return groups.size
    }

    override fun onBindViewHolder(holder: TeacherGroupViewHolder, position: Int) {
        with(holder){
            binding.textName.text = groups[position].name
            binding.buttonInvite.setOnClickListener{
                inviteListener(groups[position].token)
            }
            binding.buttonInvited.setOnClickListener{
                invitedListener(groups[position].token)
            }
            binding.textName.setOnClickListener{
                viewListener(groups[position].token)
            }
            binding.buttonExport.setOnClickListener{
                exportListener(groups[position].token, groups[position].name)
            }
        }
    }
}