package com.example.prabh.teamphi.dagger.submodule

import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.teamphi.dagger.scope.AddItemActivityScope
import com.example.prabh.teamphi.mvvm.activity.AddItemActivity.AddItemActivityViewModel
import com.example.prabh.teamphi.mvvm.activity.AddItemActivity.AddItemActivityViewModelFactory
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import dagger.Module
import dagger.Provides

@Module
class AddItemActivityModule {
    @Provides
    @AddItemActivityScope
    fun provideAddItemActivityViewModelFactory(): AddItemActivityViewModelFactory = AddItemActivityViewModelFactory()

    @Provides
    @AddItemActivityScope
    fun provideAddItemActivityViewModel(application: TeamPhiApplication, addItemActivityViewModelFactory: AddItemActivityViewModelFactory): AddItemActivityViewModel =
            ViewModelProviders.of(application, addItemActivityViewModelFactory).get(AddItemActivityViewModel::class.java)
}