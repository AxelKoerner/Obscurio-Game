package de.hft.ubq

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ubq.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var countDownTimer: CountDownTimer
    private var countDownProgress: Int = 180000 //TODO May only reset after Round End
    val shared_Preferences:String = "shared_Preferences"
    var availablePictures = IntArray(69)

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
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)

        if(!sharedPreferences.getBoolean("online",false)){
            val game = Game()
            //val maxRightGate = 6
            var rightGate = (1..6).random()
            button7.setImageResource(sharedPreferences.getInt("ChosenReference1", 700015))
            button8.setImageResource(sharedPreferences.getInt("ChosenReference2", 700015))

            when(rightGate){
                1 ->{
                    button1.setImageResource(sharedPreferences.getInt("Picture7", 700015))
                    button1.setTag(sharedPreferences.getInt("Picture7", 700015))
                    game.matchdoor(button2, provideDoor())
                    game.matchdoor(button3, provideDoor())
                    game.matchdoor(button4, provideDoor())
                    game.matchdoor(button5, provideDoor())
                    game.matchdoor(button6, provideDoor())
                }
                2 ->{
                    button2.setImageResource(sharedPreferences.getInt("Picture7", 700015))
                    button2.setTag(sharedPreferences.getInt("Picture7", 700015))
                    game.matchdoor(button1, provideDoor())
                    game.matchdoor(button3, provideDoor())
                    game.matchdoor(button4, provideDoor())
                    game.matchdoor(button5, provideDoor())
                    game.matchdoor(button6, provideDoor())
                }
                3 ->{
                    button3.setImageResource(sharedPreferences.getInt("Picture7", 700015))
                    button3.setTag(sharedPreferences.getInt("Picture7", 700015))
                    game.matchdoor(button2, provideDoor())
                    game.matchdoor(button1, provideDoor())
                    game.matchdoor(button4, provideDoor())
                    game.matchdoor(button5, provideDoor())
                    game.matchdoor(button6, provideDoor())
                }
                4 ->{
                    button4.setImageResource(sharedPreferences.getInt("Picture7", 700015))
                    button4.setTag(sharedPreferences.getInt("Picture7", 700015))
                    game.matchdoor(button2, provideDoor())
                    game.matchdoor(button3, provideDoor())
                    game.matchdoor(button1, provideDoor())
                    game.matchdoor(button5, provideDoor())
                    game.matchdoor(button6, provideDoor())
                }
                5 ->{
                    button5.setImageResource(sharedPreferences.getInt("Picture7", 700015))
                    button5.setTag(sharedPreferences.getInt("Picture7", 700015))
                    game.matchdoor(button2, provideDoor())
                    game.matchdoor(button3, provideDoor())
                    game.matchdoor(button4, provideDoor())
                    game.matchdoor(button1, provideDoor())
                    game.matchdoor(button6, provideDoor())
                }
                6 ->{
                    button6.setImageResource(sharedPreferences.getInt("Picture7", 700015))
                    button6.setTag(sharedPreferences.getInt("Picture7", 700015))
                    game.matchdoor(button2, provideDoor())
                    game.matchdoor(button3, provideDoor())
                    game.matchdoor(button4, provideDoor())
                    game.matchdoor(button5, provideDoor())
                    game.matchdoor(button1, provideDoor())
                }
            }
            saveData()

        }




    progressBarStart()

    }
    fun saveData(){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var str = StringBuilder();
        for( i in 1..availablePictures.size){
            str.append(availablePictures[i-1]).append(",")
        }

        editor.apply{

            putInt("Picture1Main", Picture1Main.getTag().toString().toInt())
            putInt("Picture2Main", Picture2Main.getTag().toString().toInt())
            putInt("Picture3Main", Picture3Main.getTag().toString().toInt())
            putInt("Picture4Main", Picture4Main.getTag().toString().toInt())
            putInt("Picture5Main", Picture5Main.getTag().toString().toInt())
            putInt("Picture6Main", Picture6Main.getTag().toString().toInt())
            putBoolean("PlayerTurn", true)
            putString("AvailablePictures",str.toString())
        }.apply()

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
    }

    fun provideDoor(): Int{
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        var savedString = sharedPreferences.getString("AvailablePictures", "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20," +
                "21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40," +
                "41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60," +
                "61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76")
        var st = StringTokenizer(savedString, ",")

        for(i in 1..availablePictures.size){
            availablePictures[i-1] = Integer.parseInt(st.nextToken())
        }
        availablePictures.shuffle()
        val max = availablePictures.size
        val gate = (Math.random() * (max + 1)).toInt()
        val mutaMapPictures = availablePictures.toMutableList()
        var output = mutaMapPictures.removeAt(gate-1)
        availablePictures = mutaMapPictures.toIntArray()
        availablePictures.shuffle()
        return output
    }


    fun progressBarStart() {
        val pb = findViewById<View>(R.id.progressbar_player) as ProgressBar

        val animation = ObjectAnimator.ofInt(pb, "progress", 0, 100)
        animation.duration = 60000
        animation.interpolator = DecelerateInterpolator()
        animation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {}
            override fun onAnimationEnd(animator: Animator) {
                //do something when the countdown is complete
            }

            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
        })
        animation.start()
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


    fun doSomething(){//TODO check for Synchronization, has to happen for each Player
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()


        if(sharedPreferences.getInt("PlayerChoice", 700015) != 700015){//choice nonstandard
            if(sharedPreferences.getInt("PlayerChoice", 700015) ==
                sharedPreferences.getInt("Picture7", 700015)){//right choice
                var Count = sharedPreferences.getInt("correctVotes", 0)+1
                var votes = sharedPreferences.getInt("overallVotes", 0)+1
                editor.apply {
                    putInt("correctVotes", Count)
                    putInt("overallVotes", votes)
                    putInt("PlayerChoice", 700015)
                }.apply()
                if(sharedPreferences.getInt("overallVotes", 0) < sharedPreferences.getInt("PlayerNumber",3)) {
                    var player = sharedPreferences.getInt("overallVotes", 0) + 1
                    Toast.makeText(
                        this,
                        "Player " + player + " Turn!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            else if(sharedPreferences.getInt("PlayerChoice", 700015) !=
                sharedPreferences.getInt("Picture7", 700015)){//wrong choice
                var lives = sharedPreferences.getInt("Lifepoints", 9)-1
                var votes = sharedPreferences.getInt("overallVotes", 0)+1
                editor.apply {
                    putInt("Lifepoints", lives)
                    putInt("overallVotes", votes)
                    putInt("PlayerChoice", 700015)
                }.apply()
                if(sharedPreferences.getInt("overallVotes", 0) < sharedPreferences.getInt("PlayerNumber",3)) {
                    var player = sharedPreferences.getInt("overallVotes", 0) + 1
                    Toast.makeText(
                        this,
                        "Player " + player + " Turn!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            if(sharedPreferences.getInt("Lifepoints",9)<= 0){//lifepointCheck
                val intent = Intent(this, lossScreen::class.java)
                startActivity(intent)

            }
            else if ( sharedPreferences.getInt("overallVotes", 0)>= sharedPreferences.getInt("PlayerNumber", 3)&&
                sharedPreferences.getInt("Round",0) >= sharedPreferences.getInt("maxRounds", 7)){ //RoundEnd+GameEnd
                val intent = Intent(this, winScreen::class.java)
                startActivity(intent)
            }

            else if ( sharedPreferences.getInt("overallVotes", 0)>= sharedPreferences.getInt("PlayerNumber", 3)){
                var round = sharedPreferences.getInt("Round",0)+1
                editor.apply {
                    putInt("Round", round)


                }.apply()
                val intent = Intent(this, Midscreen::class.java)
                startActivity(intent)
            }
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