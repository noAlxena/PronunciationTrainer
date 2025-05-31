package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class StudentGroupInvite2ViewModel : ViewModel() {
    val loginData: MutableLiveData<StudentDAO> = MutableLiveData()
    fun acceptInvite(itoken: String,first_name: String, second_name: String){
        GlobalScope.launch {
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("first_name", first_name)
                .addFormDataPart("second_name", second_name)
                .build()
            val request = APIInstance.service.acceptInvite(APIInstance.getHeader(), itoken, requestBody)
            loginData.postValue(request.execute().body())
        }
    }
}