package com.example.prabh.teamphi.mvvm.activity.Task

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.retrofit.model.TaskUser
import com.example.prabh.teamphi.retrofit.model.Tasks
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Status
import kotlinx.android.synthetic.main.activity_task.*
import javax.inject.Inject

class TaskActivity : TeamPhiApplication() {

    @Inject
    lateinit var taskActivityViewModel: TaskActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        taskActivityComponent.inject(this)
        initialise()

    }

    private fun initialise() {
        getAllTasks()
        observeTask()
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
            }
            Status.ERROR -> {
                Toast.makeText(this, "Unknown Error Occurred", Toast.LENGTH_SHORT).show()
            }
            Status.LOADING -> {
                Log.v("Register", "API Loading")
            }
        }
    }

    private fun processResult(response: Response) {
        val taskUser=response.result as TaskUser
        if(taskUser.error!=null)
        {
            task_recycler_view.layoutManager=LinearLayoutManager(this)
            task_recycler_view.adapter=TaskAdapter(taskUser.data?.tasks as ArrayList<Tasks>)
        }
        else
            Toast.makeText(this,taskUser.error,Toast.LENGTH_LONG).show()
    }

    private fun getAllTasks() {
        taskActivityViewModel.getTask("NjM2NzA2NjM0ODk2NjQxODk0LllXUmhjbk5vY0RZeE9FQm5iV0ZwYkM1amIyMD0uMGM2MjEyMWZkMDIzMjBiZWUzZDE2MTI2MmEwMzRhNjRjNTU3ZWFlMzkyNTA3Zjk5OWJhYjdmYWExOGMxYzViMC42MzY3MzI1NTQ4OTY2NDE4OTQ=")
    }
}
