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
import com.alxena.pronunciationtrainer.data.util.APIInstance
import com.alxena.pronunciationtrainer.data.util.SoundCategory
import com.alxena.pronunciationtrainer.data.util.TestData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GradeListViewModel:ViewModel() {
    val grades: MutableLiveData<List<LessonGradeDAO>> = MutableLiveData()
    fun getGrades(studentToken: String, lessonToken: String){
        GlobalScope.launch {
            val response = APIInstance.service.getLessonGrades(studentToken, lessonToken).execute()
            if(response.body()!=null){
                grades.postValue(response.body())
            }
        }
    }
}