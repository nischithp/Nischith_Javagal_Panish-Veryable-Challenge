package com.veryable.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.veryable.android.databinding.ActivityPayoutsDetailsBinding
import com.veryable.android.PayoutsListActivity
class PayoutsDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPayoutsDetailsBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_details)

        /* instantiate all the views in the activity_payouts_list card */
        val accountNameTextView : TextView = findViewById(R.id.accountNameTextView)
        val accountDetailsTextView : TextView = findViewById(R.id.accountDetailsTextView)
        val imageView : ImageView = findViewById(R.id.cardImagePayoutDetailsImageView)
        val doneButton : Button = findViewById(R.id.button)
        val backButton : FloatingActionButton = findViewById(R.id.floatingActionButton)

        /* set on click listeners for the back button in the navbar and the done button */
        backButton.setOnClickListener{
            val intentToPayoutList = Intent(this.applicationContext, PayoutsListActivity::class.java)
            startActivity(intentToPayoutList)
        }
        doneButton.setOnClickListener{
            val intentToPayoutList = Intent(this.applicationContext, PayoutsListActivity::class.java)
            startActivity(intentToPayoutList)
        }

        /* get the intent and all the values passed */
        val bundle: Bundle? = intent.extras
        val accountName : String? = intent.getStringExtra(R.string.ACCOUNT_NAME.toString())
        val id : String? = intent.getStringExtra(R.string.ACCOUNT_ID.toString())
        val accountType : String? = intent.getStringExtra(R.string.ACCOUNT_TYPE.toString())
        val desc : String? = intent.getStringExtra(R.string.ACCOUNT_DESC.toString())

        /* set the values to the views in the current activity from the data obtained from the intent's bundle */
        accountNameTextView.setText(accountName)
        accountDetailsTextView.setText(desc)
        if (accountType == "card") {
            imageView.setImageResource(R.drawable.baseline_credit_card_black_48pt_2x)
        }
        else if(accountType == "bank"){
            imageView.setImageResource(R.drawable.baseline_account_balance_black_48pt_2x)
        }
        else {
            imageView.setImageResource(R.drawable.baseline_credit_card_black_48pt_2x)
        }

    }

}