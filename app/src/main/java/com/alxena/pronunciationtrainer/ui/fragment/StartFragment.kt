package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.TestData
import com.alxena.pronunciationtrainer.databinding.FragmentStartBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StartFragment: Fragment() {
    private var _binding: FragmentStartBinding? = null
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
        binding.startButton.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_listFragment)
        }
        binding.infoButton.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_infoFragment)
        }
        binding.optionsButton.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_settingFragment)
        }
        binding.authorsButton.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_authorsFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}