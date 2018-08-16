package com.example.prabh.teamphi.utility

import android.content.Context
import android.widget.Toast
import com.example.prabh.teamphi.retrofit.handler.ApiInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Utils(private val tag: String) {
    companion object {
        private const val BASE_URL = ""


        fun provideUtil(context: Context): Utils {
            var activityname = Utils.getClassName(context.javaClass.name)
            activityname = if (activityname.length > 20) {
                activityname.substring(0, 20)
            } else {
                activityname
            }
            return Utils(activityname)
        }

        fun showToast(context: Context, message: String, length: Int) {
            Toast.makeText(context, message, length).show()
        }

        fun getClassName(fullName: String) = fullName.substring(fullName.lastIndexOf('.') + 1, fullName.length)

        val interfaceService: ApiInterface
            get() {
                val BASE_URL = "http://13.126.180.89/Main/"
                val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                return retrofit.create<ApiInterface>(ApiInterface::class.java)
            }
    }
}