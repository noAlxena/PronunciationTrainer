package com.alxena.pronunciationtrainer.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.LessonDAO
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SoundInfoViewModel: ViewModel() {
    val lessonInfo: MutableLiveData<LessonDAO> = MutableLiveData()
    fun getLessonInfo(lessonToken: String){
        GlobalScope.launch {
            val request = APIInstance.service.getLesson(APIInstance.getHeader(), lessonToken)
            lessonInfo.postValue(request.execute().body())
        }
    }
}