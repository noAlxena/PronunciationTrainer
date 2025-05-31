package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.GroupLoginDAO
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeacherGroupInvitedViewModel : ViewModel() {
    val invites: MutableLiveData<List<StudentDAO>> = MutableLiveData()
    fun getInvites(groupToken: String){
        GlobalScope.launch {
            invites.postValue(APIInstance.service.invited(
                APIInstance.getHeader(),
                groupToken).execute().body())
        }
    }
    fun accept(groupToken: String, studentToken: String){
        GlobalScope.launch {
            APIInstance.service.acceptStudent(
                APIInstance.getHeader(),
                groupToken,
                studentToken
            ).execute()
            getInvites(groupToken)
        }
    }
    fun deny(groupToken: String, studentToken: String){
        GlobalScope.launch {
            APIInstance.service.removeStudent(
                APIInstance.getHeader(),
                groupToken,
                studentToken
            ).execute()
            getInvites(groupToken)
        }
    }
}