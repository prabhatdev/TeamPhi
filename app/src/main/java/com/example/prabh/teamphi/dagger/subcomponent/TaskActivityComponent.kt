package com.example.prabh.teamphi.dagger.subcomponent

import com.example.prabh.teamphi.dagger.scope.TaskActivityScope
import com.example.prabh.teamphi.dagger.submodule.TaskActivityModule
import com.example.prabh.teamphi.mvvm.activity.Task.TaskActivity
import dagger.Subcomponent

@TaskActivityScope
@Subcomponent(modules = [TaskActivityModule::class])
interface TaskActivityComponent
{
    fun inject(target: TaskActivity)
}