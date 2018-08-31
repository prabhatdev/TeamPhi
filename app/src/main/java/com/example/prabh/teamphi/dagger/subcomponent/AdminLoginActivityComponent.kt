package com.example.prabh.teamphi.dagger.subcomponent

import com.example.prabh.teamphi.dagger.scope.AdminLoginActivityScope
import com.example.prabh.teamphi.dagger.submodule.AdminLoginActivityModule
import com.example.prabh.teamphi.mvvm.activity.adminactivity.AdminLoginActivity
import dagger.Subcomponent

@AdminLoginActivityScope
@Subcomponent(modules = [AdminLoginActivityModule::class])
interface AdminLoginActivityComponent {
    fun inject(target: AdminLoginActivity)
}