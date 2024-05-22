package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.TestData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SettingViewModel:ViewModel() {
    fun reset(context: Context){
        val db = SoundDatabase.getDatabase(context)
        GlobalScope.launch {
            db.clearAllTables()
            for(a in TestData.Sounds)
                db.SoundProfileDAO().insert(a)
        }
    }
}