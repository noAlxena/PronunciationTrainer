package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentLessonListBinding
import com.alxena.pronunciationtrainer.ui.adapter.CategoryAdapter
import com.alxena.pronunciationtrainer.ui.viewmodel.LessonListViewModel

class LessonListFragment: Fragment() {
    private var _binding: FragmentLessonListBinding? = null
    private val viewModel: LessonListViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLessonListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val studentToken = arguments?.getString("token")?:""
        viewModel.getLessons(studentToken)
        viewModel.lessons.observe(viewLifecycleOwner){
            binding.rec.adapter = CategoryAdapter(it){ lessonToken: String ->
                findNavController().navigate(
                    R.id.action_lessonListFragment_to_gradeListFragment,
                    androidx.core.os.bundleOf(
                        "studentToken" to studentToken,
                        "lessonToken" to lessonToken)
                )

            }
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