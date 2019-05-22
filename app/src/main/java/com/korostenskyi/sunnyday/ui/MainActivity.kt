package com.korostenskyi.sunnyday.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
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

        // Fetch data for Lviv
        choose_location_btn.setOnClickListener {
            fetchDayInfo()
        }
    }

    private fun fetchDayInfo() {

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
            viewModel.fetchDayInfoByCoordinates(49.0, 24.0)
        }
    }


}
