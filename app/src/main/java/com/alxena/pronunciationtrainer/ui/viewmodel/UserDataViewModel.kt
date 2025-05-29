package com.alxena.pronunciationtrainer.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.LessonGradeDAO
import com.alxena.pronunciationtrainer.data.model.UserDAO
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class UserDataViewModel: ViewModel() {
    val info: MutableLiveData<UserDAO> = MutableLiveData()
    fun updateInfo(first_name: String, second_name: String) {
        GlobalScope.launch {
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("first_name", first_name)
                .addFormDataPart("second_name", second_name)
                .build()
            val response = APIInstance.service.changeUserInfo("Bearer ${APIInstance.access_token}", requestBody).execute()
            info.postValue(response.body())
        }
    }
}