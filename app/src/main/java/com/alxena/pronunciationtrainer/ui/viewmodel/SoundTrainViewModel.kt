package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.LessonDAO
import com.alxena.pronunciationtrainer.data.model.LessonGradeDAO
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SoundTrainViewModel: ViewModel() {
    val lessonInfo: MutableLiveData<LessonDAO> = MutableLiveData()
    val grade: MutableLiveData<LessonGradeDAO> = MutableLiveData()

    fun checkSpelling(groupToken: String,
                      studentToken: String,
                      lessonToken: String,
                      filename:String) {
        val file = File(filename)
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("audio", file.name,
                RequestBody.create(MediaType.parse("audio/mpeg"),file))
            .build()
        GlobalScope.launch {
            val request = APIInstance.service.checkRecording(
                APIInstance.getHeader(),
                requestBody,
                groupToken,
                studentToken,
                lessonToken)
            grade.postValue(request.execute().body())
        }
    }
    fun getLessonInfo(lessonToken: String){
        GlobalScope.launch {
            val request = APIInstance.service.getLesson(APIInstance.getHeader(), lessonToken)
            lessonInfo.postValue(request.execute().body())
        }
    }
}