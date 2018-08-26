package com.example.prabh.teamphi.dagger.subcomponent

import com.example.prabh.teamphi.dagger.scope.AddItemActivityScope
import com.example.prabh.teamphi.dagger.submodule.AddItemActivityModule
import com.example.prabh.teamphi.mvvm.activity.AddTaskActivity.AddItemActivity
import dagger.Subcomponent

@AddItemActivityScope
@Subcomponent(modules = [AddItemActivityModule::class])
interface AddItemActivityComponent {
    fun inject(target: AddItemActivity)
}