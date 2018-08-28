package com.example.prabh.teamphi.mvvm.activity.AddItemActivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class AddItemActivityViewModelFactory:ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddItemActivityViewModel::class.java))
        {
            return AddItemActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}