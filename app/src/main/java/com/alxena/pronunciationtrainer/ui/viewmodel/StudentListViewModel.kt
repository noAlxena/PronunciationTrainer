package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentListViewModel:ViewModel() {
    val students: MutableLiveData<List<StudentDAO>> = MutableLiveData()
    fun getStudents(groupToken: String){
        GlobalScope.launch {
            val request = APIInstance.service.getStudents(APIInstance.getHeader(), groupToken)
            students.postValue(request.execute().body())
        }
    }
}