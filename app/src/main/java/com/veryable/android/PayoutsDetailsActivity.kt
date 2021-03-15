package com.veryable.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.veryable.android.databinding.ActivityPayoutsDetailsBinding
import com.veryable.android.PayoutsListActivity
class PayoutsDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPayoutsDetailsBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
//    private val payoutsListActivity : PayoutsListActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_details)


        val accountName : TextView = findViewById(R.id.accountNameTextView)
        val accountDetails : TextView = findViewById(R.id.accountDetailsTextView)
        accountName.setText("Wells Fargo")
        accountDetails.setText("1234")

    }

}