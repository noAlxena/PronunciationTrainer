package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentAuthorsBinding

class AuthorsFragment: Fragment() {
    private var _binding: FragmentAuthorsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthorsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowback.setOnClickListener(){
            findNavController().navigate(R.id.action_authorsFragment_to_startFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}