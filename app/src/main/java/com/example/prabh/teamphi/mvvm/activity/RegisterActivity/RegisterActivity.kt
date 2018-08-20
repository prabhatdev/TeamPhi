package com.example.prabh.teamphi.mvvm.activity.RegisterActivity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.activity.Bills.BillActivity
import com.example.prabh.teamphi.mvvm.activity.mainactivity.MainActivity
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Status
import kotlinx.android.synthetic.main.activity_main.*
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

    private fun processResponse(reponse: Response?) {
        when (reponse!!.status) {
            Status.SUCCESS -> {
                Log.v("Register", "API call successful")
                processResult()
            }
            Status.ERROR -> {
                Toast.makeText(this, "Username Already Exist", Toast.LENGTH_SHORT).show()
            }
            Status.LOADING -> {
                Log.v("Register", "API Loading")
            }
        }

    }

    private fun processResult() {
        val intent = Intent(this, MainActivity::class.java)
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
