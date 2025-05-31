package com.alxena.pronunciationtrainer.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentStudentGroupInviteBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.StudentGroupInviteViewModel

//student accept invite step 1
class StudentGroupInviteFragment: Fragment() {
    private val viewModel: StudentGroupInviteViewModel by viewModels()
    private var _binding: FragmentStudentGroupInviteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentGroupInviteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener{
            viewModel.checkInvite(binding.editToken.text.toString())
        }
        viewModel.response.observe(viewLifecycleOwner){
            if(it == null)
            {
                Toast.makeText(context, "invite doesn't exist", Toast.LENGTH_LONG).show()
            }
            else
            {
                findNavController().navigate(R.id.action_studentGroupInviteFragment_to_studentGroupInvite2Fragment,
                    androidx.core.os.bundleOf(
                        "inviteToken" to it.msg))
            }
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