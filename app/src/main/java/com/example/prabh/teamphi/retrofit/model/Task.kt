package com.example.prabh.teamphi.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Task {
    @SerializedName("Tasks")
    @Expose
    val tasks:List<Tasks>?=null

    @SerializedName("Count")
    @Expose
    val count:Int?=null

}