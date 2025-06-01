package com.alxena.pronunciationtrainer.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alxena.pronunciationtrainer.data.model.GroupDAO
import com.alxena.pronunciationtrainer.data.util.APIInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import okhttp3.ResponseBody

class TeacherGroupViewModel : ViewModel() {
    val groups: MutableLiveData<List<GroupDAO>> = MutableLiveData()
    val table: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    fun getGroups(){
        GlobalScope.launch {
            groups.postValue(APIInstance.service.getGroupsTeacher(APIInstance.getHeader()).execute().body())
        }
    }
    fun export(groupToken: String){
        GlobalScope.launch {
            table.postValue(APIInstance.service.export(APIInstance.getHeader(),groupToken).execute())
        }
    }
}