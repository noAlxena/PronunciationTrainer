package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.InviteTokenDAO
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeacherGroupInviteViewModel : ViewModel() {
    val code: MutableLiveData<InviteTokenDAO> = MutableLiveData()
    fun getInviteCode(groupToken: String){
        GlobalScope.launch {
            code.postValue(APIInstance.service.invite(APIInstance.getHeader(), groupToken).execute().body())
        }
    }
}