package com.example.prabh.teamphi.mvvm.application

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import com.example.prabh.teamphi.dagger.component.ActivityComponent
import com.example.prabh.teamphi.dagger.component.DaggerActivityComponent
import com.example.prabh.teamphi.dagger.module.ActivityModule
import com.example.prabh.teamphi.dagger.subcomponent.MainActivityComponent
import com.example.prabh.teamphi.dagger.subcomponent.RegisterActivityComponent
import com.example.prabh.teamphi.dagger.subcomponent.TaskActivityComponent
import com.example.prabh.teamphi.dagger.submodule.MainActivityModule
import com.example.prabh.teamphi.dagger.submodule.RegisterActivityModule
import com.example.prabh.teamphi.dagger.submodule.TaskActivityModule

abstract class TeamPhiApplication : AppCompatActivity() {


    private val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent
                .builder()
                .activityModule(ActivityModule(this))
                .build()
    }

    val mainActivityComponent: MainActivityComponent by lazy {
        activityComponent.plusMainActivityComponent(MainActivityModule())
    }

    val registerActivityComponent: RegisterActivityComponent by lazy {
        activityComponent.plusRegisterActivityComponent(RegisterActivityModule())
    }

    val taskActivityComponent: TaskActivityComponent by lazy {
        activityComponent.plusTaskActivityComponent(TaskActivityModule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        activityComponent.inject(this)
    }

}