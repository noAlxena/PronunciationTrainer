package com.alxena.pronunciationtrainer.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.databinding.FragmentUserdataBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.UserDataViewModel

class UserDataFragment : Fragment() {
    private var _binding: FragmentUserdataBinding? = null
    private val viewModel: UserDataViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserdataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.info.observe(viewLifecycleOwner){
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            with (sharedPref!!.edit()) {
                putString("first_name", it.first_name)
                putString("second_name", it.second_name)
                apply()
            }
            findNavController().popBackStack()
        }
        binding.button.setOnClickListener{
            val first_name = binding.editFirstName.text.toString()
            val second_name = binding.editSecondName.text.toString()
            viewModel.updateInfo(first_name, second_name)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}