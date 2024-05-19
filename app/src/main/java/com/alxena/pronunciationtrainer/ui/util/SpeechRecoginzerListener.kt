package com.alxena.pronunciationtrainer.ui.util

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Toast

class SpeechRecoginzerListener(val listener: (text:String)->Unit,val er : ()->Unit): RecognitionListener
{

    init{
        Log.e("app","inited")
    }
    override fun onReadyForSpeech(p0: Bundle?) {
        Log.e("app","rFs")
    }

    override fun onBeginningOfSpeech() {
        Log.e("app","bOfS")
    }

    override fun onRmsChanged(p0: Float) {
        Log.e("app","onRmsC")
    }

    override fun onBufferReceived(p0: ByteArray?) {
        Log.e("app","bRec")
    }

    override fun onEndOfSpeech() {
        Log.e("app","eOs")
    }

    override fun onError(p0: Int) {

        Log.e("app","err")
        Log.e("code","${p0}")
        Log.e("errors:","errors")
        Log.e("error","${SpeechRecognizer.ERROR_NETWORK_TIMEOUT}")
        Log.e("error","${SpeechRecognizer.ERROR_NETWORK}")
        Log.e("error","${SpeechRecognizer.ERROR_AUDIO}")
        Log.e("error","${SpeechRecognizer.ERROR_SERVER}")
        Log.e("error","${SpeechRecognizer.ERROR_CLIENT}")
        Log.e("error","${SpeechRecognizer.ERROR_SPEECH_TIMEOUT}")
        Log.e("error","${SpeechRecognizer.ERROR_NO_MATCH}")
        Log.e("error","${SpeechRecognizer.ERROR_RECOGNIZER_BUSY}")
        Log.e("error","${SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS}")
        Log.e("error","${SpeechRecognizer.ERROR_TOO_MANY_REQUESTS}")
        Log.e("error","${SpeechRecognizer.ERROR_SERVER_DISCONNECTED}")
        Log.e("error","${SpeechRecognizer.ERROR_LANGUAGE_NOT_SUPPORTED}")
        Log.e("error","${SpeechRecognizer.ERROR_LANGUAGE_UNAVAILABLE}")
        Log.e("error","${SpeechRecognizer.ERROR_CANNOT_CHECK_SUPPORT}")
        Log.e("error","${SpeechRecognizer.ERROR_CANNOT_LISTEN_TO_DOWNLOAD_EVENTS}")
        if(p0 == SpeechRecognizer.ERROR_NO_MATCH)
            er.invoke()

    }

    override fun onResults(p0: Bundle?) {
        Log.e("app","onRes")
        var matches = p0?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (matches != null) {
            listener.invoke(matches.get(0))
        }
    }

    override fun onPartialResults(p0: Bundle?) {
        Log.e("app","pRes")
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
        Log.e("app","event")
    }

}