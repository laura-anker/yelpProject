package com.example.countrystuff

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.countrystuff.databinding.ActivityMapsBinding
import com.google.android.material.button.MaterialButton
import java.util.Locale

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var confirmBtn: MaterialButton
    private var clickedAddress: Address?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        confirmBtn = findViewById(R.id.confirmBTN)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        //val latLng = LatLng(38.898365, -77.046753)
        //val title = "GWU"

        val latLng = LatLng(47.7255932, -122.2166597)
        val title = "home"
        mMap.addMarker(
            MarkerOptions().position(latLng).title(title)
        )
        val zoomlevel = 12.0f
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(latLng,zoomlevel)
        )
        val geocoder = Geocoder(this, Locale.getDefault())

        confirmBtn.setOnClickListener {
            val yelpListingsIntent = Intent(this, YelpListingsActivity:: class.java)
            yelpListingsIntent.putExtra("address", clickedAddress)
            startActivity(yelpListingsIntent)
        }

        mMap.setOnMapLongClickListener {
            val title2 = "unknown"
            val maxResult = 5
            val results=
                try{
                    geocoder.getFromLocation(
                        it.latitude,
                        it.longitude,
                        maxResult
                    )
                }catch(exception: Exception){
                    Log.e("Maps", "Geocoding error", exception)
                    listOf<Address>()
                }
            if (results.isNullOrEmpty()){
                Log.d("Maps", "no results")
            }else {
                val currentAddress = results[0]
                updateAddress(currentAddress)
                val addressLine = currentAddress.getAddressLine(0)


                mMap.addMarker(
                    MarkerOptions().position(it).title(addressLine)
                )
                confirmBtn.text = addressLine
                confirmBtn.setBackgroundColor(getColor(R.color.black))
                confirmBtn.isEnabled = true
                //confirmBtn.icon(getDrawable(R.id.))
            }
        }
    }

    private fun updateAddress(address: Address){
        clickedAddress= address
    }
}