package com.example.prabh.teamphi.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tasks {
    @SerializedName("Id")
    @Expose
    val taskId: String? = null

    @SerializedName("TaskName")
    @Expose
    val taskName: String? = null

    @SerializedName("ItemCount")
    @Expose
    val itemCount:Int?= null

    @SerializedName("AssignedUserName")
    @Expose
    val assignedUserName: String? = null

    @SerializedName("TotalCost")
    @Expose
    val totalCost: String? = null

    @SerializedName("CreationDate")
    @Expose
    val creationDate: String? = null

    @SerializedName("CreatedByUsername")
    @Expose
    val createdByUserName: String? = null


}