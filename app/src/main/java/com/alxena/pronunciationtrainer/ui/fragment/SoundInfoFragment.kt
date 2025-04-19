package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentSoundInfoBinding

class SoundInfoFragment: Fragment() {
    private var _binding: FragmentSoundInfoBinding? = null
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
        val soundId = arguments?.getInt("soundId")?:0
        with(binding)
        {
            soundInfoName.text = resources.getStringArray(R.array.sounds)[soundId]
            val fullText = resources.getStringArray(R.array.how_to_speak)[soundId]
            val sentences = fullText.split(";")
            val formattedText = sentences.joinToString("\n")

            soundInfoText.text = formattedText
            web.settings.apply {
                javaScriptEnabled = true
                loadWithOverviewMode = true
            }
            web.addJavascriptInterface(webObj(soundId),"webObj")
            web.loadUrl("file:///android_asset/asset.html")
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