package com.alxena.pronunciationtrainer.ui.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alxena.pronunciationtrainer.R
import com.alxena.pronunciationtrainer.databinding.FragmentSoundTrainBinding
import com.alxena.pronunciationtrainer.ui.util.SpeechRecoginzerListener
import com.alxena.pronunciationtrainer.ui.viewmodel.ListViewModel
import com.alxena.pronunciationtrainer.ui.viewmodel.SoundTrainViewModel
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import java.io.File
import java.util.Locale


class SoundTrainFragment: Fragment() {
    private var _binding: FragmentSoundTrainBinding? = null
    private val viewModel: SoundTrainViewModel by viewModels()
    private val binding get() = _binding!!
    private lateinit var speechRecognizer: SpeechRecognizer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSoundTrainBinding.inflate(inflater, container, false)
        return binding.root
    }
    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val soundId = arguments?.getInt("soundId")?:0
        with(binding){
            soundName.text = resources.getStringArray(R.array.sounds)[soundId]
            soundInfoButton.setOnClickListener {
                findNavController().navigate(R.id.action_soundTrainFragment_to_soundInfoFragment,
                    bundleOf("soundId" to soundId)
                )
            }

            binding.arrowback.setOnClickListener(){
                findNavController().navigate(R.id.action_soundTrainFragment_to_listFragment)
            }

            binding.backhome.setOnClickListener(){
                findNavController().navigate(R.id.action_soundTrainFragment_to_startFragment)
            }

            recordButton.setOnClickListener{

                recordButton.setImageResource(R.drawable.baseline_keyboard_voice_48)

                Toast.makeText(requireContext(), "Запись началась", Toast.LENGTH_LONG).show()

                //results.text = "..."
                //grade.text = "..."
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, resources.getString(R.string.speak))
                speechRecognizer.startListening(intent)
            }

            val successListener = {
                    result :String ->
                mark.text = "Ваша оценка:"
                said.text = "Вы произнесли:"
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
    }


    override fun onDestroyView() {
        super.onDestroyView()
        speechRecognizer.destroy();
        _binding = null
    }
}