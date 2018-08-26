package com.example.prabh.teamphi.dagger.subcomponent

import com.example.prabh.teamphi.dagger.scope.ItemActivityScope
import com.example.prabh.teamphi.dagger.submodule.AddItemActivityModule
import com.example.prabh.teamphi.dagger.submodule.ItemActivityModule
import com.example.prabh.teamphi.mvvm.activity.AddTaskActivity.AddItemActivity
import com.example.prabh.teamphi.mvvm.activity.itemactivity.ItemActivity
import dagger.Subcomponent

@ItemActivityScope
@Subcomponent(modules = [ItemActivityModule::class])
interface ItemActivityComponent {
    fun inject(target: ItemActivity)
}