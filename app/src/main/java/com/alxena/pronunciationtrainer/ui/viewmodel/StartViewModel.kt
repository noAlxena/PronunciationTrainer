package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StartViewModel:ViewModel() {
    val settings: MutableLiveData<ProfileSettingsEntity> = MutableLiveData()
    fun getSettings(context:Context)
    {
        val db = SoundDatabase.getDatabase(context)
        GlobalScope.launch {
            settings.postValue(db.ProfileSettingDAO().select())
        }
    }
}