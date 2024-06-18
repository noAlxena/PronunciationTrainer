package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.SoundData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SoundTrainViewModel: ViewModel() {
    fun saveResults(context: Context,soundId: Int){
        val db = SoundDatabase.getDatabase(context)
        GlobalScope.launch {
            db.SoundProfileDAO().completeSound(soundId)
        }
    }
    fun checkSpelling(context: Context, soundId: Int, result :String):Boolean{
        for(i in SoundData.data[soundId])
        {
            if(result.contains(i, ignoreCase = true))
            {
                saveResults(context,soundId)
                return true
            }
        }
        return false
    }
}