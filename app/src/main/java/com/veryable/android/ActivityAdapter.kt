package com.veryable.android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.models.ActivityModel

internal class ActivityAdapter (private var activityList: List<ActivityModel>):
    RecyclerView.Adapter<ActivityAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var accountName: TextView = view.findViewById(R.id.title)
        var desc: TextView = view.findViewById(R.id.year)
        var accountType: TextView = view.findViewById(R.id.genre)
        var image: ImageView = view.findViewById(R.id.cardImage)
        /* added the parent component here so that I can add the onClickListener to this component later on */
        var payoutsHomeScreen : RelativeLayout = view.findViewById(R.id.layoutPayoutsListView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        val headerViewHolder = MyViewHolder(itemView)
        return MyViewHolder(itemView)
    }

    /* set the values to items in the viewholder and set the onclick listener for the specific card component */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = activityList[position]
        holder.accountName.text = item.getAccountName()
        holder.accountType.text = item.getAccountType()
        holder.desc.text = item.getDesc()
        holder.payoutsHomeScreen.setOnClickListener {
            val intentToPayoutDetails = Intent(it.context, PayoutsDetailsActivity::class.java)
            intentToPayoutDetails.putExtra(R.string.ACCOUNT_NAME.toString(), item.getAccountName())
            intentToPayoutDetails.putExtra(R.string.ACCOUNT_ID.toString(), item.getAccountId())
            intentToPayoutDetails.putExtra(R.string.ACCOUNT_DESC.toString(), item.getDesc())
            intentToPayoutDetails.putExtra(R.string.ACCOUNT_TYPE.toString(), item.getAccountType())
            startActivity(it.context, intentToPayoutDetails, null)
        }
        if (item.getAccountType() == "card"){
            holder.image.setImageResource(R.drawable.baseline_credit_card_black_48pt_1x)
        }
        else if(item.getAccountType() == "bank"){
            holder.image.setImageResource(R.drawable.baseline_account_balance_black_48pt_1x)
        }
        else {
            holder.image.setImageResource(R.drawable.baseline_credit_card_black_48pt_1x)
        }

    }

    override fun getItemCount(): Int {
        return activityList.size
    }

}
