package com.veryable.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.*
import com.veryable.android.databinding.ActivityPayoutsListBindingImpl
import com.veryable.android.models.ActivityModel
import org.json.JSONException


class PayoutsListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPayoutsListBindingImpl
    private lateinit var linearLayoutManager: LinearLayoutManager

/* create a list using the Model class and instantiate the adapter*/
    val activityList = ArrayList<ActivityModel>()
    private lateinit var activityAdapter: ActivityAdapter
    var restCallCount : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_list)

        /* add a layout manager and add the data from the activityList to the adapter*/
        linearLayoutManager = LinearLayoutManager(this)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        activityAdapter = ActivityAdapter(activityList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = activityAdapter

        /* call the function which makes the REST call and populates the activityList with data */
        prepareActivityList()
    }


    private fun prepareActivityList() {

        val url = getString(R.string.PAYOUTS_DATA_JSON_URL)

        var activityItem = ActivityModel("","","",0)
        val queue = Volley.newRequestQueue(this)

        /* Request a JSON response from the provided URL using Volley and parse the JSON response. */
        val request = JsonArrayRequest(
            com.android.volley.Request.Method.GET,url,null,
            { response ->
                try {
                    for (i in 0 until response.length()) {
                        val jsonArray = response.getJSONObject(i)
                        val id = jsonArray[getString(R.string.ACCOUNT_ID)]
                        val accountType = jsonArray[getString(R.string.ACCOUNT_TYPE_JSON)]
                        val accountName = jsonArray[getString(R.string.ACCOUNT_NAME_JSON)]
                        val desc = jsonArray[getString(R.string.ACCOUNT_DESC)]
                        var acitivityItem = ActivityModel(accountType as String?,accountName as String?, desc as String?, id as Int?)
                        activityList.add(acitivityItem)
                    }
                    /* notify the adapter that the data has been changed or updated, this makes the recyclerview reload the cards */
                    activityAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                    if (restCallCount < 3){
                        restCallCount += 1
                        prepareActivityList()
                    }
                }
            },
            { error -> error.printStackTrace()
                if (restCallCount < 3) {
                    restCallCount += 1
                    prepareActivityList()
                }
                })
        queue.add(request)
        queue.start()
    }
}