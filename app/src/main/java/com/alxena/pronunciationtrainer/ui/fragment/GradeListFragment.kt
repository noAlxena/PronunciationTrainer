package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentGradeListBinding
import com.alxena.pronunciationtrainer.ui.adapter.GradeAdapter
import com.alxena.pronunciationtrainer.ui.viewmodel.GradeListViewModel

//teacher list of students grades on selected lesson
class GradeListFragment: Fragment() {
    private var _binding: FragmentGradeListBinding? = null
    private val viewModel: GradeListViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGradeListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val groupToken = arguments?.getString("groupToken")?:""
        val lessonToken = arguments?.getString("lessonToken")?:""
        val studentToken = arguments?.getString("studentToken")?:""
        viewModel.getGrades(groupToken, studentToken, lessonToken)
        viewModel.grades.observe(viewLifecycleOwner){
            binding.rec.adapter = GradeAdapter(it)
        }
        binding.rec.layoutManager = LinearLayoutManager(context)
        binding.arrowback.setOnClickListener(){
            findNavController().popBackStack()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}