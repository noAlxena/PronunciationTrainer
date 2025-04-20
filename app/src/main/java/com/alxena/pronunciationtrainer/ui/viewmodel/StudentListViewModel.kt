package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

class StudentListViewModel:ViewModel() {
    val students: MutableLiveData<List<StudentDAO>> = MutableLiveData()
    fun getStudents(context:Context){
        val db = SoundDatabase.getDatabase(context)
        //val arr = ArrayList<SoundCategory>()

        GlobalScope.launch {
            val settings = db.ProfileSettingDAO().select()
            settings?.let {
                val response = APIInstance.service.getStudents(it.token).execute()
                if(response.body()!=null){
                    students.postValue(response.body())
                }
            }
        }
    }
}