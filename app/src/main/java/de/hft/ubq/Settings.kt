package com.example.ubq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_settings.*


class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val b = findViewById<Button>(R.id.button)

        b.setOnClickListener{
            setContentView(R.layout.activity_main)
        }

    }



}