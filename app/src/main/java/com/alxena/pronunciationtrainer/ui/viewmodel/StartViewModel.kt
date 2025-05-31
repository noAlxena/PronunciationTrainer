package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.ProfileSettingsEntity
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.model.TokenPairDAO
import com.alxena.pronunciationtrainer.data.util.APIInstance
import com.alxena.pronunciationtrainer.ui.activity.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StartViewModel:ViewModel() {
    fun refresh(activity: FragmentActivity?){
        GlobalScope.launch {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            APIInstance.refresh_token = sharedPref?.getString("refresh_token","")?:""
            with (sharedPref!!.edit()) {
                putString("refresh_token", APIInstance.refresh())
                apply()
            }
        }
    }
}