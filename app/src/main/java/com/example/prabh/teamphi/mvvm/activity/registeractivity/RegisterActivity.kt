package com.example.prabh.teamphi.mvvm.activity.registeractivity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.activity.mainactivity.MainActivity
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.retrofit.model.RegisterUser
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Status
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : TeamPhiApplication() {

    @Inject
    lateinit var registerActivityViewModel: RegisterActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerActivityComponent.inject(this)
        request_login.setOnClickListener {
            initialise()
        }
    }

    private fun initialise() {
        registerUser()
        observeResponse()
    }

    private fun observeResponse() {
        registerActivityViewModel.response.observe(this, Observer { it ->
            processResponse(it)
        })

    }

    private fun processResponse(response: Response?) {
        when (response!!.status) {
            Status.SUCCESS -> {
                Log.v("Register", "API call successful")
                processResult(response)
            }
            Status.ERROR -> {
                processResult(response)
            }
            Status.LOADING -> {
                Log.v("Register", "API Loading")
            }
        }

    }

    private fun processResult(response: Response) {
        val intent = Intent(this, MainActivity::class.java)
        val registerUser=response.result as RegisterUser
        Toast.makeText(this,registerUser.message,Toast.LENGTH_SHORT).show()
        startActivity(intent)
        finish()
    }

    private fun registerUser() {
        if (register_password.text.toString() == confirm_password.text.toString()) {
            registerActivityViewModel.registerUser(name.text.toString(), designation.text.toString(), email.text.toString(), register_password.text.toString())
        } else
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
