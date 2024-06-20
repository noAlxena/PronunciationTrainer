package com.alxena.pronunciationtrainer.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.SoundDatabase
import com.alxena.pronunciationtrainer.data.util.SoundCategory
import com.alxena.pronunciationtrainer.data.util.TestData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel:ViewModel() {

    val categories: MutableLiveData<ArrayList<SoundCategory>> = MutableLiveData()
    fun getCategories(context:Context){
        val db = SoundDatabase.getDatabase(context)
        val arr = ArrayList<SoundCategory>()
        GlobalScope.launch {
            var cats = db.SoundProfileDAO().getCategories()
            if(cats.isEmpty())
            {
                db.clearAllTables()
                for(a in TestData.Sounds)
                    db.SoundProfileDAO().insert(a)
                cats = db.SoundProfileDAO().getCategories()
            }
            for(i in cats){
                arr.add(SoundCategory(i,db.SoundProfileDAO().getSoundsByCategory(i)))
            }
            categories.postValue(arr)
        }
    }
}