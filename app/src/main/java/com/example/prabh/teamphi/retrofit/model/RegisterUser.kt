package com.example.prabh.teamphi.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterUser
{
    @SerializedName("status")
    @Expose
    val status:String?=null

    @SerializedName("Data")
    @Expose
    val data:RegisterData?=null
}