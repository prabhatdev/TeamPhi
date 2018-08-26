package com.example.prabh.teamphi.retrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Login {

    @SerializedName("Username")
    @Expose
    val userName : String? = null

    @SerializedName("isAdmin")
    @Expose
    val isAdmin : String? = null

    @SerializedName("Token")
    @Expose
    val token : String? = null

    @SerializedName("ExpiresOn")
    @Expose
    val expiresOn : String? = null
}