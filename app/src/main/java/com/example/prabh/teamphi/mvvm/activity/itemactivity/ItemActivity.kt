package com.example.prabh.teamphi.mvvm.activity.itemactivity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.activity.AddTaskActivity.AddItemActivity
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.retrofit.model.ItemsResult
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Session
import com.example.prabh.teamphi.utility.Status
import kotlinx.android.synthetic.main.activity_item.*
import javax.inject.Inject


class ItemActivity : TeamPhiApplication() {

    @Inject
    lateinit var itemActivityViewModel: ItemActivityViewModel

    private var taskId = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        itemActivityComponent.inject(this)
        val intent = getIntent()
        taskId = intent.getStringExtra("TASK_ID")
        initialise()
        add_items.setOnClickListener {
            val intent= Intent(this, AddItemActivity::class.java)
            intent.putExtra("TASK_ID",taskId)
            startActivity(intent)
        }
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
                processResult(response)
                progressDialog.dismiss()
            }
            Status.ERROR -> {
                Toast.makeText(this, response.error.toString(), Toast.LENGTH_LONG).show()
                progressDialog.dismiss()
            }
            Status.LOADING -> {
                progressDialog.setMessage("Loading...")
                progressDialog.setCancelable(false)
                progressDialog.show()
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

    private fun getItems() {
        itemActivityViewModel.getItems(session.getStringValue(Session.TOKEN), taskId)
    }
}
