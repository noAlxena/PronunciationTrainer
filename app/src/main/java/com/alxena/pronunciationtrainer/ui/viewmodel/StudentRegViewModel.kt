package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class StudentRegViewModel:ViewModel() {
    val registered: MutableLiveData<Boolean> = MutableLiveData()
    fun getToken(context:Context, teacherToken:String, login:String){
        val db = SoundDatabase.getDatabase(context)
        GlobalScope.launch {
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("login", login)
                .build()
            val response = APIInstance.service.createStudent(teacherToken, requestBody).execute()
            response.body()?.let{
                val settings = ProfileSettingsEntity(
                    0,
                    it.token,
                    login,
                    teacherToken
                )
                db.ProfileSettingDAO().insert(settings)
                registered.postValue(true)
            }
        }
    }
}