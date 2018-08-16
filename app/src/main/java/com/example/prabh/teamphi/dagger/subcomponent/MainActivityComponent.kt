package com.example.prabh.teamphi.dagger.subcomponent

import com.example.prabh.teamphi.dagger.scope.MainActivityScope
import com.example.prabh.teamphi.dagger.submodule.MainActivityModule
import com.example.prabh.teamphi.mvvm.activity.mainactivity.MainActivity
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent{
    fun inject(target:MainActivity)
}