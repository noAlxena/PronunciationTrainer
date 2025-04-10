package com.alxena.pronunciationtrainer.data.util

import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.data.model.tokenDAO
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST


interface APIController {
    @POST("/teacher")
    fun createTeacher(@Body body: RequestBody) : Call<tokenDAO>

    @POST("/student")
    fun createStudent(@Body body: RequestBody) : Call<tokenDAO>
/*
    @PUT("/grade")
    fun checkRecording(@Body body: RequestBody) : Call<GradeDAO>

    @GET("/student")
    fun getStudents(@Body body: RequestBody) : Call<ArrayList<StudentDAO>>

    @GET("/grade")
    fun getGrades(@Body body: RequestBody) : Call<ArrayList<GradeDAO>>
    */
}