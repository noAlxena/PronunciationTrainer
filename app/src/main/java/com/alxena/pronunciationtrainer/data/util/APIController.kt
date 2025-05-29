package com.alxena.pronunciationtrainer.data.util

import com.alxena.pronunciationtrainer.data.model.CategoryDAO
import com.alxena.pronunciationtrainer.data.model.GroupDAO
import com.alxena.pronunciationtrainer.data.model.LessonDAO
import com.alxena.pronunciationtrainer.data.model.LessonGradeDAO
import com.alxena.pronunciationtrainer.data.model.TopGradeDAO
import com.alxena.pronunciationtrainer.data.model.StudentDAO
import com.alxena.pronunciationtrainer.data.model.InviteTokenDAO
import com.alxena.pronunciationtrainer.data.model.TokenPairDAO
import com.alxena.pronunciationtrainer.data.model.UserDAO
import com.alxena.pronunciationtrainer.data.model.GroupLoginDAO
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


interface APIController {
    @POST("/register")
    fun register(@Body body: RequestBody) : Call<TokenPairDAO>

    @POST("/login")
    fun login(@Body body: RequestBody) : Call<TokenPairDAO>

    @POST("/refresh")
    fun refresh(@Header("Authorization") token: String) : Call<TokenPairDAO>

    @GET("/userInfo")
    fun getUserInfo(@Header("Authorization") token: String): Call<UserDAO>

    @POST("/userInfo")
    fun changeUserInfo(@Header("Authorization") token: String, @Body body: RequestBody): Call<UserDAO>

    @POST("/groups")
    fun createGroup(@Header("Authorization") token: String, @Body body: RequestBody): Call<GroupDAO>

    @GET("/groups")
    fun getGroupsTeacher(@Header("Authorization") token: String): Call<List<GroupDAO>>

    @GET("/groups")
    fun getGroupsStudent(@Header("Authorization") token: String): Call<List<GroupLoginDAO>>

    @GET("/groups/{token}/invite")
    fun invite(@Path("token") groupToken: String): Call<InviteTokenDAO>

    @GET("/groups/{token}/invited")
    fun invited(@Path("token") groupToken: String): Call<List<StudentDAO>>

    //@GET("/groups/{token}/export")
    //fun invited(@Path("token") groupToken: String): Call<List<StudentInfo>>

    @POST("invite/{itoken}")
    fun invite(@Path("itoken") inviteToken: String, @Body body: RequestBody) : Call<StudentDAO>

    @GET("/groups/{token}/students")
    fun getStudents(@Path("token") groupToken: String) : Call<List<StudentDAO>>

    @POST("/groups/{token}/students/{stoken}/remove")
    fun removeStudent(@Path("token") groupToken: String, @Path("stoken") studentToken: String) : Call<List<StudentDAO>>

    @POST("/groups/{token}/students/{stoken}/accept")
    fun acceptStudent(@Path("token") groupToken: String, @Path("stoken") studentToken: String) : Call<List<StudentDAO>>

    @GET("/groups/{token}/students/{stoken}/top/{ctoken}")
    fun getTopGrades(@Path("token") groupToken: String, @Path("stoken") studentToken: String, @Path("ctoken") categoryToken: String) : Call<List<TopGradeDAO>>

    @GET("/groups/{token}/students/{stoken}/grades/{ltoken}")
    fun getLessonGrades(@Path("token") groupToken: String, @Path("stoken") studentToken: String, @Path("ltoken") lessonToken: String) : Call<List<LessonGradeDAO>>

    @POST("/groups/{token}/students/{stoken}/grades/{ltoken}")
    fun checkRecording(@Body body: RequestBody, @Path("token") groupToken: String, @Path("stoken") studentToken: String, @Path("ltoken") lessonToken: String) : Call<LessonGradeDAO>

    @GET("/categories")
    fun getCategories():Call<List<CategoryDAO>>

    @GET("/lessons/{lessonToken}")
    fun getLesson(@Path("lessonToken") lessonToken: String):Call<LessonDAO>
}