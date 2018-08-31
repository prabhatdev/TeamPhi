package com.example.prabh.teamphi.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VerifyItemsList {
    @SerializedName("Item")
    @Expose
    val itemsDetails: VerifyItemDetails? = null

    @SerializedName("TaskId")
    @Expose
    val taskId: String? = null

    @SerializedName("AssignedUsername")
    @Expose
    val assignedUserName:String?=null
}