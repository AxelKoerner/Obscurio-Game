package de.hft.ubq

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.ubq.R


class PictureFullscreenGM : AppCompatActivity() {
    val shared_Preferences:String = "shared_Preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_fullscreen)

        fun returnToMain() {
            val intent = Intent(this, GM_ReferenceChoice::class.java)
            startActivity(intent)
        }

        val pictureFull = findViewById<ImageButton>(R.id.Button_fullscreen)
        var bundle = getIntent().getExtras()

        if (bundle != null) {
            var res_image = bundle.getString("chosenImage")
            val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)


            var savedInt = sharedPreferences.getInt(res_image, 700015)
            pictureFull.setImageResource(savedInt)
        }

        pictureFull.setOnClickListener(View.OnClickListener {
            returnToMain()
        })


    }



}