package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentSettingsBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.SettingViewModel

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
            viewModel.reset(requireContext())
        }

        binding.backhome.setOnClickListener(){
            findNavController().navigate(R.id.action_settingFragment_to_startFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}