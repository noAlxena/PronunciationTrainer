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
    val db by lazy{
        Room.databaseBuilder(
            requireContext(),
            SoundDatabase::class.java,"sounds.db"
        ).build()
    }

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
        GlobalScope.launch {
            db.clearAllTables()
            for(a in TestData.Sounds){
                db.SoundProfileDAO().insert(a)
            }
            val snds = db.SoundProfileDAO().getCategories()
            Log.e("123",snds.joinToString(","))
            val snds2 = db.SoundProfileDAO().getSoundsByCategory(snds[0])
            Log.e("123",snds2.joinToString(","))
        }

        binding.button.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_soundTrainFragment)
        }
        binding.button3.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_soundInfoFragment)
        }
        binding.button4.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_listFragment)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}