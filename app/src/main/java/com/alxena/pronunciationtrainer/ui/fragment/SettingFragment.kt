package com.alxena.pronunciationtrainer.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.databinding.FragmentSettingsBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.SettingViewModel

//setting of app
class SettingFragment: Fragment() {
    private val viewModel: SettingViewModel by viewModels()
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resetButton.setOnClickListener{
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            with (sharedPref!!.edit()) {
                putString("login", "none")
                putString("first_name", "")
                putString("second_name", "")
                putString("role", "")
                putString("access_token", "")
                putString("refresh_token", "")
                apply()
            }
            findNavController().popBackStack()
        }
        //binding.buttonUrl.setOnClickListener(){
        //    viewModel.setURL(binding.editTextURL.text.toString())
        //}
        binding.arrowback.setOnClickListener(){
            findNavController().popBackStack()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}