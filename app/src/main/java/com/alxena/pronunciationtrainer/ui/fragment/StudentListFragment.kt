package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alxena.pronunciationtrainer.databinding.FragmentStudentListBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.StudentListViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.ui.adapter.StudentAdapter

class StudentListFragment: Fragment() {
    private var _binding: FragmentStudentListBinding? = null
    private val viewModel : StudentListViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arrowback.setOnClickListener(){
            findNavController().navigate(R.id.action_studentListFragment_to_startFragment)
        }
        viewModel.getStudents(requireContext())
        viewModel.students.observe(viewLifecycleOwner){
            //
            binding.rec.adapter = StudentAdapter(it) { token: String ->
                findNavController().navigate(
                    R.id.action_studentListFragment_to_lessonListFragment,
                    androidx.core.os.bundleOf("token" to token)
                )
            }
        }
        binding.rec.layoutManager = LinearLayoutManager(context)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}