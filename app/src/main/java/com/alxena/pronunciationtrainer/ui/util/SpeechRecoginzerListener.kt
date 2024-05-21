package com.alxena.pronunciationtrainer.ui.util

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast

class SpeechRecoginzerListener(
    val successListener: (text:String)->Unit,
    val errorListener : (code:Int)->Unit
        ): RecognitionListener
{
    override fun onReadyForSpeech(p0: Bundle?) {}
    override fun onBeginningOfSpeech() {}
    override fun onRmsChanged(p0: Float) {}
    override fun onBufferReceived(p0: ByteArray?) {}
    override fun onEndOfSpeech() {}
    override fun onError(p0: Int) {
        if(p0 == SpeechRecognizer.ERROR_NO_MATCH)
            errorListener.invoke(0)
        if(p0 == SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS)
            errorListener.invoke(1)
    }
    override fun onResults(p0: Bundle?) {
        val matches = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (matches != null) {
            successListener.invoke(matches[0])
        }
    }
    override fun onPartialResults(p0: Bundle?) {}
    override fun onEvent(p0: Int, p1: Bundle?) {}

}