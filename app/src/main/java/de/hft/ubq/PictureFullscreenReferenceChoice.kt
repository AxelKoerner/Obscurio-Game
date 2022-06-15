package de.hft.ubq

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import com.example.ubq.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class PictureFullscreenReferenceChoice : AppCompatActivity() {
    val shared_Preferences:String = "shared_Preferences"

    private lateinit var database : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_fullscreen_reference_choice)
        supportActionBar?.hide()

        database = Firebase.database.reference

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

    fun getdata(dbchild : String) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var test = snapshot.child("$dbchild").getValue()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun setData(dbchild2 : String, saveValue : String, datatype : String) {
        when(datatype){
            "Int"->{
                database.child("$dbchild2").setValue(saveValue.toInt())
            }
            "String"->{
                database.child("$dbchild2").setValue("$saveValue")
            }
            "Boolean"->{
                if (saveValue == "true"){
                    database.child("$dbchild2").setValue(true)
                }
                if (saveValue == "false"){
                    database.child("$dbchild2").setValue(false)
                }
            }
        }
    }
}