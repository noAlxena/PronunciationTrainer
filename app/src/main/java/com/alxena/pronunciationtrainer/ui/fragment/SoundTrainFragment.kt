package com.alxena.pronunciationtrainer.ui.fragment

import android.annotation.SuppressLint
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
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentSoundTrainBinding
import com.alxena.pronunciationtrainer.ui.util.SpeechRecoginzerListener
import com.alxena.pronunciationtrainer.ui.viewmodel.SoundTrainViewModel
import java.util.Locale


class SoundTrainFragment: Fragment() {
    private var _binding: FragmentSoundTrainBinding? = null
    private val viewModel: SoundTrainViewModel by viewModels()
    private val binding get() = _binding!!
    //private lateinit var speechRecognizer: SpeechRecognizer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSoundTrainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lessonToken = arguments?.getString("lessonToken")?:""

        viewModel.getLessonInfo(lessonToken)
        viewModel.grade.observe(viewLifecycleOwner){
            binding.textGrade.text = "${it.grade}"
            binding.textTranscription.text = it.transcription
        }
        viewModel.lessonInfo.observe(viewLifecycleOwner){
            binding.textTitle.text = it.title
            binding.textContent.text = it.content
        }
        binding.buttonLessonInfo.setOnClickListener {
            findNavController().navigate(R.id.action_soundTrainFragment_to_soundInfoFragment,
                bundleOf("lessonToken" to lessonToken)
            )
        }
        binding.arrowback.setOnClickListener(){
            findNavController().popBackStack()
        }
        binding.recordButton.setOnClickListener(){
            viewModel.checkSpelling(requireContext(), lessonToken)
        }
        /*
        with(binding){
            recordButton.setOnClickListener{

                recordButton.setImageResource(R.drawable.baseline_keyboard_voice_48)

                Toast.makeText(requireContext(), resources.getString(R.string.record_start), Toast.LENGTH_LONG).show()

                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ru")
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, resources.getString(R.string.speak))
                speechRecognizer.startListening(intent)
            }

            val successListener = {
                    result :String ->
                mark.text = resources.getString(R.string.grade)
                said.text = resources.getString(R.string.sound_info)
                recordButton.setImageResource(R.drawable.baseline_keyboard_voice_24)
                if(viewModel.checkSpelling(requireContext(), soundId,result)) {
                    grade.text = resources.getString(R.string.correct)
                }
                else
                    grade.text = resources.getString(R.string.wrong)
                results.text = result
            }
            val errorListener = {

                code :Int->
                recordButton.setImageResource(R.drawable.baseline_keyboard_voice_24)
                mark.text = " "
                said.text = " "
                grade.text = resources.getString(R.string.wrong)
                if(code == 0)
                    results.text = resources.getString(R.string.not_recognized)
                else
                    results.text = resources.getString(R.string.no_permision)
            }
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
            speechRecognizer.setRecognitionListener(
                SpeechRecoginzerListener(successListener,errorListener)
            )
        }
         */
    }


    override fun onDestroyView() {
        super.onDestroyView()
        //speechRecognizer.destroy();
        _binding = null
    }
}