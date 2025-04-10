package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.APIController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeacherRegViewModel:ViewModel() {
    val API = Retrofit
        .Builder().baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val service: APIController = API.create(APIController::class.java)
    val registered: MutableLiveData<Boolean> = MutableLiveData()
    fun getToken(context:Context, login:String){
        val db = SoundDatabase.getDatabase(context)
        GlobalScope.launch {
            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("login", login)
                .build()
            val response = service.createTeacher(requestBody).execute()
            response.body()?.let {
                val settings = ProfileSettingsEntity(
                    0,
                    it.token,
                    login,
                    null
                )
                db.ProfileSettingDAO().insert(settings)
                registered.postValue(true)
            }
            //change to enqueue with callback later
        }
    }

}