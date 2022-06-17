package de.hft.ubq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import com.example.ubq.R


class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.hide()


        val b = findViewById<Button>(R.id.zurück)
        b.setOnClickListener{
            finish()
        }


        pauseResume()
        changeVolume()

    }

    fun changeVolume() {
        val seekBar = findViewById<SeekBar>(R.id.backgroundVolume)
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                val volume = progress.toFloat()
                BackgroundSoundService.changeVolume(volume/100)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
    }

    fun pauseResume() {
        val musicOnOffBtn = findViewById<Button>(R.id.musicOnOff)
        var musicOn = true
        musicOnOffBtn.setOnClickListener {
            if(musicOn) {
                BackgroundSoundService.pauseAudio()
                musicOn = false
            }

            else {
                BackgroundSoundService.continuePlaying(this, R.raw.song2)
                musicOn = true
            }
        }
    }
}