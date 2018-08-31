package com.example.prabh.teamphi.mvvm.activity.adminactivity

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.activity.mainactivity.MainActivity
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.retrofit.model.ApproveItem
import com.example.prabh.teamphi.retrofit.model.VerifyItem
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Session
import com.example.prabh.teamphi.utility.Status
import kotlinx.android.synthetic.main.activity_admin_login.*
import javax.inject.Inject

class AdminLoginActivity : TeamPhiApplication(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        getItems()
    }

    @Inject
    lateinit var adminLoginActivityViewModel: AdminLoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        adminLoginActivityComponent.inject(this)
        onClickListeners()
        initialise()

    }

    private fun observeResponse() {
        adminLoginActivityViewModel.response.observe(this, Observer {
            processResponse(it)
        })

    }

    private fun processResponse(response: Response?) {
        when (response!!.status) {
            Status.SUCCESS -> {
                swipeRefreshLayoutItemVerify.isRefreshing = false
                processResult(response)
            }
            Status.ERROR -> {
                showToast("Re-logging in due to Ip Changed")
                swipeRefreshLayoutItemVerify.isRefreshing = false
                startActivity(Intent(this, MainActivity::class.java))
            }
            Status.LOADING -> {
                Log.v("Items", "Loading..")
            }
        }
    }

    private fun processResult(response: Response) {
        when (response.apiType) {
            ApiType.GET_ITEM_VERIFICATION -> {
                val itemsResult = response.result as VerifyItem
                item_verify_recycler_view.layoutManager = LinearLayoutManager(this)
                item_verify_recycler_view.adapter = VerifyItemsAdapter(itemsResult.data?.item!!, this@AdminLoginActivity)
            }

            ApiType.APPROVE_ITEM -> {
                val approveItemResult = response.result as ApproveItem
                if (approveItemResult.data == "Ok" && approveItemResult.status == "Ok") {
                    showToast("Item Approved.")
                    onRefresh()
                } else {
                    showToast(approveItemResult.status!!)
                }
            }
            else -> {
            }
        }
    }

    private fun onClickListeners() {
        swipeRefreshLayoutItemVerify?.let {
            swipeRefreshLayoutItemVerify?.setOnRefreshListener(this)
        }
        logout_approve_item.setOnClickListener {
            val builder = AlertDialog.Builder(this@AdminLoginActivity)
            builder.setTitle("Are you sure you want to logout?")
            builder.setPositiveButton("Yes") { dialog, which ->
                session.setIsLogedIn(false)
                val intent=Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            builder.setNegativeButton("No") { dialog, which ->
            }
            val dialogBox = builder.create()
            dialogBox.show()
        }
    }

    private fun initialise() {
        getItems()
        observeResponse()
    }

    private fun getItems() {
        adminLoginActivityViewModel.getItemsForVerification(session.getStringValue(Session.TOKEN), session.getStringValue(Session.USERNAME))
    }
}
