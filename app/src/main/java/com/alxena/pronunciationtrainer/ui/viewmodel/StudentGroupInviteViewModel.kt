package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.MessageDAO
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentGroupInviteViewModel : ViewModel() {
    val response: MutableLiveData<MessageDAO?> = MutableLiveData()
    fun checkInvite(code: String){
        GlobalScope.launch {
            val resp = APIInstance.service.checkInvite(APIInstance.getHeader(), code).execute()
            if(resp.code() != 200)
                response.postValue(null)
            else
                response.postValue(resp.body())
        }
    }
}