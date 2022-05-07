package de.hft.ubq


import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ubq.R

class MainMenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        val mediaPlayer = MediaPlayer.create(this, R.raw.music)
        //mediaPlayer?.start()
        mediaPlayer?.isLooping = true

        val bttNeuSpiel = findViewById<Button>(R.id.ButtonNeuSpiel)
        val bttSpielBeit = findViewById<Button>(R.id.ButtonSpielBeit)
        val bttOpt = findViewById<Button>(R.id.ButtonOpt)
        val bttBeenden = findViewById<Button>(R.id.ButtonEsc)


        bttNeuSpiel.setOnClickListener {
            val intent = Intent(this, MainActivityGM::class.java)
            startActivity(intent)
        }

        bttSpielBeit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        bttOpt.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
        }

        bttBeenden.setOnClickListener {
            finish()
            System.exit(0)
        }



    }






}