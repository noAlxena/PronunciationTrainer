package com.alxena.pronunciationtrainer.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alxena.pronunciationtrainer.databinding.FragmentSoundTrainBinding
import com.alxena.pronunciationtrainer.ui.util.SpeechRecoginzerListener
import java.util.Locale


class SoundTrainFragment: Fragment() {
    private var _binding: FragmentSoundTrainBinding? = null
    private val binding get() = _binding!!
    private lateinit var speechRecognizer: SpeechRecognizer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSoundTrainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer.setRecognitionListener(
            SpeechRecoginzerListener({
                abc:String ->
                binding.textView2.text = abc
            },{
                Toast.makeText(context,"no match", Toast.LENGTH_LONG).show()
            })
        )
        binding.button2.setOnClickListener{
            Log.e("app","clicked")
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now")
            speechRecognizer.startListening(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        speechRecognizer.destroy();
        _binding = null
    }
}