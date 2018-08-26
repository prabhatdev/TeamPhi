package com.example.prabh.teamphi.mvvm.activity.itemactivity

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ItemActivityViewModelFactory:ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemActivityViewModel::class.java)) {
            return ItemActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}