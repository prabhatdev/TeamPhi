package com.example.prabh.teamphi.utility

import android.content.Context
import android.content.SharedPreferences

class Session(val context:Context)
{
    private var editor:SharedPreferences.Editor?=null
    private var sharedPreferences:SharedPreferences
    init {
        sharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
    }

    fun setIsLogedIn(value:Boolean)
    {
        editor=sharedPreferences.edit()
        editor?.putBoolean(IS_LOGIN,value)
        editor?.apply()
    }

    fun saveLoginDetails(token:String,userName:String,isAdmin:String,expiresOn:String,password:String)
    {
        editor=sharedPreferences.edit()
        editor?.putString(TOKEN,token)
        editor?.putString(USERNAME,userName)
        editor?.putString(IS_ADMIN,isAdmin)
        editor?.putString(EXPIRES_ON,expiresOn)
        editor?.putString(PASSWORD,password)
        editor?.apply()

    }

    fun isLoggedIn():Boolean
    {
     return sharedPreferences.getBoolean(IS_LOGIN,false)
    }

    fun getStringValue(key:String,default: String?=null)=sharedPreferences.getString(key,default)


    companion object {
        const val PREF_NAME="TeamPhi"
        const val IS_LOGIN="IsLoggedIn"
        const val TOKEN="token"
        const val USERNAME="username"
        const val IS_ADMIN="isAdmin"
        const val EXPIRES_ON="expires_on"
        const val PASSWORD="password"
    }
}