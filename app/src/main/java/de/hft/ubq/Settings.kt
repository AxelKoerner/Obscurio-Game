package de.hft.ubq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ubq.R


class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.hide()
        val b = findViewById<Button>(R.id.button)

        b.setOnClickListener{
            setContentView(R.layout.activity_main)
        }


        val intentMusic = Intent(this, BackgroundSoundService::class.java)
        val musicOnOffBtn = findViewById<Button>(R.id.musicOnOff)
        musicOnOffBtn.setOnClickListener {
            BackgroundSoundService().onDestroy()
        }


    }
}