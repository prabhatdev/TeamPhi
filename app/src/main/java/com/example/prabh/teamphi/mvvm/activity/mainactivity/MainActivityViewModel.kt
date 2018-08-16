package com.example.prabh.teamphi.mvvm.activity.mainactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.teamphi.utility.Response
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel : ViewModel()
{
    val compositeDisposable=CompositeDisposable()
    val response:MutableLiveData<Response> = MutableLiveData()

}