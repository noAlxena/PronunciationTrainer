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
import com.alxena.pronunciationtrainer.data.model.MessageDAO
import okhttp3.RequestBody
import okhttp3.ResponseBody
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
    fun changeUserInfo(@Header("Authorization") token: String,
                       @Body body: RequestBody): Call<UserDAO>

    @POST("/groups")
    fun createGroup(@Header("Authorization") token: String,
                    @Body body: RequestBody): Call<GroupDAO>

    @GET("/groups")
    fun getGroupsTeacher(@Header("Authorization") token: String): Call<List<GroupDAO>>

    @GET("/groups")
    fun getGroupsStudent(@Header("Authorization") token: String): Call<List<GroupLoginDAO>>

    @GET("/groups/{token}/invite")
    fun invite(@Header("Authorization") token: String,
               @Path("token") groupToken: String): Call<InviteTokenDAO>

    @GET("/groups/{token}/invited")
    fun invited(@Header("Authorization") token: String,
                @Path("token") groupToken: String): Call<List<StudentDAO>>

    @GET("/groups/{token}/export")
    fun export(@Header("Authorization") token: String,
                @Path("token") groupToken: String): Call<ResponseBody>

    @GET("invite/{itoken}")
    fun checkInvite(@Header("Authorization") token: String,
                     @Path("itoken") inviteToken: String) : Call<MessageDAO>

    @POST("invite/{itoken}")
    fun acceptInvite(@Header("Authorization") token: String,
                     @Path("itoken") inviteToken: String,
                     @Body body: RequestBody) : Call<StudentDAO>

    @GET("/groups/{token}/students")
    fun getStudents(@Header("Authorization") token: String,
                    @Path("token") groupToken: String) : Call<List<StudentDAO>>

    @POST("/groups/{token}/students/{stoken}/remove")
    fun removeStudent(@Header("Authorization") token: String,
                      @Path("token") groupToken: String,
                      @Path("stoken") studentToken: String) : Call<StudentDAO>

    @POST("/groups/{token}/students/{stoken}/accept")
    fun acceptStudent(@Header("Authorization") token: String,
                      @Path("token") groupToken: String,
                      @Path("stoken") studentToken: String) : Call<StudentDAO>

    @GET("/groups/{token}/students/{stoken}/top/{ctoken}")
    fun getTopGrades(@Header("Authorization") token: String,
                     @Path("token") groupToken: String,
                     @Path("stoken") studentToken: String,
                     @Path("ctoken") categoryToken: String) : Call<List<TopGradeDAO>>

    @GET("/groups/{token}/students/{stoken}/grades/{ltoken}")
    fun getLessonGrades(@Header("Authorization") token: String,
                        @Path("token") groupToken: String,
                        @Path("stoken") studentToken: String,
                        @Path("ltoken") lessonToken: String) : Call<List<LessonGradeDAO>>

    @POST("/groups/{token}/students/{stoken}/grades/{ltoken}")
    fun checkRecording(@Header("Authorization") token: String,
                       @Body body: RequestBody,
                       @Path("token") groupToken: String,
                       @Path("stoken") studentToken: String,
                       @Path("ltoken") lessonToken: String) : Call<LessonGradeDAO>

    @GET("/categories")
    fun getCategories(@Header("Authorization") token: String):Call<List<CategoryDAO>>

    @GET("/lessons/{lessonToken}")
    fun getLesson(@Header("Authorization") token: String,
                  @Path("lessonToken") lessonToken: String):Call<LessonDAO>
}