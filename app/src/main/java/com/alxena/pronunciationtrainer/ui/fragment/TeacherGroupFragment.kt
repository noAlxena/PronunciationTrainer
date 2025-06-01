package com.alxena.pronunciationtrainer.ui.fragment

import android.app.Activity
import android.content.Intent
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
import java.io.FileOutputStream
import java.net.URLDecoder

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
            },
            { groupToken: String ->
                viewModel.export(groupToken)
            })
        }
        binding.rec.layoutManager = LinearLayoutManager(context)
        binding.addButton.setOnClickListener{
            findNavController().navigate(R.id.action_teacherGroupFragment_to_teacherGroupCreateFragment)
        }
        binding.arrowback.setOnClickListener(){
            findNavController().popBackStack()
        }
        viewModel.table.observe(viewLifecycleOwner){
            val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
                val filenameUTF = viewModel.table.value?.headers()?.get("Content-Disposition")
                val filename = URLDecoder.decode(filenameUTF?.split("filename*=UTF-8''")?.get(1),"UTF-8")
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                putExtra(Intent.EXTRA_TITLE, filename?:"группа.xlsx")
            }
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                val contentResolver = context?.contentResolver
                contentResolver?.openFileDescriptor(uri, "w")?.use {
                    val inStream = viewModel.table.value?.body()?.byteStream()
                    val outStream = FileOutputStream(it.fileDescriptor)
                    try {
                        if(inStream != null)
                        {
                            val fileBuffer = ByteArray(4 * 1024)
                            while(true){
                                val readB = inStream.read(fileBuffer)
                                if (readB == -1)
                                    break
                                outStream.write(fileBuffer, 0, readB)
                            }
                            outStream.flush()
                        }
                    }
                    finally {
                        inStream?.close()
                        outStream.close()
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}