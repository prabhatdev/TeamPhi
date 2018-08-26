package com.example.prabh.teamphi.mvvm.activity.AddTaskActivity

import android.app.DatePickerDialog
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.prabh.teamphi.R
import com.example.prabh.teamphi.mvvm.activity.itemactivity.ItemActivity
import com.example.prabh.teamphi.mvvm.application.TeamPhiApplication
import com.example.prabh.teamphi.retrofit.model.AddItem
import com.example.prabh.teamphi.retrofit.model.ItemType
import com.example.prabh.teamphi.utility.ApiType
import com.example.prabh.teamphi.utility.Response
import com.example.prabh.teamphi.utility.Session
import com.example.prabh.teamphi.utility.Status
import kotlinx.android.synthetic.main.activity_add_item.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AddItemActivity : TeamPhiApplication(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var addItemActivityViewModel: AddItemActivityViewModel

    private val itemTypeList = ArrayList<String>()

    private var itemType = String()

    private var purchaseDate = String()

    private var taskId = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        addItemComponent.inject(this)
        val intent = getIntent()
        taskId = intent.getStringExtra("TASK_ID")
        submit_items.setOnClickListener {
            addItemActivityViewModel.sendItems(session.getStringValue(Session.TOKEN), session.getStringValue(Session.USERNAME),
                    taskId, get_item_name.text.toString(), itemType, item_price.text.toString().toDouble(), get_quantity.text.toString().toInt(),
                    purchaseDate, bill_image.text.toString())
        }
        initialise()
    }

    private fun initialise() {
        getItemType()
        observeResponse()
        datePicker()
    }

    private fun datePicker() {
        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            purchaseDate = sdf.format(cal.time)
            showToast(purchaseDate)
            get_date_text.text = purchaseDate
        }
        calendar.setOnClickListener {
            DatePickerDialog(this@AddItemActivity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun observeResponse() {
        addItemActivityViewModel.response.observe(this, Observer {
            processResponse(it)
        })
    }

    private fun processResponse(response: Response?) {
        when (response?.status) {
            Status.SUCCESS -> {
                processResult(response)
                progressDialog.dismiss()
            }
            Status.ERROR -> {
                showToast(response.error.toString())
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
            ApiType.GET_ITEM_TYPE -> {
                val itemType = response.result as ItemType
                setSpinner(itemType.data)
            }
            ApiType.ADD_ITEM -> {
                val addItem = response.result as AddItem
                if (addItem.status == "Ok" && addItem.data?.message == "Ok") {
                    showToast("Item Successfully Added")
                    val intent = Intent(this, ItemActivity::class.java)
                    startActivity(intent)
                } else {
                    showToast(addItem.data?.message!!)
                }
            }
            else -> {
            }
        }
    }

    private fun setSpinner(data: ArrayList<String>?) {
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        item_type.adapter = arrayAdapter
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        itemType = itemTypeList[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        itemType = itemTypeList[0]
    }

    private fun getItemType() {
        addItemActivityViewModel.getItemType(session.getStringValue(Session.TOKEN))
    }
}
