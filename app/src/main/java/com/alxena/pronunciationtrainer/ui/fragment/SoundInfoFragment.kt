package com.alxena.pronunciationtrainer.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import androidx.fragment.app.Fragment
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
    class to{
        @JavascriptInterface
        fun hello():String
        {
        return "hello";
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.web.settings.apply {
            javaScriptEnabled = true
            loadWithOverviewMode = true
        }
        binding.web.addJavascriptInterface(to(),"testobj")
        binding.web.loadUrl("file:///android_asset/asset.html")

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}