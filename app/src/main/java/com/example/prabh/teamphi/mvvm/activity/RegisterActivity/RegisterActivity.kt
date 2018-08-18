package com.example.prabh.teamphi.mvvm.activity.RegisterActivity

import android.os.Bundle
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication

class RegisterActivity : TeamPhiApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}
