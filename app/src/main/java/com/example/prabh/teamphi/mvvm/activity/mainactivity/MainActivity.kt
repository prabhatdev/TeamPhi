package com.example.prabh.teamphi.mvvm.activity.mainactivity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.activity.Bills.BillActivity
import com.example.prabh.teamphi.mvvm.activity.RegisterActivity.RegisterActivity
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
        register.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        login.setOnClickListener{
            initialise()
        }
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
                Toast.makeText(this,"Invalid Id Password",Toast.LENGTH_LONG).show()
            }
            Status.LOADING -> {
                Log.v("Login","Loading")
            }
        }
    }

    private fun processResult(response: Response) {
        val intent = Intent(this,BillActivity::class.java)
        startActivity(intent)
    }

    private fun getInterest() {
        mainActivityViewModel.getData(username.text.toString(),password.text.toString())

    }
}
