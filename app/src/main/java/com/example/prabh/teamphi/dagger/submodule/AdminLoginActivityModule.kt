package com.example.prabh.teamphi.dagger.submodule

import android.arch.lifecycle.ViewModelProviders
import com.example.prabh.teamphi.dagger.scope.AdminLoginActivityScope
import com.example.prabh.teamphi.mvvm.activity.adminactivity.AdminLoginActivityViewModel
import com.example.prabh.teamphi.mvvm.activity.adminactivity.AdminLoginActivityViewModelFactory
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import dagger.Module
import dagger.Provides

@Module
class AdminLoginActivityModule {
    @Provides
    @AdminLoginActivityScope
    fun provideAdminLoginActivityViewModelFactory(): AdminLoginActivityViewModelFactory = AdminLoginActivityViewModelFactory()

    @Provides
    @AdminLoginActivityScope
    fun provideAdminLoginActivityViewModel(application: TeamPhiApplication, AdminLoginActivityViewModelFactory: AdminLoginActivityViewModelFactory): AdminLoginActivityViewModel =
            ViewModelProviders.of(application, AdminLoginActivityViewModelFactory).get(AdminLoginActivityViewModel::class.java)
}