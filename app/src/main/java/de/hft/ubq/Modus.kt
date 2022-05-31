package de.hft.ubq

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ubq.R
import kotlinx.android.synthetic.main.activity_gm_reference_choice.*
import kotlinx.android.synthetic.main.activity_modus.*


class Modus : AppCompatActivity() {

    val shared_Preferences:String = "shared_Preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modus)

        val buttonOffline = findViewById<Button>(R.id.offline)
        val buttonOnline = findViewById<Button>(R.id.online)

        buttonOffline.setOnClickListener{
            setLifesandPlayers()
            val intent = Intent(this, GM_ReferenceChoice::class.java)
            startActivity(intent)
        }

        buttonOnline.setOnClickListener { //TODO LoginScreen
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }


    }

    fun setLifesandPlayers(){
        var leben = lebensText.text.toString().toInt()
        var Spieler = spielerText.text.toString().toInt()
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply{
            putInt("Lifepoints", leben)
            putInt("PlayerNumber", Spieler)
        }.apply()

    }

}