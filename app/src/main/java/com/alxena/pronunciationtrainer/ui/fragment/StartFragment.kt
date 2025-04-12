package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.TestData
import com.alxena.pronunciationtrainer.databinding.FragmentStartBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.ListViewModel
import com.alxena.pronunciationtrainer.ui.viewmodel.StartViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StartFragment: Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val viewModel: StartViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSettings(requireContext())
        viewModel.settings.observe(viewLifecycleOwner){
            if(it==null)
            {
                findNavController().navigate(R.id.action_startFragment_to_registrationFragment)
            }
            else
            {
                binding.loginText.text = it.login
                if(it.teacherToken == null)
                {
                    binding.roleText.text = "учитель"
                }
                else
                {
                    binding.roleText.text = "ученик"
                }
            }
            Log.d("a","${it==null}")
        }
        binding.startButton.setOnClickListener{
            if(viewModel.settings.value?.teacherToken == null)
            {
                findNavController().navigate(R.id.action_startFragment_to_studentViewFragment)
            }
            else
            {
                findNavController().navigate(R.id.action_startFragment_to_listFragment)
            }
        }
        binding.infoButton.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_infoFragment)
        }
        binding.settingButton.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_settingFragment)
        }
        //binding.authorsButton.setOnClickListener{
            //findNavController().navigate(R.id.action_startFragment_to_authorsFragment)
        //}
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}