package com.example.prabh.teamphi.mvvm.activity.AddTaskActivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.prabh.teamphi.mvvm.activity.mainactivity.MainActivityViewModel

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