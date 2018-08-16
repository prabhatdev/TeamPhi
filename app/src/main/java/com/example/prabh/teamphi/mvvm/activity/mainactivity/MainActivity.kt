package com.example.prabh.teamphi.mvvm.activity.mainactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import javax.inject.Inject

class MainActivity : TeamPhiApplication() {


    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityComponent.inject(this)
        initialise()
    }
    private fun initialise()
    {


    }
}
