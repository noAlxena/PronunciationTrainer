package com.alxena.pronunciationtrainer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.data.model.TopGradeDAO
import com.alxena.pronunciationtrainer.databinding.ViewSoundBinding

class SoundAdapter (private val lessons: List<TopGradeDAO>, private val listener:(String)->Unit):
    RecyclerView.Adapter<SoundAdapter.SoundViewHolder>()
{
    class SoundViewHolder(var binding: ViewSoundBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewSoundBinding.inflate(inflater, parent, false)
        return SoundViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        with(holder.binding){
            soundButton.text = lessons[position].title
            if(lessons[position].grade == 0)
                soundButton.setBackgroundColor(soundButton.context.getColor(R.color.button_main))
            else if(lessons[position].grade < 4)
                soundButton.setBackgroundColor(soundButton.context.getColor(R.color.button_bad))
            else if(lessons[position].grade < 8)
                soundButton.setBackgroundColor(soundButton.context.getColor(R.color.button_medium))
            else
                soundButton.setBackgroundColor(soundButton.context.getColor(R.color.button_complete))
            soundButton.setOnClickListener{
                listener(lessons[position].token)
            }
        }
    }
}