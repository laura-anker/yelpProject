package com.example.countrystuff

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport
import java.lang.RuntimeException

private lateinit var myUsername: EditText
private lateinit var myPassword: EditText
private lateinit var myButton: Button
private lateinit var signUpBtn: Button
private lateinit var firebaseAuth: FirebaseAuth
private lateinit var crashButton: Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val preferences = getSharedPreferences("Yelp", Context.MODE_PRIVATE)
        myPassword = findViewById(R.id.password)
        myUsername = findViewById(R.id.username)
        myButton = findViewById(R.id.loginButton)
        signUpBtn = findViewById(R.id.signUpBtn)
        crashButton = findViewById(R.id.crashButton)
        firebaseAuth = FirebaseAuth.getInstance()

        val savedUsername = preferences.getString("SAVED_USERNAME", "")
        val savedPassword = preferences.getString("SAVED_PASSWORD", "")

        myUsername.setText(savedUsername)
        myPassword.setText(savedPassword)

        signUpBtn.setOnClickListener {
            val inputtedUsername:String = myUsername.text.toString().trim()
            val inputtedPassword:String = myPassword.text.toString().trim()
            firebaseAuth.createUserWithEmailAndPassword(inputtedUsername, inputtedPassword)
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        val user= firebaseAuth.currentUser
                        Toast.makeText(this, "Created user: ${user!!.email}", Toast.LENGTH_LONG).show()
                    }else{
                        val exception = it.exception
                        Toast.makeText(this, "Failed: $exception", Toast.LENGTH_LONG).show()
                    }
                }
        }

        crashButton.setOnClickListener {
            Log.d("crash", "purposely crashing app")
            throw RuntimeException("Test crash")
        }

        myButton.setOnClickListener {
            preferences.edit().putString("SAVED_USERNAME", myUsername.text.toString()).apply()
            preferences.edit().putString("SAVED_PASSWORD", myPassword.text.toString()).apply()
            val inputtedUsername:String = myUsername.text.toString().trim()
            val inputtedPassword:String = myPassword.text.toString().trim()

            firebaseAuth.signInWithEmailAndPassword(inputtedUsername, inputtedPassword)
                .addOnCompleteListener{
                    if (it.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        Toast.makeText(this, "Logged in as $(user!!.email", Toast.LENGTH_LONG).show()
                        preferences.edit().putString("SAVED_USERNAME", myUsername.text.toString())
                            .apply()
                        preferences.edit().putString("SAVED_PASSWORD", myPassword.text.toString())
                            .apply()
                        val countryIntentActivity =
                            Intent(this@MainActivity, MapsActivity::class.java)
                        startActivity(countryIntentActivity)
                    }else{
                        val exception = it.exception
                        Toast.makeText(this, "Failed: $exception", Toast.LENGTH_LONG).show()
                    }
                }



        }
    }
}