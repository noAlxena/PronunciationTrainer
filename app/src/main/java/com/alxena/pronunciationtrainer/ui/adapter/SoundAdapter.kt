package com.alxena.pronunciationtrainer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alxena.pronunciationtrainer.data.model.SoundProfileEntity
import com.alxena.pronunciationtrainer.data.util.SoundCategory
import com.alxena.pronunciationtrainer.databinding.ViewSoundBinding
class SoundAdapter (private val sounds: List<SoundProfileEntity>):
    RecyclerView.Adapter<SoundAdapter.SoundViewHolder>()
{
    class SoundViewHolder(var binding: ViewSoundBinding): RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewSoundBinding.inflate(inflater, parent, false)
        return SoundViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sounds.size
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        with(holder.binding){
            soundName.text = sounds[position].soundId.toString()
        }
    }
}