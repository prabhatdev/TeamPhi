package com.example.prabh.teamphi.mvvm.activity.RegisterActivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.teamphi.retrofit.model.RegisterUser
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.Util


class RegisterActivityViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()
    val response: MutableLiveData<Response> = MutableLiveData()

    fun registerUser(name:String,designation:String,email:String,password:String)
    {
        val mApiService= Utils.interfaceService
        compositeDisposable.add(mApiService.registerUser(email,name,designation,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    response.value = Response.loading(ApiType.REGISTER_USER)
                }
                .subscribe(
                        {it:RegisterUser -> response.value= Response.success(ApiType.REGISTER_USER,it)},
                        {throwable: Throwable? -> response.value= Response.error(ApiType.REGISTER_USER, throwable!!) }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}