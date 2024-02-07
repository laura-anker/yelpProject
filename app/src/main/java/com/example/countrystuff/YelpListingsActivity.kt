package com.example.countrystuff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class YelpListingsActivity : AppCompatActivity() {
    private lateinit var myRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yelp_listings)

        myRecyclerView = findViewById(R.id.myRecyclerView)
        val yelps = getFakeYelpData()
        val adapter = YelpAdapter(yelps)
        myRecyclerView.adapter= adapter
        myRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getFakeYelpData(): List<YelpBusiness>{
        return listOf(
            YelpBusiness("Panera","Breakfast",4.2,"https://...", "none"),
            YelpBusiness("WingsToGo","Comfort",5.3,"https://...", "none"),
            YelpBusiness("Log Cabin","Seafood",4.2,"https://...", "none"),
            YelpBusiness("Dunkin Donut","Breakfast",3.1,"https://...", "none"),
            YelpBusiness("Starbucks","Coffee",3.1,"https://...", "none"),
            YelpBusiness("Panera","Breakfast",3.1,"https://...", "none"),
            YelpBusiness("Panera","Breakfast",3.1,"https://...", "none"),
            YelpBusiness("Panera","Breakfast",2.8,"https://...", "none"),
            YelpBusiness("Panera","Breakfast",5.3,"https://...", "none"),
            YelpBusiness(icon="https://....", category="Dinner", rating=1.2, restaurantName ="Subway",url="none")

        )
    }
}