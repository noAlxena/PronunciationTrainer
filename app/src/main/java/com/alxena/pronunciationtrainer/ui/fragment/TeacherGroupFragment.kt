package com.alxena.pronunciationtrainer.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentTeacherGroupBinding
import com.alxena.pronunciationtrainer.ui.adapter.TeacherGroupAdapter
import com.alxena.pronunciationtrainer.ui.viewmodel.TeacherGroupViewModel

//teacher group list
class TeacherGroupFragment: Fragment() {
    private val viewModel: TeacherGroupViewModel by viewModels()
    private var _binding: FragmentTeacherGroupBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeacherGroupBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGroups()
        viewModel.groups.observe(viewLifecycleOwner){
            binding.rec.adapter = TeacherGroupAdapter(it,
            { groupToken: String ->
                findNavController().navigate(
                    R.id.action_teacherGroupFragment_to_studentListFragment,
                    androidx.core.os.bundleOf("groupToken" to groupToken)
                )
            },
            { groupToken: String ->
                findNavController().navigate(
                    R.id.action_teacherGroupFragment_to_teacherGroupInviteFragment,
                    androidx.core.os.bundleOf("groupToken" to groupToken)
                )
            },
            { groupToken: String ->
                findNavController().navigate(
                    R.id.action_teacherGroupFragment_to_teacherGroupInvitedFragment,
                    androidx.core.os.bundleOf("groupToken" to groupToken)
                )
            })
        }
        binding.rec.layoutManager = LinearLayoutManager(context)
        binding.addButton.setOnClickListener{
            findNavController().navigate(R.id.action_teacherGroupFragment_to_teacherGroupCreateFragment)
        }
        /*
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

         */
        binding.arrowback.setOnClickListener(){
            findNavController().popBackStack()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}