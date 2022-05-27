package de.hft.ubq

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ubq.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var countDownTimer: CountDownTimer
    private var countDownProgress: Int = 180000 //TODO May only reset after Round End
    val shared_Preferences:String = "shared_Preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val mediaPlayer = MediaPlayer.create(this, R.raw.music)
        //mediaPlayer?.start()
        mediaPlayer?.isLooping = true
        countdown()



        //-----------------------------onClick Listener------------------------------//

        val button1 = findViewById<ImageButton>(R.id.Picture1Main)
        val button2 = findViewById<ImageButton>(R.id.Picture2Main)
        val button3 = findViewById<ImageButton>(R.id.Picture3Main)
        val button4 = findViewById<ImageButton>(R.id.Picture4Main)
        val button5 = findViewById<ImageButton>(R.id.Picture5Main)
        val button6 = findViewById<ImageButton>(R.id.Picture6Main)
        val button7 = findViewById<ImageButton>(R.id.MarkedReference1)
        val button8 = findViewById<ImageButton>(R.id.MarkedReference2)
        val confirm = findViewById<Button>(R.id.ConfirmMain)

        fun openPictureFullscreenMain(button: ImageButton){
            val intent = Intent(this, PictureFullscreenReferenceChoice::class.java)
            var resource = "Picture0"
            if(button==button1){
                resource = "Picture1Main"
            }
            if(button==button2){
                resource = "Picture2Main"
            }
            if(button==button3){
                resource = "Picture3Main"
            }
            if(button==button4){
                resource = "Picture4Main"
            }
            if(button==button5){
                resource = "Picture5Main"
            }
            if(button==button6){
                resource = "Picture6Main"
            }


            intent.putExtra("chosenImage", resource)
            startActivity(intent)
        }

        fun openPictureFullscreenMarked(button: ImageButton) {
            val intent = Intent(this, PictureFullscreenMarked::class.java)
            var resource = "Picture0"
            if(button==button7){
                resource = "ChosenReference1"
            }
            if(button==button8){
                resource = "ChosenReference2"
            }
            intent.putExtra("chosenImage", resource)
            startActivity(intent)
        }


        confirm.setOnClickListener {
            doSomething()
        }

        button1.setOnClickListener{
            openPictureFullscreenMain(button1)
        }

        button2.setOnClickListener {
           openPictureFullscreenMain(button2)
        }

        button3.setOnClickListener {
            openPictureFullscreenMain(button3)
        }

        button4.setOnClickListener {
            openPictureFullscreenMain(button4)
        }

        button5.setOnClickListener {
            openPictureFullscreenMain(button5)
        }

        button6.setOnClickListener {
            openPictureFullscreenMain(button6)
        }

        button7.setOnClickListener {
            openPictureFullscreenMarked(button7)
        }

        button8.setOnClickListener {
            openPictureFullscreenMarked(button8)
        }




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.chat -> Toast.makeText(this,"Chat",Toast.LENGTH_SHORT).show()
            R.id.leave -> finishAffinity()
            R.id.option -> setContentView(R.layout.activity_settings)

        }

        return super.onOptionsItemSelected(item)
    }

    fun timeout(){ //TODO has to happen when Time runs out
        doSomething()
        roundEnd()
    }

    fun doSomething(){//TODO check for Synchronization, has to happen for each Player
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if(sharedPreferences.getInt("PlayerChoice", 700015) != 700015){
            if(sharedPreferences.getInt("PlayerChoice", 700015) ==
                    sharedPreferences.getInt("Picture7", 700015)){
               var Count = sharedPreferences.getInt("correctVotes", 0)+1
                editor.apply {
                    putInt("correctVotes", Count)

                }.apply()

            }
        }
    }

    fun roundEnd(){// TODO check for Synchronization, has to happen only once
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if(sharedPreferences.getInt("correctVotes", 0) > sharedPreferences.getInt("PlayerNumber",3)/2){
            var Round = sharedPreferences.getInt("Round", 0)+1
            editor.apply {
                putInt("Round", Round)
            }.apply()
        }
        if(sharedPreferences.getInt("correctVotes", 0) <= sharedPreferences.getInt("PlayerNumber",3)/2){
            var Round = sharedPreferences.getInt("Round", 0)
            var LifePoints = sharedPreferences.getInt("Lifepoints", 0)
                            - (sharedPreferences.getInt("PlayerNumber",3) - sharedPreferences.getInt("correctVotes", 0))
            editor.apply {
                putInt("Round", Round)
                putInt("Lifepoints", LifePoints)
            }.apply()
        }
        if(sharedPreferences.getInt("Lifepoints", 9) <= 0){
            //TODO GameOver
        }
        if(sharedPreferences.getInt("Round", 0) > sharedPreferences.getInt("maxRounds", 7)
            &&sharedPreferences.getInt("Lifepoints", 9) > 0){
            //TODO WIN
        }

    }

    fun providePictures(){//TODO Call when GM has confirmed ArrowPositions
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val correctDoor = sharedPreferences.getInt("correctDoor", 1)
        val button1 = findViewById<ImageButton>(R.id.Picture1Main)
        val button2 = findViewById<ImageButton>(R.id.Picture2Main)
        val button3 = findViewById<ImageButton>(R.id.Picture3Main)
        val button4 = findViewById<ImageButton>(R.id.Picture4Main)
        val button5 = findViewById<ImageButton>(R.id.Picture5Main)
        val button6 = findViewById<ImageButton>(R.id.Picture6Main)
        val game = Game()

        if(correctDoor == 1){
            button1.setImageResource(sharedPreferences.getInt("Picture7", 700015))
            game.matchdoor(button2, sharedPreferences.getInt("Picture2Main", 0))
            game.matchdoor(button3, sharedPreferences.getInt("Picture3Main", 0))
            game.matchdoor(button4, sharedPreferences.getInt("Picture4Main", 0))
            game.matchdoor(button5, sharedPreferences.getInt("Picture5Main", 0))
            game.matchdoor(button6, sharedPreferences.getInt("Picture6Main", 0))
        }
        if(correctDoor == 2){
            button2.setImageResource(sharedPreferences.getInt("Picture7", 700015))
            game.matchdoor(button1, sharedPreferences.getInt("Picture1Main", 0))
            game.matchdoor(button3, sharedPreferences.getInt("Picture3Main", 0))
            game.matchdoor(button4, sharedPreferences.getInt("Picture4Main", 0))
            game.matchdoor(button5, sharedPreferences.getInt("Picture5Main", 0))
            game.matchdoor(button6, sharedPreferences.getInt("Picture6Main", 0))
        }
        if(correctDoor == 3){
            button3.setImageResource(sharedPreferences.getInt("Picture7", 700015))
            game.matchdoor(button2, sharedPreferences.getInt("Picture2Main", 0))
            game.matchdoor(button1, sharedPreferences.getInt("Picture1Main", 0))
            game.matchdoor(button4, sharedPreferences.getInt("Picture4Main", 0))
            game.matchdoor(button5, sharedPreferences.getInt("Picture5Main", 0))
            game.matchdoor(button6, sharedPreferences.getInt("Picture6Main", 0))
        }
        if(correctDoor == 4){
            button4.setImageResource(sharedPreferences.getInt("Picture7", 700015))
            game.matchdoor(button2, sharedPreferences.getInt("Picture2Main", 0))
            game.matchdoor(button3, sharedPreferences.getInt("Picture3Main", 0))
            game.matchdoor(button1, sharedPreferences.getInt("Picture1Main", 0))
            game.matchdoor(button5, sharedPreferences.getInt("Picture5Main", 0))
            game.matchdoor(button6, sharedPreferences.getInt("Picture6Main", 0))
        }
        if(correctDoor == 5){
            button5.setImageResource(sharedPreferences.getInt("Picture7", 700015))
            game.matchdoor(button2, sharedPreferences.getInt("Picture2Main", 0))
            game.matchdoor(button3, sharedPreferences.getInt("Picture3Main", 0))
            game.matchdoor(button4, sharedPreferences.getInt("Picture4Main", 0))
            game.matchdoor(button1, sharedPreferences.getInt("Picture1Main", 0))
            game.matchdoor(button6, sharedPreferences.getInt("Picture6Main", 0))
        }
        if(correctDoor == 6){
            button6.setImageResource(sharedPreferences.getInt("Picture7", 700015))
            game.matchdoor(button2, sharedPreferences.getInt("Picture2Main", 0))
            game.matchdoor(button3, sharedPreferences.getInt("Picture3Main", 0))
            game.matchdoor(button4, sharedPreferences.getInt("Picture4Main", 0))
            game.matchdoor(button5, sharedPreferences.getInt("Picture5Main", 0))
            game.matchdoor(button1, sharedPreferences.getInt("Picture1Main", 0))
        }

    }

    private fun countdown(){
        countDownTimer = object : CountDownTimer(5000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                countDownProgress--
                timerMain.text = countDownProgress.toString()
            }

            override fun onFinish() {
                timerMain.setText("done!")
            }
        }.start()

    }


}