package com.example.prabh.teamphi.dagger.submodule

import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.teamphi.dagger.scope.ItemActivityScope
import com.example.prabh.teamphi.mvvm.activity.itemactivity.ItemActivityViewModel
import com.example.prabh.teamphi.mvvm.activity.itemactivity.ItemActivityViewModelFactory
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import dagger.Module
import dagger.Provides

@Module
class ItemActivityModule {

    @Provides
    @ItemActivityScope
    fun provideItemActivityViewModelFactory():ItemActivityViewModelFactory= ItemActivityViewModelFactory()

    @Provides
    @ItemActivityScope
    fun provideItemActivityViewModel(application: TeamPhiApplication,itemActivityViewModelFactory: ItemActivityViewModelFactory): ItemActivityViewModel=
            ViewModelProviders.of(application,itemActivityViewModelFactory).get(ItemActivityViewModel::class.java)
}