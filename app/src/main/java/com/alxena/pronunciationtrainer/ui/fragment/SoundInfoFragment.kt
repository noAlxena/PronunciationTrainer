package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentSoundInfoBinding
import com.alxena.pronunciationtrainer.ui.viewmodel.SoundInfoViewModel

class SoundInfoFragment: Fragment() {
    private var _binding: FragmentSoundInfoBinding? = null
    val viewModel: SoundInfoViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSoundInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
    class webObj(val soundId:Int){
        @JavascriptInterface
        fun getId():Int
        {
            return soundId
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lessonToken = arguments?.getString("lessonToken")?:""
        viewModel.getLessonInfo(lessonToken)
        viewModel.lessonInfo.observe(viewLifecycleOwner){
            binding.textTitle.text = it.title
            binding.textDesctiption.text = it.description
            if(it.require_3dmodel)
            {
                binding.web.settings.apply {
                    javaScriptEnabled = true
                    loadWithOverviewMode = true
                }
                binding.web.addJavascriptInterface(webObj(0),"webObj")
                binding.web.loadUrl("file:///android_asset/asset.html")
            }
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