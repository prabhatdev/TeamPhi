package com.example.prabh.teamphi.mvvm.activity.itemactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.teamphi.retrofit.model.ItemsResult
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ItemActivityViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val response: MutableLiveData<Response> = MutableLiveData()

    fun getItems(token:String,taskId:String)
    {
        val mApiService = Utils.interfaceService

        compositeDisposable.add(mApiService.getItems(token, taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    response.value= Response.loading(ApiType.GET_ITEM)
                }
                .subscribe(
                        {it: ItemsResult -> response.value = Response.success(ApiType.GET_ITEM,it) },
                        { throwable:Throwable -> response.value = Response.error(ApiType.GET_ITEM,throwable) }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}