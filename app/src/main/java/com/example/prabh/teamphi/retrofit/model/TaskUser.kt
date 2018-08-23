package com.example.prabh.teamphi.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TaskUser {
    @SerializedName("data")
    @Expose
    val data:Task?=null

    @SerializedName("status")
    @Expose
    val status:String?=null

    @SerializedName("error")
    @Expose
    val error:String?=null
}