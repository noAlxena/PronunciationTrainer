package com.alxena.pronunciationtrainer.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.LessonGradeDAO
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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