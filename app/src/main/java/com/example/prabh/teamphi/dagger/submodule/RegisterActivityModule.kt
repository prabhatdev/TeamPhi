package com.example.prabh.teamphi.dagger.submodule

import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.teamphi.dagger.scope.RegisterActivityScope
import com.example.prabh.teamphi.mvvm.activity.registeractivity.RegisterActivityViewModel
import com.example.prabh.teamphi.mvvm.activity.registeractivity.RegisterActivityViewModelFactory
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import dagger.Module
import dagger.Provides

@Module
class RegisterActivityModule
{
    @Provides
    @RegisterActivityScope
    fun provideRegisterActivityViewModelFactory(): RegisterActivityViewModelFactory = RegisterActivityViewModelFactory()

    @Provides
    @RegisterActivityScope
    fun provideRegisterActivityViewModel(application: TeamPhiApplication,registerActivityViewModelFactory: RegisterActivityViewModelFactory): RegisterActivityViewModel=
            ViewModelProviders.of(application,registerActivityViewModelFactory).get(RegisterActivityViewModel::class.java)
}