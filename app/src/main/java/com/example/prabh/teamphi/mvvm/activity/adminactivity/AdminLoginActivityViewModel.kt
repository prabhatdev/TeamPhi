package com.example.prabh.teamphi.mvvm.activity.adminactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.teamphi.retrofit.model.ApproveItem
import com.example.prabh.teamphi.retrofit.model.VerifyItem
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AdminLoginActivityViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val response: MutableLiveData<Response> = MutableLiveData()

    fun getItemsForVerification(token: String, userName: String) {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.getItemVerification(token, userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    response.value = Response.loading(ApiType.GET_ITEM_VERIFICATION)
                }
                .subscribe(
                        { it: VerifyItem -> response.value = Response.success(ApiType.GET_ITEM_VERIFICATION, it) },
                        { throwable: Throwable -> response.value = Response.error(ApiType.GET_ITEM_VERIFICATION, throwable) }
                ))
    }

    fun approveItem(token: String, userName: String, taskId: String, itemId: String) {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.approveItems(token, taskId, itemId, userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    response.value = Response.loading(ApiType.APPROVE_ITEM)
                }
                .subscribe(
                        { it: ApproveItem -> response.value = Response.success(ApiType.APPROVE_ITEM, it) },
                        { throwable: Throwable -> response.value = Response.error(ApiType.APPROVE_ITEM, throwable) }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}