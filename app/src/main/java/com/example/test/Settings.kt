package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_settings.*
import android.media.MediaPlayer


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