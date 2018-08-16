package com.example.prabh.teamphi.mvvm.activity.mainactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.teamphi.retrofit.model.LoginResult
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel()
{
    val compositeDisposable=CompositeDisposable()
    val response:MutableLiveData<Response> = MutableLiveData()

    fun getData(userName:String,password:String)
    {
        val mApiService = Utils.interfaceService

        compositeDisposable.add(mApiService.loginUser(userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    response.value= Response.loading(ApiType.LOGIN_USER)
                }
                .subscribe(
                        {it: LoginResult -> response.value = Response.success(ApiType.LOGIN_USER,it) },
                        { throwable:Throwable -> response.value = Response.error(ApiType.LOGIN_USER,throwable) }
                ))
    }
}