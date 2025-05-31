package com.alxena.pronunciationtrainer.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.databinding.ViewTeacherInvitedBinding

class InvitesAdapter(private val invites: List<StudentDAO>,
                     private val acceptListener:(String)->Unit,
                     private val denyListener:(String)->Unit):
    RecyclerView.Adapter<InvitesAdapter.InviteViewHolder>()
{
    class InviteViewHolder(var binding: ViewTeacherInvitedBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewTeacherInvitedBinding.inflate(inflater, parent, false)
        return InviteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return invites.size
    }

    override fun onBindViewHolder(holder: InviteViewHolder, position: Int) {
        with(holder){
            binding.textFirstName.text = invites[position].first_name
            binding.textSecondName.text = invites[position].second_name
            binding.buttonDeny.setOnClickListener{
                denyListener(invites[position].token)
            }
            binding.buttonAccept.setOnClickListener{
                acceptListener(invites[position].token)
            }
        }
    }
}