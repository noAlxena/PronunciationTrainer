package com.alxena.pronunciationtrainer.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.data.util.SoundCategory
import com.alxena.pronunciationtrainer.databinding.ViewCategoryBinding
class CategoryAdapter(private val categories:List<SoundCategory>, private val listener:(Int)->Unit):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>()
{
    class CategoryViewHolder(var binding:ViewCategoryBinding):RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder.binding){
            catName.text = rec.context.resources.getStringArray(R.array.categories)[categories[position].nameId]
            val adapter = SoundAdapter(categories[position].sounds,listener)
            rec.adapter = adapter
            rec.layoutManager = GridLayoutManager(rec.context,3)
        }
    }
}