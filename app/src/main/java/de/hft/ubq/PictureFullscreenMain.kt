package de.hft.ubq

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.ubq.R
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Switch

class PictureFullscreenMain : AppCompatActivity() {
    val shared_Preferences:String = "shared_Preferences"





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_fullscreen_main)
        supportActionBar?.hide()

        val pictureFull = findViewById<ImageButton>(R.id.Button_fullscreen_main)
        val confirmSwitch: Switch = findViewById(R.id.switch_Main)
        var bundle = getIntent().getExtras()

        if (bundle != null) {
            var res_image = bundle.getString("chosenImage")

            val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)


            var savedInt = sharedPreferences.getInt(res_image, 700015)
            var reference = savedInt
            pictureFull.setImageResource(savedInt)

            savedInt = sharedPreferences.getInt("PlayerChoice", 700015)
            if(savedInt == reference){
                confirmSwitch.setChecked(true)
            }
        }


        pictureFull.setOnClickListener(View.OnClickListener {
           finish()
        })
        confirmSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            var res_image = bundle?.getString("chosenImage")
            var savedInt = sharedPreferences.getInt(res_image, 700015)
            if (isChecked && sharedPreferences.getInt("PlayerChoice", 700015) == 700015) {
                editor.apply {
                    putInt("PlayerChoice", savedInt)
                }.apply()
            }
            if(!isChecked) {
                editor.apply{
                    putInt("PlayerChoice", 700015)
                }.apply()
            }
            if(isChecked && sharedPreferences.getInt("PlayerChoice", 700015) != 700015){
                confirmSwitch.setChecked(false)
            }


        }


    }
}
