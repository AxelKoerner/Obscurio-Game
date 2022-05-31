package de.hft.ubq

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.ubq.R
val shared_Preferences:String = "shared_Preferences"
class Midscreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_midscreen)

        var weiter = findViewById<Button>(R.id.continueMidscreen)
        var round = findViewById<TextView>(R.id.runden)
        var lifes = findViewById<TextView>(R.id.leben)

        updateRound(round)
        updatelifes(lifes)

        weiter.setOnClickListener {
            clearPreferencesRound()
            intent = Intent(this, GM_ReferenceChoice::class.java)
            startActivity(intent)
        }
    }

    fun updateRound(textView: TextView){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        textView.setText("Runde: " + sharedPreferences.getInt("Round", 0))
    }
    fun updatelifes(textView: TextView){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        textView.setText("Leben: " + sharedPreferences.getInt("Lifepoints", 9))
    }

    fun clearPreferencesRound(){

        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply{
            putInt("overallVotes", 0)
            putBoolean("PlayerTurn", false)
            putInt("PlayerChoice", 700015)
            putBoolean("gmDone", false)
            putInt("correctDoor",0)
            putInt("correctVotes", 0)
            putString("UsedPicture", "")
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
            putInt("PositionX_ChosenReference1", 100)
            putInt("PositionY_ChosenReference1", 100)
            putInt("PositionX_ChosenReference2", 100)
            putInt("PositionY_ChosenReference2", 100)
            putInt("Picture1Main", 700015)
            putInt("Picture2Main", 700015)
            putInt("Picture3Main", 700015)
            putInt("Picture4Main", 700015)
            putInt("Picture5Main", 700015)
            putInt("Picture6Main", 700015)
            putString("AvailablePictures", "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20," +
                    "21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40," +
                    "41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60," +
                    "61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76")



        }.apply()
    }
}