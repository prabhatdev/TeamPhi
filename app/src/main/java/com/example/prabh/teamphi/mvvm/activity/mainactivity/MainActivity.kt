package com.example.prabh.teamphi.mvvm.activity.mainactivity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.activity.registeractivity.RegisterActivity
import com.example.prabh.teamphi.mvvm.activity.taskactivity.TaskActivity
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.retrofit.model.LoginResult
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Session
import com.example.prabh.teamphi.utility.Status
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : TeamPhiApplication() {


    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityComponent.inject(this)
        initialise()
        if (session.isLoggedIn()) {
            username.setText(session.getStringValue(Session.USERNAME))
            password.setText(session.getStringValue(Session.PASSWORD))
            if(isConnected())
            getTask()
        }

        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            if (isConnected()) {
                getTask()
            }
        }
    }

    private fun initialise() {
        observeResponse()
    }

    private fun observeResponse() {
        mainActivityViewModel.response.observe(this, Observer { it ->
            processResponse(it)
        })
    }

    private fun processResponse(response: Response?) {
        when (response!!.status) {
            Status.SUCCESS -> {
                Log.v("Login", "Api Call Successful")
                progressDialog.dismiss()
                processResult(response)

            }
            Status.ERROR -> {
                Toast.makeText(this, response.error.toString(), Toast.LENGTH_LONG).show()
            }
            Status.LOADING -> {
                Log.v("Login", "Loading")
                progressDialog.setMessage("Logging in..")
                progressDialog.setCancelable(false)
                progressDialog.show()
            }
        }
    }

    private fun processResult(response: Response) {
        when (response.apiType) {
            ApiType.LOGIN_USER -> {
                val loginResult = response.result as LoginResult
                if (loginResult.status == "Ok") {
                    session.saveLoginDetails(loginResult.data?.token!!, loginResult.data.userName!!, loginResult.data.isAdmin!!, loginResult.data.expiresOn!!,password.text.toString())
                    session.setIsLogedIn(true)
                    val intent = Intent(this, TaskActivity::class.java)
                    startActivity(intent)
                } else if(loginResult.status == "Failed"){
                    showToast("Incorrect Id and Password")
                }
            }
        }
    }

    private fun getTask() {
        mainActivityViewModel.getData(username.text.toString(), password.text.toString())

    }
}
