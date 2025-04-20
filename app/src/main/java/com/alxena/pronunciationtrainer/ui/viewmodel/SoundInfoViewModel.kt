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
            val lsn = APIInstance.service.getLesson(lessonToken).execute().body()
            lsn?.let{
                lessonInfo.postValue(it)
            }
        }
    }
}