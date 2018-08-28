package com.example.prabh.teamphi.mvvm.activity.taskactivity

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.activity.mainactivity.MainActivity
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.retrofit.model.AddTask
import com.example.prabh.teamphi.retrofit.model.TaskUser
import com.example.prabh.teamphi.retrofit.model.Tasks
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Session
import com.example.prabh.teamphi.utility.Status
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.activity_task.*
import javax.inject.Inject

class TaskActivity : TeamPhiApplication(), SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        swipeRefreshLayoutTask.isRefreshing=true
        getAllTasks()
    }

    @Inject
    lateinit var taskActivityViewModel: TaskActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        taskActivityComponent.inject(this)
        onClickListeners()
        swipeRefreshLayoutTask?.let {
            swipeRefreshLayoutTask?.setOnRefreshListener(this)
        }
        initialise()
    }

    override fun onResume() {
        super.onResume()
        onRefresh()
    }

    private fun onClickListeners() {
        add_task.setOnClickListener {
            val builder = AlertDialog.Builder(this@TaskActivity)
            builder.setTitle("Task Name")
            val taskName = EditText(this)
            taskName.hint = "Enter Task Name"
            builder.setView(taskName)
            builder.setPositiveButton("Submit") { dialog, which ->
                if (taskName.text.isEmpty()) {
                    showToast("Task Name cannot be Empty")
                } else {
                    addTask(taskName.text.toString())
                }
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
            }
            val dialogBox = builder.create()
            dialogBox.show()
        }
        logout_register.setOnClickListener {
            val builder = AlertDialog.Builder(this@TaskActivity)
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
        getAllTasks()
        observeTask()
    }

    private fun addTask(taskName: String) {
        taskActivityViewModel.addTask(session.getStringValue(Session.TOKEN), taskName, session.getStringValue(Session.USERNAME))
    }

    private fun observeTask() {
        taskActivityViewModel.response.observe(this, Observer { it ->
            processResponse(it)
        })

    }

    private fun processResponse(response: Response?) {
        when (response!!.status) {
            Status.SUCCESS -> {
                Log.v("Register", "API call successful")
                processResult(response)
                swipeRefreshLayoutTask.isRefreshing=false
            }
            Status.ERROR -> {
                Toast.makeText(this, response.error.toString(), Toast.LENGTH_LONG).show()
                swipeRefreshLayoutTask.isRefreshing=false
            }
            Status.LOADING -> {
                Log.v("Register", "API Loading")
            }
        }
    }

    private fun processResult(response: Response) {
        when (response.apiType) {
            ApiType.GET_TASK -> {
                val taskUser = response.result as TaskUser
                if (taskUser.error != null) {
                    task_recycler_view.layoutManager = LinearLayoutManager(this)
                    task_recycler_view.adapter = TaskAdapter(taskUser.data?.tasks as ArrayList<Tasks>)
                } else
                    Toast.makeText(this, taskUser.error, Toast.LENGTH_LONG).show()
            }
            ApiType.ADD_TASK -> {
                val addTask = response.result as AddTask
                if (addTask.status == "Ok") {
                    showToast("Task Added")
                    getAllTasks()
                } else if (addTask.status == null) {
                    showToast("Invalid Token")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            else -> {
            }
        }
    }

    private fun getAllTasks() {
        taskActivityViewModel.getTask(session.getStringValue(Session.TOKEN))
    }
}
