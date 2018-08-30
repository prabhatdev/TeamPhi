package com.example.prabh.teamphi.mvvm.activity.itemactivity

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.activity.AddItemActivity.AddItemActivity
import com.example.prabh.teamphi.mvvm.activity.mainactivity.MainActivity
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.retrofit.model.ItemsResult
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Session
import com.example.prabh.teamphi.utility.Status
import kotlinx.android.synthetic.main.activity_add_item.*
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.activity_task.*
import javax.inject.Inject


class ItemActivity : TeamPhiApplication(),SwipeRefreshLayout.OnRefreshListener {
    @Inject
    lateinit var itemActivityViewModel: ItemActivityViewModel

    private var taskId = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        itemActivityComponent.inject(this)
        val intent = getIntent()
        taskId = intent.getStringExtra("TASK_ID")!!
        initialise()
        onClickListeners()
    }

    private fun onClickListeners() {
        swipeRefreshLayoutItem?.let {
            swipeRefreshLayoutItem?.setOnRefreshListener(this)
        }
        add_items.setOnClickListener {
            val intent= Intent(this, AddItemActivity::class.java)
            intent.putExtra("TASK_ID",taskId)
            startActivity(intent)
            finish()
        }
        logout_items.setOnClickListener {
            val builder = AlertDialog.Builder(this@ItemActivity)
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

    override fun onRefresh() {
        swipeRefreshLayoutItem.isRefreshing=true
        getItems()
    }

    private fun initialise() {
        getItems()
        observeResponse()
    }

    private fun observeResponse() {
        itemActivityViewModel.response.observe(this, Observer {
            processResponse(it)
        })

    }

    private fun processResponse(response: Response?) {
        when (response!!.status) {
            Status.SUCCESS -> {
                swipeRefreshLayoutItem.isRefreshing=false
                processResult(response)
            }
            Status.ERROR -> {
                showToast("Re-logging in due to Ip Change")
                swipeRefreshLayoutItem.isRefreshing=false
                startActivity(Intent(this,MainActivity::class.java))
            }
            Status.LOADING -> {
                Log.v("Items","Loading..")
            }
        }
    }

    private fun processResult(response: Response) {
        when (response.apiType) {
            ApiType.GET_ITEM -> {
                val itemsResult = response.result as ItemsResult
                if (itemsResult.status == "Ok") {
                    if (itemsResult.data?.size == 0) {
                        showToast("No Items Have Been Added Yet")
                    } else {
                        items_recycler.layoutManager = LinearLayoutManager(this)
                        items_recycler.adapter = ItemsAdapter(itemsResult.data!!)
                    }
                }
            }
            else -> {
            }
        }
    }

    override fun onResume() {
        super.onResume()
        onRefresh()
    }

    private fun getItems() {
        itemActivityViewModel.getItems(session.getStringValue(Session.TOKEN), taskId)
    }
}
