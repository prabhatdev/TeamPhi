package com.example.prabh.teamphi.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VerifyItemData
{
    @SerializedName("Items")
    @Expose
    val item:ArrayList<VerifyItemsList>?=null

    @SerializedName("Count")
    @Expose
    val count:Int?=null
}