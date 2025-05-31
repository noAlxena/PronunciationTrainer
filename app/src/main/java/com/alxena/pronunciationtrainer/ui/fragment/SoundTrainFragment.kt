package com.alxena.pronunciationtrainer.ui.fragment

import android.Manifest
import android.media.MediaRecorder
import android.os.Bundle
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
import com.alxena.pronunciationtrainer.ui.viewmodel.SoundTrainViewModel
import java.io.IOException

private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

//student lesson fragment
class SoundTrainFragment: Fragment() {
    private var _binding: FragmentSoundTrainBinding? = null
    private val viewModel: SoundTrainViewModel by viewModels()
    private val binding get() = _binding!!
    var recorder: MediaRecorder? = null
    var recording = false
    lateinit var fileName: String
    lateinit var groupToken: String
    lateinit var studentToken: String
    lateinit var lessonToken: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSoundTrainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        groupToken = arguments?.getString("groupToken")?:""
        studentToken = arguments?.getString("studentToken")?:""
        lessonToken = arguments?.getString("lessonToken")?:""
        fileName = "${requireContext().externalCacheDir?.absolutePath}/audiorecordtest.mp3"
        var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
        requestPermissions(permissions, REQUEST_RECORD_AUDIO_PERMISSION)

        viewModel.getLessonInfo(lessonToken)
        viewModel.grade.observe(viewLifecycleOwner){
            binding.textGrade.text = "${it.grade}"
            binding.textTranscription.text = it.transcription
            binding.textGradeLabel.text = "Оценка за урок"
            binding.textTranscriptionLabel.text = "транскрипция"
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
            if(recording){
                stopRecording()
            }
            else{
                startRecording()
            }
            recording = !recording
        }
    }
    fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioEncodingBitRate(384000)
            setAudioSamplingRate(16000)
            try {
                prepare()
            } catch (e: IOException) {
                Toast.makeText(requireContext(), "prepare error", Toast.LENGTH_LONG).show()
            }
            start()
        }
        binding.recordButton.setImageResource(R.drawable.baseline_keyboard_voice_48)
    }
    fun stopRecording(){
        recorder?.apply{
            stop()
            release()
        }
        recorder = null
        viewModel.checkSpelling(groupToken, studentToken, lessonToken, fileName)
        binding.recordButton.setImageResource(R.drawable.baseline_keyboard_voice_24)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}