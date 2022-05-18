package de.hft.ubq

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.ubq.R
import kotlinx.android.synthetic.main.activity_gm_reference_choice.*
import kotlinx.android.synthetic.main.activity_main.*

class GM_ReferenceChoice : AppCompatActivity() {
    var isRunning = false
    val shared_Preferences:String = "shared_Preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gm_reference_choice)

        val mediaPlayer = MediaPlayer.create(this, R.raw.music)
        //mediaPlayer?.start()
        mediaPlayer?.isLooping = true




        //-----------------------------onClick Listener------------------------------//

        val button1 = findViewById<ImageButton>(R.id.Picture1_ReferenceChoice)
        val button2 = findViewById<ImageButton>(R.id.Picture2_ReferenceChoice)
        val button3 = findViewById<ImageButton>(R.id.Picture3_ReferenceChoice)
        val button4 = findViewById<ImageButton>(R.id.Picture4_ReferenceChoice)
        val button5 = findViewById<ImageButton>(R.id.Picture5_ReferenceChoice)
        val button6 = findViewById<ImageButton>(R.id.Picture6_ReferenceChoice)
        val button7 = findViewById<ImageButton>(R.id.Picture7_ReferenceChoice)
        val confirm = findViewById<Button>(R.id.confirm_ReferenceChoice)



        loadGame()

        if(isRunning==true) {
            loadData()
        }

        if(!isRunning){
            /*
            game.matchdoor(Picture1_ReferenceChoice)
            game.matchdoor(Picture2_ReferenceChoice)
            game.matchdoor(Picture3_ReferenceChoice)
            game.matchdoor(Picture4_ReferenceChoice)
            game.matchdoor(Picture5_ReferenceChoice)
            game.matchdoor(Picture6_ReferenceChoice)
            game.matchdoor(Picture7_ReferenceChoice)
             */
            duplicationPrevention(Picture1_ReferenceChoice)
            duplicationPrevention(Picture2_ReferenceChoice)
            duplicationPrevention(Picture3_ReferenceChoice)
            duplicationPrevention(Picture4_ReferenceChoice)
            duplicationPrevention(Picture5_ReferenceChoice)
            duplicationPrevention(Picture6_ReferenceChoice)
            duplicationPrevention(Picture7_ReferenceChoice)

            isRunning = true
            saveData()
        }



        fun openMainActivityGM() {
            val intent = Intent(this, MainActivityGM::class.java)
            startActivity(intent)
        }




        fun openPictureFullscreen() {
            val intent = Intent(this, PictureFullscreen::class.java)
            var resource = "Picture7"

            intent.putExtra("chosenImage", resource)
            startActivity(intent)
        }

        fun openPictureFullscreenReferenceChoice(button: ImageButton){
            val intent = Intent(this, PictureFullscreenReferenceChoice::class.java)
            var resource = "Picture0"
            if(button==button1){
                resource = "Picture1"
            }
            if(button==button2){
                resource = "Picture2"
            }
            if(button==button3){
                resource = "Picture3"
            }
            if(button==button4){
                resource = "Picture4"
            }
            if(button==button5){
                resource = "Picture5"
            }
            if(button==button6){
                resource = "Picture6"
            }


            intent.putExtra("chosenImage", resource)
            startActivity(intent)
        }


        button1.setOnClickListener{
            openPictureFullscreenReferenceChoice(button1)
        }

        button2.setOnClickListener {
            openPictureFullscreenReferenceChoice(button2)
        }

        button3.setOnClickListener {
            openPictureFullscreenReferenceChoice(button3)
        }

        button4.setOnClickListener {
            openPictureFullscreenReferenceChoice(button4)
        }

        button5.setOnClickListener {
            openPictureFullscreenReferenceChoice(button5)
        }

        button6.setOnClickListener {
            openPictureFullscreenReferenceChoice(button6)
        }

        button7.setOnClickListener {
            openPictureFullscreen()
        }
        confirm.setOnClickListener{
            val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
            var savedInt =sharedPreferences.getInt("ChosenReference1", 700015)
            var chosenReference1 = savedInt
            savedInt=sharedPreferences.getInt("ChosenReference2", 700015)
            var chosenReference2 = savedInt

            if(chosenReference1 != 700015 && chosenReference2 != 700015){
                openMainActivityGM()
            }
            else{
                Toast.makeText(this, "Chose 2 References to Continue", Toast.LENGTH_SHORT).show()
            }
        }



    }

    fun duplicationPrevention(button: ImageButton){
        val game = Game()
        game.matchdoor(button)
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        var buttonTag = button.getTag().toString().toInt()

        var savedInt = sharedPreferences.getInt("Picture1", 700015)
        if(buttonTag == savedInt){duplicationPrevention(button)}
        savedInt = sharedPreferences.getInt("Picture2", 700015)
        if(buttonTag == savedInt){duplicationPrevention(button)}
        savedInt = sharedPreferences.getInt("Picture3", 700015)
        if(buttonTag == savedInt){duplicationPrevention(button)}
        savedInt = sharedPreferences.getInt("Picture4", 700015)
        if(buttonTag == savedInt){duplicationPrevention(button)}
        savedInt = sharedPreferences.getInt("Picture5", 700015)
        if(buttonTag == savedInt){duplicationPrevention(button)}
        savedInt = sharedPreferences.getInt("Picture6", 700015)
        if(buttonTag == savedInt){duplicationPrevention(button)}
        savedInt = sharedPreferences.getInt("Picture7", 700015)
        if(buttonTag == savedInt){duplicationPrevention(button)}


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.chat -> Toast.makeText(this,"Chat", Toast.LENGTH_SHORT).show()
            R.id.leave -> finishAffinity()
            R.id.option -> setContentView(R.layout.activity_settings)

        }

        return super.onOptionsItemSelected(item)
    }

    fun saveData(){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply{
            putBoolean("isRunning", isRunning)
            putInt("Picture1", Picture1_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture2", Picture2_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture3", Picture3_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture4", Picture4_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture5", Picture5_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture6", Picture6_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture7", Picture7_ReferenceChoice.getTag().toString().toInt())
        }.apply()

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()

    }

    fun loadData(){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)


        var savedInt = sharedPreferences.getInt("Picture1", 700015)
        Picture1_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture2", 700015)
        Picture2_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture3", 700015)
        Picture3_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture4", 700015)
        Picture4_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture5", 700015)
        Picture5_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture6", 700015)
        Picture6_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture7", 700015)
        Picture7_ReferenceChoice.setImageResource(savedInt)

    }

    fun loadGame(){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        var savedBoolean = sharedPreferences.getBoolean("isRunning", false)
        isRunning = savedBoolean
    }


}