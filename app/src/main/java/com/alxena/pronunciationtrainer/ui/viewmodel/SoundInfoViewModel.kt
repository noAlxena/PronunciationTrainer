package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.LessonDAO
import com.alxena.pronunciationtrainer.data.model.LessonGradeDAO
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.APIController
import com.alxena.pronunciationtrainer.data.util.LessonCategory
import com.alxena.pronunciationtrainer.data.util.SoundData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SoundInfoViewModel: ViewModel() {
    private val API = Retrofit
        .Builder().baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service: APIController = API.create(APIController::class.java)
    val lessonInfo: MutableLiveData<LessonDAO> = MutableLiveData()

    fun getLessonInfo(lessonToken: String){
        GlobalScope.launch {
            val lsn = service.getLesson(lessonToken).execute().body()
            lsn?.let{
                lessonInfo.postValue(it)
            }
        }
    }
}