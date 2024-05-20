package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alxena.pronunciationtrainer.data.model.SoundProfileEntity
import com.alxena.pronunciationtrainer.data.util.SoundCategory
import com.alxena.pronunciationtrainer.databinding.FragmentListBinding
import com.alxena.pronunciationtrainer.ui.adapter.CategoryAdapter

class ListFragment:Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CategoryAdapter(listOf(SoundCategory("гласные",listOf())))
        binding.rec.adapter = adapter
        binding.rec.layoutManager = LinearLayoutManager(context)
    //LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}