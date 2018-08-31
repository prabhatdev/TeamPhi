package com.example.prabh.teamphi.mvvm.application

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.widget.Toast
import com.example.prabh.teamphi.dagger.component.ActivityComponent
import com.example.prabh.teamphi.dagger.component.DaggerActivityComponent
import com.example.prabh.teamphi.dagger.module.ActivityModule
import com.example.prabh.teamphi.dagger.subcomponent.*
import com.example.prabh.teamphi.dagger.submodule.*
import com.example.prabh.teamphi.utility.Session
import com.example.prabh.teamphi.utility.Utils
import javax.inject.Inject

abstract class TeamPhiApplication : AppCompatActivity() {


    private val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent
                .builder()
                .activityModule(ActivityModule(this))
                .build()
    }

    @Inject
    lateinit var session: Session

    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var progressDialog:ProgressDialog

    val mainActivityComponent: MainActivityComponent by lazy {
        activityComponent.plusMainActivityComponent(MainActivityModule())
    }

    val registerActivityComponent: RegisterActivityComponent by lazy {
        activityComponent.plusRegisterActivityComponent(RegisterActivityModule())
    }

    val taskActivityComponent: TaskActivityComponent by lazy {
        activityComponent.plusTaskActivityComponent(TaskActivityModule())
    }

    val addItemComponent: AddItemActivityComponent by lazy {
        activityComponent.plusAddItemActivityComponent(AddItemActivityModule())
    }

    val itemActivityComponent:ItemActivityComponent by lazy {
        activityComponent.plusItemActivityComponent(ItemActivityModule())
    }

    val adminLoginActivityComponent:AdminLoginActivityComponent by lazy {
        activityComponent.plusAdminLoginActivityComponent(AdminLoginActivityModule())
    }

    fun isConnected() = utils.isInternetAvailable(this)

    fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) = Utils.showToast(this, message, length)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        activityComponent.inject(this)
    }

}