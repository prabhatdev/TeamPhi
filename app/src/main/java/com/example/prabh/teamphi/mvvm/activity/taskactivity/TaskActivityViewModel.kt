package com.example.prabh.teamphi.mvvm.activity.taskactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.teamphi.retrofit.model.AddTask
import com.example.prabh.teamphi.retrofit.model.TaskUser
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class TaskActivityViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val response: MutableLiveData<Response> = MutableLiveData()

    fun getTask(token: String) {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.getAllTasks(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    response.value = Response.loading(ApiType.GET_TASK)
                }
                .subscribe(
                        { it: TaskUser -> response.value = Response.success(ApiType.GET_TASK, it) },
                        { throwable: Throwable? -> response.value = Response.error(ApiType.GET_TASK, throwable!!) }
                ))
    }

    fun addTask(token: String, taskName: String, userName:String) {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.sendTask(token, taskName, "null",userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    response.value = Response.loading(ApiType.ADD_TASK)
                }
                .subscribe(
                        { it: AddTask -> response.value = Response.success(ApiType.ADD_TASK, it) },
                        { throwable: Throwable? -> response.value = Response.error(ApiType.ADD_TASK, throwable!!) }
                ))
    }


}