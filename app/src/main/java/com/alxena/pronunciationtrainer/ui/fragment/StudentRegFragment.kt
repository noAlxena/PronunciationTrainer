package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentStudentRegBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.StudentRegViewModel

class StudentRegFragment: Fragment() {
    private var _binding: FragmentStudentRegBinding? = null
    private val viewModel: StudentRegViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentRegBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arrowback.setOnClickListener(){
            findNavController().popBackStack()
        }
        binding.button.setOnClickListener(){
            val login = binding.editLogin.text.toString()
            val teacherToken = binding.editToken.text.toString()
            viewModel.getToken(requireContext(), teacherToken, login)
        }
        viewModel.registered.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_studentRegFragment_to_startFragment)
            //findNavController().popBackStack(R.id.startFragment, false)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}