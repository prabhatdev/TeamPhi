package com.example.prabh.teamphi.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddItem
{
    @SerializedName("status")
    @Expose
    val status:String?=null

    @SerializedName("data")
    @Expose
    val data:AddItemData?=null
}