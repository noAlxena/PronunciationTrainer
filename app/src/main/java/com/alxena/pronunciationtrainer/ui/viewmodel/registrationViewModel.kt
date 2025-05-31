package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.model.TokenPairDAO
import com.alxena.pronunciationtrainer.data.model.UserDAO
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class registrationViewModel:ViewModel() {
    val info: MutableLiveData<UserDAO> = MutableLiveData()
    val status: MutableLiveData<TokenPairDAO?> = MutableLiveData()
    fun register(login: String, password: String, role:String){
        GlobalScope.launch {
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("login", login)
                .addFormDataPart("password", password)
                .addFormDataPart("role", role)
                .build()
            val response = APIInstance.service.register(requestBody).execute()
            if(response.code() == 200){
                val access_token = response.body()?.access_token
                if(access_token != null)
                {
                    val response2 = APIInstance.service.getUserInfo("Bearer ${access_token}").execute()
                    if(response2.code() == 200)
                    {
                        info.postValue(response2.body())
                        status.postValue(response.body())
                    }
                }
            }
            else{
                status.postValue(null)
            }
        }
    }
}