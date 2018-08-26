package com.example.prabh.teamphi.dagger.subcomponent

import com.example.prabh.teamphi.dagger.scope.RegisterActivityScope
import com.example.prabh.teamphi.dagger.submodule.RegisterActivityModule
import com.example.prabh.teamphi.mvvm.activity.registeractivity.RegisterActivity
import dagger.Subcomponent


@RegisterActivityScope
@Subcomponent(modules = [RegisterActivityModule::class])
interface RegisterActivityComponent
{
    fun inject(target: RegisterActivity)
}