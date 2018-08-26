package com.example.prabh.teamphi.dagger.component

import com.example.prabh.teamphi.dagger.module.ActivityModule
import com.example.prabh.teamphi.dagger.subcomponent.*
import com.example.prabh.teamphi.dagger.submodule.*
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ActivityModule::class])
interface ActivityComponent{
    fun inject(target:TeamPhiApplication)

    fun plusMainActivityComponent(mainActivityModule: MainActivityModule) : MainActivityComponent

    fun plusRegisterActivityComponent(registerActivityModule: RegisterActivityModule) : RegisterActivityComponent

    fun plusTaskActivityComponent(taskActivityModule: TaskActivityModule):TaskActivityComponent

    fun plusAddItemActivityComponent(addItemActivityModule: AddItemActivityModule):AddItemActivityComponent

    fun plusItemActivityComponent(itemActivityModule: ItemActivityModule):ItemActivityComponent
}