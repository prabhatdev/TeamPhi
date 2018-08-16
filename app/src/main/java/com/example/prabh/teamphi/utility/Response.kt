package com.example.prabh.teamphi.utility

import android.support.annotation.NonNull
import android.util.Log
import retrofit2.HttpException

class Response private constructor(val status: Status, val apiType: ApiType, val error: Throwable?, val result: Any?) {

    companion object {
        private const val TAG = "API RESPONSE"

        fun loading(apiType: ApiType, result: Any? = null): Response {
            return Response(Status.LOADING, apiType,null, result)
        }

        fun success(apiType: ApiType, result: Any? = null): Response {
            return Response(Status.SUCCESS, apiType, null, result)
        }

        fun error(apiType: ApiType, @NonNull error: Throwable, result: Any? = null): Response {
            if (error is HttpException) {
                val code = error.code()
            }
            return Response(Status.ERROR, apiType, error, result)
        }
    }
}