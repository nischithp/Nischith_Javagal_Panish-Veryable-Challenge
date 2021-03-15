package com.veryable.android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        val headerViewHolder = MyViewHolder(itemView)
        itemView.setOnClickListener {
            val position = headerViewHolder.adapterPosition
            val intentToPayoutDetails = Intent(itemView.context, PayoutsDetailsActivity::class.java)
            intentToPayoutDetails.putExtra("position", position)
            startActivity(parent.context, intentToPayoutDetails, null)
//            onItemClickHandler(position)
        }
        return MyViewHolder(itemView)
    }

//    private fun onItemClickHandler(position: Int) {
//        Log.d("***","${position}");
//        val intentToPayoutDetails = Intent(, PayoutsDetailsActivity::class.java)
//        intentToPayoutDetails.putExtra("position", position)
//        Log.i("",activityList[position].toString())
//        startActivity(intentToPayoutDetails)
//    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = activityList[position]
        holder.accountName.text = movie.getAccountName()
        holder.accountType.text = movie.getAccountType()
        holder.desc.text = movie.getDesc()
        if (holder.accountType.text == "card"){
            holder.image.setImageResource(R.drawable.baseline_credit_card_black_48pt_1x)
        }
        else if(holder.accountType.text == "bank"){
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
