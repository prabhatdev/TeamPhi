package com.example.prabh.teamphi.mvvm.activity.AddTaskActivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.prabh.teamphi.retrofit.model.AddItem
import com.example.prabh.teamphi.retrofit.model.ItemType
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AddItemActivityViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val response: MutableLiveData<Response> = MutableLiveData()

    fun getItemType(token: String) {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.getItemType(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    response.value = Response.loading(ApiType.GET_ITEM_TYPE)
                }
                .subscribe(
                        { it: ItemType -> response.value = Response.success(ApiType.GET_ITEM_TYPE, it) },
                        { throwable: Throwable? -> response.value = Response.error(ApiType.GET_ITEM_TYPE, throwable!!) }
                ))
    }

    fun sendItems(token: String, userName: String, taskId: String, itemName: String, itemType: String, price: Double, quantity: Int, purchaseDate: String, billImageURL: String) {
        val mApiService = Utils.interfaceService
        compositeDisposable.add(mApiService.sendItem(token,taskId,itemName,itemType,price,quantity,purchaseDate,billImageURL,userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    response.value = Response.loading(ApiType.ADD_ITEM)
                }
                .subscribe(
                        { it: AddItem -> response.value = Response.success(ApiType.ADD_ITEM, it) },
                        { throwable: Throwable? -> response.value = Response.error(ApiType.ADD_ITEM, throwable!!) }
                ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}