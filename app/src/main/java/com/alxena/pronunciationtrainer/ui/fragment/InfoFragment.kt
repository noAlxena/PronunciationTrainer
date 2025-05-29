package com.alxena.pronunciationtrainer.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentInfoBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.InfoViewModel

class InfoFragment: Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val viewModel: InfoViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arrowback.setOnClickListener{
            findNavController().popBackStack()
        }
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        binding.textLogin.text = sharedPref?.getString("login","")?:""
        binding.textFirstName.text = sharedPref?.getString("first_name","")?:""
        binding.textSecondName.text = sharedPref?.getString("second_name","")?:""
        binding.textRole.text = if(sharedPref?.getString("role","")?:"" == "teacher") "Учитель" else "Ученик"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}