package com.alxena.pronunciationtrainer.ui.fragment

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
        viewModel.getSettings(requireContext())
        viewModel.settings.observe(viewLifecycleOwner){
            binding.textLogin.text = it.login
            binding.textToken.text = it.token
            if(it.teacherToken == null)
            {
                binding.textTeacherTokenLabel.visibility = View.GONE
                binding.textTeacherToken.visibility = View.GONE
            }
            else
            {
                binding.textTeacherToken.text = it.teacherToken
            }

        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}