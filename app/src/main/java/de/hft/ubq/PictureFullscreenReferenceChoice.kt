package de.hft.ubq

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import com.example.ubq.R

class PictureFullscreenReferenceChoice : AppCompatActivity() {
    val shared_Preferences:String = "shared_Preferences"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_fullscreen_reference_choice)
        supportActionBar?.hide()

        fun backtoReferenceChoice() {
            finish()
        }

        val pictureFull = findViewById<ImageButton>(R.id.Button_fullscreen_ReferenceChoice)
        val confirmSwitch: Switch = findViewById(R.id.switch_referenceChoice)
        var bundle = getIntent().getExtras()

        if (bundle != null) {
            var res_image = bundle.getString("chosenImage")
            val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)


            var savedInt = sharedPreferences.getInt(res_image, 700015)
            var reference = savedInt
            pictureFull.setImageResource(savedInt)

            savedInt = sharedPreferences.getInt("ChosenReference1", 700015)
            if(savedInt == reference){
                confirmSwitch.setChecked(true)
            }
            savedInt = sharedPreferences.getInt("ChosenReference2", 700015)
            if(savedInt == reference){
                confirmSwitch.setChecked(true)
            }
            savedInt = sharedPreferences.getInt("PlayerChoice", 700015)
            if(savedInt==reference){
                confirmSwitch.setChecked(true)
            }
        }

        confirmSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            var res_image = bundle?.getString("chosenImage")
            var savedInt = sharedPreferences.getInt(res_image, 700015)
            if(!sharedPreferences.getBoolean("PlayerTurn", false)){
                if (isChecked) {
                    if (sharedPreferences.getInt("ChosenReference1", 700015) == 700015) {

                        editor.apply {
                            putInt("ChosenReference1", savedInt)

                        }.apply()
                        Toast.makeText(this, "Reference 1 Chosen", Toast.LENGTH_SHORT).show()
                    } else if (sharedPreferences.getInt("ChosenReference2", 700015) == 700015) {

                        editor.apply {
                            putInt("ChosenReference2", savedInt)

                        }.apply()
                        Toast.makeText(this, "Reference 2 Chosen", Toast.LENGTH_SHORT).show()
                    } else if (sharedPreferences.getInt("ChosenReference1", 700015) != 700015
                        && sharedPreferences.getInt("ChosenReference2", 700015) != 700015
                    ) {
                        confirmSwitch.setChecked(false)

                        Toast.makeText(this, "Uncheck one Reference to choose This", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else if (sharedPreferences.getInt("ChosenReference1", 700015) == savedInt) {
                    editor.apply {
                        putInt("ChosenReference1", 700015)
                    }.apply()
                    Toast.makeText(this, "Reference 1 Unchecked", Toast.LENGTH_SHORT).show()
                } else if (sharedPreferences.getInt("ChosenReference2", 700015) == savedInt) {
                    editor.apply {
                        putInt("ChosenReference2", 700015)
                    }.apply()
                    Toast.makeText(this, "Reference 2 Unchecked", Toast.LENGTH_SHORT).show()
                }
            }
            if(sharedPreferences.getBoolean("PlayerTurn", false)){
                if(isChecked){
                    if(sharedPreferences.getInt("PlayerChoice", 700015)!= 700015
                        && sharedPreferences.getInt("PlayerChoice", 700015)!= savedInt ){
                        confirmSwitch.setChecked(false)
                    }
                    else {
                        editor.apply {
                            putInt("PlayerChoice", savedInt)

                        }.apply()
                    }
                }
                if (!isChecked){
                    editor.apply {
                        putInt("PlayerChoice", 700015)

                    }.apply()
                }
            }
        }


        pictureFull.setOnClickListener(View.OnClickListener {
            backtoReferenceChoice()
        })


    }
}