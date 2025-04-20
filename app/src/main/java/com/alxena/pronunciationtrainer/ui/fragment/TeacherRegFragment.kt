package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentTeacherRegBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.TeacherRegViewModel

class TeacherRegFragment: Fragment() {
    private var _binding: FragmentTeacherRegBinding? = null
    private val viewModel: TeacherRegViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeacherRegBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arrowback.setOnClickListener(){
            findNavController().popBackStack()
        }
        binding.button.setOnClickListener(){
            val login = binding.editLogin.text.toString()
            viewModel.getToken(requireContext(),login)
        }
        viewModel.registered.observe(viewLifecycleOwner){
           findNavController().navigate(R.id.action_teacherRegFragment_to_startFragment)
            //findNavController().popBackStack(R.id.startFragment, false) //TODO popbackstack loads Livedata from previous fragment which restart registration
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}