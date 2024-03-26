package com.example.countrystuff

import android.location.Address
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class YelpListingsActivity : AppCompatActivity() {
    private lateinit var myRecyclerView: RecyclerView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yelp_listings)

        myRecyclerView = findViewById(R.id.myRecyclerView)
        val address=intent.getParcelableExtra("address", Address::class.java)!!

        val yelpManager = YelpManager()
        //val gwuLat = 38.899
        //val gwuLong = -77.0486
        val lat = address.latitude
        val long = address.longitude
        val apiKey = getString(R.string.yelp_key)
        var yelps = listOf<YelpBusiness>()
        CoroutineScope(IO).launch{
            yelps = yelpManager.retrieveYelps(lat, long, apiKey)
            withContext(Main){
                val adapter = YelpAdapter(yelps)
                myRecyclerView.adapter= adapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@YelpListingsActivity)
            }
        }
        //val yelps = getFakeYelpData()

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