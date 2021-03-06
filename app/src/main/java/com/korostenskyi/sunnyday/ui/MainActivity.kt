package com.korostenskyi.sunnyday.ui

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.korostenskyi.sunnyday.R
import com.korostenskyi.sunnyday.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPlaces()
        bindUI()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this@MainActivity)
        if (NetworkUtils.isNetworkAvailable(this@MainActivity)) {
            choose_location_btn.setOnClickListener {
                updateCurrentLocation()
            }
        } else {
            showToast("No network connection...")
        }
    }

    private fun bindUI() {
        viewModel.dayInfo.observe(this@MainActivity, Observer { dayInfoWrapped ->
            sunRise_tv.text = dayInfoWrapped.sunrise
            sunSet_tv.text = dayInfoWrapped.sunset
            dayDuration_tv.text = dayInfoWrapped.dayLength
        })
    }

    private fun fetchDayInfo(latitude: Double, longitude: Double) {
        GlobalScope.launch(Dispatchers.IO) {
            viewModel.fetchDayInfoByCoordinates(latitude, longitude)
        }
    }

    private fun updateCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this@MainActivity, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                fetchDayInfo(location.latitude, location.longitude)
            }
        } else {
            requestPermission()
            updateCurrentLocation()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(ACCESS_FINE_LOCATION), PLACE_REQUEST)
    }

    private fun initPlaces() {
        (supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment).apply {
            setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG))
            setOnPlaceSelectedListener(object : PlaceSelectionListener {

                override fun onPlaceSelected(place: Place) {
                    val coordinates = place.latLng
                    fetchDayInfo(coordinates!!.latitude, coordinates.latitude)
                }

                override fun onError(status: Status) {
                    Log.e("GOOGLE PLACES", "$status")
                }
            })
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val PLACE_REQUEST = 1
    }
}
