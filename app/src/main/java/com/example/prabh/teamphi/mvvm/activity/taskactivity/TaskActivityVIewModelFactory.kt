package com.example.prabh.teamphi.mvvm.activity.taskactivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class TaskActivityVIewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskActivityViewModel::class.java)) {
            return TaskActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown Model Class")
    }
}