package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.APIController
import com.alxena.pronunciationtrainer.data.util.APIInstance
import com.alxena.pronunciationtrainer.data.util.LessonCategory
import com.alxena.pronunciationtrainer.data.util.SoundCategory
import com.alxena.pronunciationtrainer.data.util.TestData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LessonListViewModel: ViewModel() {
    val lessons: MutableLiveData<ArrayList<LessonCategory>> = MutableLiveData()
    fun getLessons(studentToken: String){
        GlobalScope.launch {
            val categories = APIInstance.service.getCategories().execute().body()
            if(categories!=null)
            {
                val cats = ArrayList<LessonCategory>()
                for(cat in categories)
                {
                    val lessons = APIInstance.service.getTopGrades(studentToken, cat.token).execute().body()
                    if(lessons!=null)
                    {
                        cats.add(LessonCategory(cat.title, cat.difficulty, cat.numColumn, lessons))
                    }
                }
                lessons.postValue(cats)
            }
        }
    }
}