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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        /* removing the title bar on top of this activity */

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

        val url = "https://veryable-public-assets.s3.us-east-2.amazonaws.com/veryable.json"

        var activityItem = ActivityModel("","","",0)
        val queue = Volley.newRequestQueue(this)

        /* Request a JSON response from the provided URL using Volley and parse the JSON response. */
        val request = JsonArrayRequest(
            com.android.volley.Request.Method.GET,url,null,
            { response ->
                try {
                    for (i in 0 until response.length()) {
                        val jsonArray = response.getJSONObject(i)
                        val id = jsonArray["id"]
                        val accountType = jsonArray["account_type"]
                        val accountName = jsonArray["account_name"]
                        val desc = jsonArray["desc"]
                        Log.i("Accounttype", accountType.toString())
                        var acitivityItem = ActivityModel(accountType as String?,accountName as String?, desc as String?, id as Int?)
                        activityList.add(acitivityItem)
                    }
                    /* notify the adapter that the data has been changed or updated, this makes the recyclerview reload the cards */
                    activityAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error -> error.printStackTrace() })
        queue.add(request)
        queue.start()
    }

    public fun onItemClickHandler(position:Int){
        Log.d("***","${position}");
        val intentToPayoutDetails = Intent(this, PayoutsDetailsActivity::class.java)
        intentToPayoutDetails.putExtra("position", position)
        Log.i("",activityList[position].toString())
        startActivity(intentToPayoutDetails)
        //here you can start a new intent to open a new activity on click of item

    }
}