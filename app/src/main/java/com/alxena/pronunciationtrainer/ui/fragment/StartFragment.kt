package com.alxena.pronunciationtrainer.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentStartBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.StartViewModel

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
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val role = sharedPref?.getString("role","")?:""
        if(role.isBlank())
        {
            findNavController().navigate(R.id.action_startFragment_to_authorizationFragment)
        }
        else
        {
            viewModel.refresh(activity)
            val first_name = sharedPref?.getString("first_name","")?:""
            if(first_name.isBlank())
            {
                findNavController().navigate(R.id.action_startFragment_to_userDataFragment)
            }
            else
            {
                binding.loginText.text = first_name
                binding.roleText.text = if(role == "teacher") "учитель" else "ученик"
            }
        }

        binding.startButton.setOnClickListener{
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            val role = sharedPref?.getString("role","")?:""
            if(role == "teacher")
            {
                findNavController().navigate(R.id.action_startFragment_to_studentListFragment)
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