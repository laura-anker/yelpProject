package com.example.countrystuff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

private lateinit var myUsername: EditText
private lateinit var myPassword: EditText
private lateinit var myButton: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myPassword = findViewById(R.id.password)
        myUsername = findViewById(R.id.username)
        myButton = findViewById(R.id.loginButton)

        myButton.setOnClickListener {
            val countryIntentActivity= Intent(this@MainActivity, YelpListingsActivity::class.java)
            startActivity(countryIntentActivity)
        }
    }
}