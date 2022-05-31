package de.hft.ubq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ubq.R

class Modus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modus)

        val buttonOffline = findViewById<Button>(R.id.offline)
        val buttonOnline = findViewById<Button>(R.id.online)

        buttonOffline.setOnClickListener{
            val intent = Intent(this, GM_ReferenceChoice::class.java)
            startActivity(intent)
        }

        buttonOnline.setOnClickListener { //TODO LoginScreen
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }


    }
}