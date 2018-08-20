package com.example.prabh.teamphi.dagger.module

import android.content.Context
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.utility.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private val teamPhiApplication: TeamPhiApplication) {

    @Provides
    @Singleton
    fun provideContext() : Context=teamPhiApplication

    @Provides
    @Singleton
    fun provideteamPhiApplication() : TeamPhiApplication=teamPhiApplication

    @Provides
    @Singleton
    fun provideUtil(context: Context): Utils = Utils.provideUtil(context)


}