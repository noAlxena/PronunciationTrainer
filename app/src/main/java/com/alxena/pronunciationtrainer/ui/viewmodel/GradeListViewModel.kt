package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.LessonGradeDAO
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.data.util.APIController
import com.alxena.pronunciationtrainer.data.util.SoundCategory
import com.alxena.pronunciationtrainer.data.util.TestData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GradeListViewModel:ViewModel() {
    private val API = Retrofit
        .Builder().baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service: APIController = API.create(APIController::class.java)
    val grades: MutableLiveData<List<LessonGradeDAO>> = MutableLiveData()
    fun getGrades(studentToken: String, lessonToken: String){
        GlobalScope.launch {
            val response = service.getLessonGrades(studentToken, lessonToken).execute()
            if(response.body()!=null){
                grades.postValue(response.body())
            }
        }
    }
}