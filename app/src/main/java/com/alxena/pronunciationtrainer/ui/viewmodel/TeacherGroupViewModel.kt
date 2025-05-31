package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.GroupDAO
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeacherGroupViewModel : ViewModel() {
    val groups: MutableLiveData<List<GroupDAO>> = MutableLiveData()
    fun getGroups(){
        GlobalScope.launch {
            groups.postValue(APIInstance.service.getGroupsTeacher(APIInstance.getHeader()).execute().body())
        }
    }
}