package com.example.prabh.teamphi.dagger.submodule

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.teamphi.dagger.scope.MainActivityScope
import com.example.prabh.teamphi.mvvm.activity.mainactivity.MainActivityViewModel
import com.example.prabh.teamphi.mvvm.activity.mainactivity.MainActivityViewModelFactory
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun provideMainActivityViewModelFactory(): MainActivityViewModelFactory= MainActivityViewModelFactory()

    @Provides
    @MainActivityScope
    fun provideMainActivityViewModel(application: TeamPhiApplication, mainActivityViewModelFactory: MainActivityViewModelFactory):MainActivityViewModel=
            ViewModelProviders.of(application,mainActivityViewModelFactory).get(MainActivityViewModel::class.java)
}