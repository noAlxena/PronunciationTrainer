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
import com.alxena.pronunciationtrainer.databinding.FragmentRegistrationBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.registrationViewModel

//register in app
class RegistrationFragment: Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val viewModel: registrationViewModel by viewModels()
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
        binding.arrowback.setOnClickListener(){
            findNavController().popBackStack()
        }
        binding.button.setOnClickListener(){
            val login = binding.editLogin.text.toString()
            val password = binding.editPassword.text.toString()
            val isTeacher = binding.isTeacher.isChecked
            viewModel.register(login, password, if(isTeacher) "teacher" else "student")
            //viewModel.getToken(requireContext(), teacherToken, login)
        }
        viewModel.status.observe(viewLifecycleOwner){
            if(it == null)
            {
                Toast.makeText(requireContext(),"failed to login", Toast.LENGTH_LONG).show()
            }
            else
            {
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                with (sharedPref!!.edit()) {
                    putString("login", viewModel.info.value?.login)
                    putString("role", viewModel.info.value?.role)
                    putString("refresh_token", it.refresh_token)
                    apply()
                }
                findNavController().popBackStack(R.id.startFragment, false)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}