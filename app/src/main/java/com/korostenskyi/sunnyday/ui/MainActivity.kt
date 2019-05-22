package com.korostenskyi.sunnyday.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.korostenskyi.sunnyday.R

class MainActivity : AppCompatActivity() {

    private val LAYOUT = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
    }
}
