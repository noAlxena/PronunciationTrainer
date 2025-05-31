package com.alxena.pronunciationtrainer.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentStudentGroupInvite2Binding
import com.alxena.pronunciationtrainer.ui.viewmodel.StudentGroupInvite2ViewModel

//student accept invite step 2
class StudentGroupInvite2Fragment: Fragment() {
    private val viewModel: StudentGroupInvite2ViewModel by viewModels()
    private var _binding: FragmentStudentGroupInvite2Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentGroupInvite2Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val inviteToken = arguments?.getString("inviteToken")?:""

        binding.editTextFirstName.setText(sharedPref?.getString("first_name","")?:"")
        binding.editTextSecondName.setText(sharedPref?.getString("second_name","")?:"")
        binding.button.setOnClickListener{
            val first_name = binding.editTextFirstName.text.toString()
            val second_name = binding.editTextSecondName.text.toString()
            viewModel.acceptInvite(inviteToken, first_name, second_name)
            findNavController().popBackStack(R.id.studentGroupFragment, false)
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