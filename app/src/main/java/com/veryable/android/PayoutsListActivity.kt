package com.veryable.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.databinding.ActivityPayoutsListBinding
import com.veryable.android.models.ActivityModel

class PayoutsListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPayoutsListBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val activityList = ArrayList<ActivityModel>()
    private lateinit var activityAdapter: ActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_list)

        linearLayoutManager = LinearLayoutManager(this)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        activityAdapter = ActivityAdapter(activityList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = activityAdapter
        prepareMovieData()

    }


    private fun prepareMovieData() {

//        var movie = ActivityModel("Mad Max: Fury Road", "Action & Adventure", "2015")
//        activityList.add(movie)
//        movie = ActivityModel("Inside Out", "Animation, Kids & Family", "2015")
//        activityList.add(movie)
//        movie = ActivityModel("Star Wars: Episode VII - The Force Awakens", "Action", "2015")
//        activityList.add(movie)
//        movie = ActivityModel("Shaun the Sheep", "Animation", "2015")
//        activityList.add(movie)
//        movie = ActivityModel("The Martian", "Science Fiction & Fantasy", "2015")
//        activityList.add(movie)
//        movie = ActivityModel("Mission: Impossible Rogue Nation", "Action", "2015")
//        activityList.add(movie)
//        movie = ActivityModel("Up", "Animation", "2009")
//        activityList.add(movie)
//        movie = ActivityModel("Star Trek", "Science Fiction", "2009")
//        activityList.add(movie)
//        movie = ActivityModel("The LEGO MovieModel", "Animation", "2014")
//        activityList.add(movie)
//        movie = ActivityModel("Iron Man", "Action & Adventure", "2008")
//        activityList.add(movie)
//        movie = ActivityModel("Aliens", "Science Fiction", "1986")
//        activityList.add(movie)
//        movie = ActivityModel("Chicken Run", "Animation", "2000")
//        activityList.add(movie)
//        movie = ActivityModel("Back to the Future", "Science Fiction", "1985")
//        activityList.add(movie)
//        movie = ActivityModel("Raiders of the Lost Ark", "Action & Adventure", "1981")
//        activityList.add(movie)
//        movie = ActivityModel("Goldfinger", "Action & Adventure", "1965")
//        activityList.add(movie)
//        movie = ActivityModel("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014")
//        activityList.add(movie)
        activityAdapter.notifyDataSetChanged()
    }
}