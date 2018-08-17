package com.example.prabh.teamphi.mvvm.activity.mainactivity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.retrofit.model.LoginResult
import com.example.prabh.teamphi.utility.Response
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
    }
    private fun initialise()
    {
        getInterest()
        observeResponse()
    }

    private fun observeResponse() {
        mainActivityViewModel.response.observe(this, Observer { it ->
            processResponse(it)
        })
    }

    private fun processResponse(response: Response?) {
        when (response!!.status){
            Status.SUCCESS -> {
                Log.v("Login","Api Call Successful")
                processResult(response)
            }
            Status.ERROR -> {
                Toast.makeText(this,"No net",Toast.LENGTH_LONG).show()
            }
            Status.LOADING -> {
                Log.v("Login","Loading")
            }
        }
    }

    private fun processResult(response: Response) {
        val loginResult= response.result as LoginResult
        login.setText(loginResult.token)

    }

    private fun getInterest() {
        mainActivityViewModel.getData("prabhat.singh374@gmail.com","helloworld")

    }
}
