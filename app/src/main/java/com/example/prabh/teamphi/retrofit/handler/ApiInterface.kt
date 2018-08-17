package com.example.prabh.teamphi.retrofit.handler

import android.support.annotation.Keep
import com.example.prabh.teamphi.retrofit.model.LoginResult
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

@Keep
interface ApiInterface {

    @FormUrlEncoded
    @POST("api/Users/Login")
    fun loginUser(@Field("Username") userName : String,@Field("Password") password : String) : Observable<LoginResult>

}