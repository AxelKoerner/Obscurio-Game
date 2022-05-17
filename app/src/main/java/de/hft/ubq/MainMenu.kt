package de.hft.ubq


import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.ubq.R

class MainMenu : AppCompatActivity() {
    val shared_Preferences:String = "shared_Preferences"


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
            val intent = Intent(this, GM_ReferenceChoice::class.java)
            clearPreferences()
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
    fun clearPreferences(){

        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply{
            putBoolean("isRunning", false)
            putInt("Picture1", 700015)
            putInt("Picture2", 700015)
            putInt("Picture3", 700015)
            putInt("Picture4", 700015)
            putInt("Picture5", 700015)
            putInt("Picture6", 700015)
            putInt("Picture7", 700015)
            putInt("ChosenReference1", 700015)
            putInt("ChosenReference2", 700015)
            putInt("PositionX_ChosenReference1", 0)
            putInt("PositionY_ChosenReference1", 0)
            putInt("PositionX_ChosenReference2", 0)
            putInt("PositionY_ChosenReference2", 0)
        }.apply()
    }





}