package com.example.prabh.teamphi.dagger.submodule

import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.teamphi.dagger.scope.TaskActivityScope
import com.example.prabh.teamphi.mvvm.activity.Task.TaskActivityVIewModelFactory
import com.example.prabh.teamphi.mvvm.activity.Task.TaskActivityViewModel
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import dagger.Module
import dagger.Provides

@Module
class TaskActivityModule
{
    @Provides
    @TaskActivityScope
    fun provideTaskActivityViewModelFactory():TaskActivityVIewModelFactory= TaskActivityVIewModelFactory()

    @Provides
    @TaskActivityScope
    fun provideTaskActivityViewModel(application: TeamPhiApplication,taskActivityVIewModelFactory: TaskActivityVIewModelFactory):TaskActivityViewModel=
            ViewModelProviders.of(application,taskActivityVIewModelFactory).get(TaskActivityViewModel::class.java)
}