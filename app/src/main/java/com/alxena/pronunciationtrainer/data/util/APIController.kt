package com.alxena.pronunciationtrainer.data.util

import com.alxena.pronunciationtrainer.data.model.CategoryDAO
import com.alxena.pronunciationtrainer.data.model.LessonDAO
import com.alxena.pronunciationtrainer.data.model.LessonGradeDAO
import com.alxena.pronunciationtrainer.data.model.TopGradeDAO
import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.data.model.TokenDAO
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface APIController {
    @POST("/teachers")
    fun createTeacher(@Body body: RequestBody) : Call<TokenDAO>

    @POST("/teachers/{teacherToken}/students")
    fun createStudent(@Path("teacherToken") teacherToken: String, @Body body: RequestBody) : Call<TokenDAO>

    @GET("/teachers/{teacherToken}/students")
    fun getStudents(@Path("teacherToken") teacherToken: String) : Call<List<StudentDAO>>

    @GET("/categories")
    fun getCategories():Call<List<CategoryDAO>>

    @GET("/students/{studentToken}/top/{categoryToken}")
    fun getTopGrades(@Path("studentToken") studentToken: String, @Path("categoryToken") categoryToken: String) : Call<List<TopGradeDAO>>

    @GET("/students/{studentToken}/grades/{lessonToken}")
    fun getLessonGrades(@Path("studentToken") studentToken: String, @Path("lessonToken") lessonToken: String) : Call<List<LessonGradeDAO>>

    @POST("/students/{studentToken}/grades/{lessonToken}")
    fun checkRecording(@Body body: RequestBody, @Path("studentToken") studentToken: String, @Path("lessonToken") lessonToken: String) : Call<LessonGradeDAO>

    @GET("/lessons/{lessonToken}")
    fun getLesson(@Path("lessonToken") lessonToken: String):Call<LessonDAO>
}