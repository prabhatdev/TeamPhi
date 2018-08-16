package com.example.prabh.teamphi.dagger.component

import com.example.prabh.teamphi.dagger.module.ActivityModule
import com.example.prabh.teamphi.dagger.subcomponent.MainActivityComponent
import com.example.prabh.teamphi.dagger.submodule.MainActivityModule
import com.example.prabh.teamphi.mvvm.activity.mainactivity.MainActivityViewModel
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ActivityModule::class])
interface ActivityComponent{
    fun inject(target:TeamPhiApplication)

    fun plusMainActivityComponent(mainActivityModule: MainActivityModule) : MainActivityComponent
}