package de.hft.ubq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.ubq.MainActivity
import com.example.ubq.R


class PictureFullscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_fullscreen)

        fun returnToMain() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val pictureFull = findViewById<ImageButton>(R.id.Button_fullscreen)

        pictureFull.setOnClickListener(View.OnClickListener {
            returnToMain()
        })


    }



}