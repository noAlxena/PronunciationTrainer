package com.alxena.pronunciationtrainer.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentStudentGroupBinding
import com.alxena.pronunciationtrainer.ui.adapter.StudentGroupAdapter
import com.alxena.pronunciationtrainer.ui.viewmodel.StudentGroupViewModel

//students groups
class StudentGroupFragment: Fragment() {
    private val viewModel: StudentGroupViewModel by viewModels()
    private var _binding: FragmentStudentGroupBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentGroupBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGroups()
        viewModel.groups.observe(viewLifecycleOwner){
            binding.rec.adapter = StudentGroupAdapter(it) { groupToken: String, studentToken: String ->
                findNavController().navigate(
                    R.id.action_studentGroupFragment_to_listFragment,
                    androidx.core.os.bundleOf(
                        "groupToken" to groupToken,
                        "studentToken" to studentToken)
                )
            }
        }
        binding.rec.layoutManager = LinearLayoutManager(context)
        binding.addButton.setOnClickListener{
            findNavController().navigate(R.id.action_studentGroupFragment_to_studentGroupInviteFragment)
        }
        binding.arrowback.setOnClickListener(){
            findNavController().popBackStack()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}