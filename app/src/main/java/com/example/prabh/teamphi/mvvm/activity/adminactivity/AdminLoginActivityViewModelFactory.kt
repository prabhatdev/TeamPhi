package com.example.prabh.teamphi.mvvm.activity.adminactivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class AdminLoginActivityViewModelFactory:ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminLoginActivityViewModel::class.java)) {
            return AdminLoginActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}