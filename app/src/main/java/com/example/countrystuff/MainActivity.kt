package com.example.countrystuff

import android.content.Context
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
        val preferences = getSharedPreferences("Yelp", Context.MODE_PRIVATE)
        myPassword = findViewById(R.id.password)
        myUsername = findViewById(R.id.username)
        myButton = findViewById(R.id.loginButton)

        val savedUsername = preferences.getString("SAVED_USERNAME", "")
        val savedPassword = preferences.getString("SAVED_PASSWORD", "")

        myUsername.setText(savedUsername)
        myPassword.setText(savedPassword)

        myButton.setOnClickListener {
            preferences.edit().putString("SAVED_USERNAME", myUsername.text.toString()).apply()
            preferences.edit().putString("SAVED_PASSWORD", myPassword.text.toString()).apply()
            val countryIntentActivity= Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(countryIntentActivity)
        }
    }
}