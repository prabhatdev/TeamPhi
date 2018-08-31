package com.example.prabh.teamphi.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VerifyItem
{
    @SerializedName("Ok")
    @Expose
    val status:String?=null

    @SerializedName("data")
    @Expose
    val data:VerifyItemData?=null
}