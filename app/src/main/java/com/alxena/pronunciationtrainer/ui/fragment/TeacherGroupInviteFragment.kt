package com.alxena.pronunciationtrainer.ui.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.databinding.FragmentTeacherGroupInviteBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.TeacherGroupInviteViewModel

//teacher invite code
class TeacherGroupInviteFragment: Fragment() {
    private val viewModel: TeacherGroupInviteViewModel by viewModels()
    private var _binding: FragmentTeacherGroupInviteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeacherGroupInviteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val groupToken = arguments?.getString("groupToken")?:""
        viewModel.getInviteCode(groupToken)
        viewModel.code.observe(viewLifecycleOwner){
            binding.invitecode.text = it.token
        }
        binding.invitecode.setOnClickListener{
            val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("Код приглашения", binding.invitecode.text)
            clipboard.setPrimaryClip(clip)
        }
        binding.arrowback.setOnClickListener(){
            findNavController().popBackStack()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}