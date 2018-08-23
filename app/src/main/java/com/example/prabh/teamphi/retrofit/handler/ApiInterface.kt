package com.example.prabh.teamphi.retrofit.handler

import android.support.annotation.Keep
import com.example.prabh.teamphi.retrofit.model.LoginResult
import com.example.prabh.teamphi.retrofit.model.RegisterUser
import com.example.prabh.teamphi.retrofit.model.TaskUser
import io.reactivex.Observable
import retrofit2.http.*

@Keep
interface ApiInterface {

    @FormUrlEncoded
    @POST("api/Users/Login")
    fun loginUser(@Field("Username") userName: String, @Field("Password") password: String): Observable<LoginResult>

    @FormUrlEncoded
    @POST("api/Users/RegisterUser")
    fun registerUser(@Field("Username") userName: String, @Field("Name") name: String, @Field("Designation") designation: String, @Field("Password") password: String): Observable<RegisterUser>

    @GET("api/Task/GetAllTasks")
    fun getAllTasks(@Header("token") token: String): Observable<TaskUser>

}