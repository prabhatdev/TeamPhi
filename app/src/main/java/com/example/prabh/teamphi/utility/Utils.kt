package com.example.prabh.teamphi.utility

import android.content.Context
import android.widget.Toast

class Utils(private val tag:String)
{
    companion object {
        private const val BASE_URL = ""


        fun provideUtil(context: Context) : Utils{
            var activityname = Utils.getClassName(context.javaClass.name)
            activityname=if (activityname.length>20) {
                activityname.substring(0, 20)
            }
            else{
                activityname
            }
            return Utils(activityname)
        }

        fun showToast(context: Context,message:String,length:Int) {
            Toast.makeText(context,message,length).show()
        }

        fun getClassName(fullName: String) = fullName.substring(fullName.lastIndexOf('.') + 1, fullName.length)
    }

}