package com.example.countrystuff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.ListView
import com.example.countrystuff.R

class CountryListActivity : AppCompatActivity() {
    private lateinit var myCountryList: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        myCountryList = findViewById(R.id.countryList)
        var countryList = resources.getStringArray(R.array.countries)
        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, countryList)
        myCountryList.adapter = arrayAdapter

        myCountryList.setOnItemClickListener { parent, view, position, id ->
            val countryClicked = parent.getItemAtPosition(position).toString()
            Toast.makeText(this, "you clicked on $countryClicked", Toast.LENGTH_LONG).show()
        }
    }
}