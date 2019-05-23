package com.korostenskyi.sunnyday.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.korostenskyi.sunnyday.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val LAYOUT = R.layout.activity_main

    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)

        initPlaces()
    }

    private fun fetchDayInfo(latitude: Double, longitude: Double) {

        viewModel.dayInfo.observe(this@MainActivity, Observer { dayInfoWrapped ->
            if (dayInfoWrapped.data != null) {

                sunRise_tv.text = dayInfoWrapped.data.sunrise
                sunSet_tv.text = dayInfoWrapped.data.sunset
                dayDuration_tv.text = dayInfoWrapped.data.dayLength

            } else if (dayInfoWrapped.error != null) {

                println(dayInfoWrapped.error.toString())
            }
        })

        GlobalScope.launch(Dispatchers.IO) {
            viewModel.fetchDayInfoByCoordinates(latitude, longitude)
        }
    }

    private fun initPlaces() {

        Places.initialize(this@MainActivity, resources.getString(R.string.places_api_key))

        val autoCompleteSupportFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment
        autoCompleteSupportFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG))

        autoCompleteSupportFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {

            override fun onPlaceSelected(place: Place) {

                val coordinates = place.latLng

                val latitude = coordinates!!.latitude
                val longitude = coordinates.latitude

                fetchDayInfo(latitude, longitude)
            }

            override fun onError(status: Status) {
                Log.e("GOOGLE PLACES", "$status")
            }
        })
    }
}
