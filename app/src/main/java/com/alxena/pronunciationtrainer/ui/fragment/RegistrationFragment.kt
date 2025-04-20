package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.data.util.APIInstance
import com.alxena.pronunciationtrainer.databinding.FragmentRegistrationBinding

class RegistrationFragment: Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonTeacher.setOnClickListener(){
            findNavController().navigate(R.id.action_registrationFragment_to_teacherRegFragment)
        }
        binding.buttonStudent.setOnClickListener(){
            findNavController().navigate(R.id.action_registrationFragment_to_studentRegFragment)
        }
        binding.buttonUrl.setOnClickListener(){
            APIInstance.ConnectionUrl = binding.editTextURL.text.toString()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}