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
import com.alxena.pronunciationtrainer.databinding.FragmentTeacherGroupInvitedBinding
import com.alxena.pronunciationtrainer.ui.adapter.InvitesAdapter
import com.alxena.pronunciationtrainer.ui.viewmodel.TeacherGroupInvitedViewModel

//teacher invited list
class TeacherGroupInvitedFragment: Fragment() {
    private val viewModel: TeacherGroupInvitedViewModel by viewModels()
    private var _binding: FragmentTeacherGroupInvitedBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeacherGroupInvitedBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val groupToken = arguments?.getString("groupToken")?:""
        viewModel.getInvites(groupToken)
        viewModel.invites.observe(viewLifecycleOwner){
            binding.rec.adapter =  InvitesAdapter(it,
                {studentToken: String ->
                    viewModel.accept(groupToken, studentToken)
                },
                {studentToken: String ->
                    viewModel.deny(groupToken, studentToken)
                })
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