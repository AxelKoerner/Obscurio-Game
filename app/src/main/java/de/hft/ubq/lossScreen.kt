package de.hft.ubq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ubq.R

class lossScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endscreen_loss)

        val neuesSpiel = findViewById<Button>(R.id.ErneutSpielenLoss)
        val spielbeenden = findViewById<Button>(R.id.ButtonEscEndLoss)

        neuesSpiel.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }

        spielbeenden.setOnClickListener {
            finish()
            System.exit(0)
        }

    }
}