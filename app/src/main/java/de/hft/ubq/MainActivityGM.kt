package de.hft.ubq

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.view.animation.DecelerateInterpolator
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ubq.R
import kotlinx.android.synthetic.main.activity_gm_main.*
import kotlinx.android.synthetic.main.activity_gm_reference_choice.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


/*                                   Global Variables                             */



class MainActivityGM : AppCompatActivity() {

    val shared_Preferences:String = "shared_Preferences"
    lateinit var countDownTimer: CountDownTimer
    private var countDownProgress: Int = 60000
    var availablePictures = IntArray(69)
    var gmDone = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gm_main)
        supportActionBar?.hide()
        val mediaPlayer = MediaPlayer.create(this, R.raw.music)
        //mediaPlayer?.start()
        mediaPlayer?.isLooping = true
        //countdown()   <-- crashed die app





        //-----------------------------onClick Listener------------------------------//

        val button1 = findViewById<ImageButton>(R.id.Picture1_gm)
        val button2 = findViewById<ImageButton>(R.id.Picture2_gm)
        val button3 = findViewById<ImageButton>(R.id.Picture3_gm)
        val confirm = findViewById<Button>(R.id.confirm_gm_main)

        loadImages()

        fun openPictureFullscreenGM(button: ImageButton) {
            val intent = Intent(this, PictureFullscreenGM::class.java)
            var resource = "Picture0"
            if(button==button1){
                resource = "ChosenReference1"
            }
            if(button==button2){
                resource = "ChosenReference2"
            }
            intent.putExtra("chosenImage", resource)
            startActivity(intent)
        }

        button1.setOnClickListener {
            openPictureFullscreenGM(button1)
        }

        button2.setOnClickListener {
            openPictureFullscreenGM(button2)
        }

        button3.setOnClickListener {
            openPictureFullscreen()
        }

        confirm.setOnClickListener{//TODO send shared Preferences to other Players
            val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
            gmDone = sharedPreferences.getBoolean("gmDone", false)
            val editor = sharedPreferences.edit()
            if(!gmDone) {
                shufflePlayerDoors()
                gmDone = true
                editor.apply(){
                    putBoolean("gmDone", true)
                }.apply()
            }
            if (gmDone){
                Toast.makeText(this, "Arrow Position already confirmed", Toast.LENGTH_SHORT)
            }
        }



        val pb = findViewById<View>(R.id.progressbar) as ProgressBar

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





    fun openPictureFullscreen() {
        val intent = Intent(this, PictureFullscreen::class.java)
        var resource = "Picture7"

        intent.putExtra("chosenImage", resource)
        intent.putExtra("origin", "MainActivityGM")
        startActivity(intent)
    }

    fun provideDoor(): Int{
        val max = availablePictures.size
        val gate = (Math.random() * (max + 1)).toInt()
        val mutaMapPictures = availablePictures.toMutableList()
        var output = mutaMapPictures.removeAt(gate-1)
        availablePictures = mutaMapPictures.toIntArray()
        availablePictures.shuffle()
        return output
    }


    fun shufflePlayerDoors(){
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
        val editor = sharedPreferences.edit()

        val maxRightGate = 6
        var rightGate = (Math.random() * (maxRightGate + 1)).toInt()




        when (rightGate){
            1 -> {

                editor.apply{
                    putInt("correctDoor", 1)
                    putInt("Picture1Main", Picture7_ReferenceChoice.getTag().toString().toInt())
                    putInt("Picture2Main", provideDoor())
                    putInt("Picture3Main", provideDoor())
                    putInt("Picture4Main", provideDoor())
                    putInt("Picture5Main", provideDoor())
                    putInt("Picture6Main", provideDoor())
                }.apply()
            }
            2 -> {

                editor.apply{
                    putInt("correctDoor", 2)
                    putInt("Picture2Main", Picture7_ReferenceChoice.getTag().toString().toInt())
                    putInt("Picture1Main", provideDoor())
                    putInt("Picture3Main", provideDoor())
                    putInt("Picture4Main", provideDoor())
                    putInt("Picture5Main", provideDoor())
                    putInt("Picture6Main", provideDoor())
                }.apply()
            }
            3 -> {

                editor.apply{
                    putInt("correctDoor", 3)
                    putInt("Picture3Main", Picture7_ReferenceChoice.getTag().toString().toInt())
                    putInt("Picture2Main", provideDoor())
                    putInt("Picture1Main", provideDoor())
                    putInt("Picture4Main", provideDoor())
                    putInt("Picture5Main", provideDoor())
                    putInt("Picture6Main", provideDoor())
                }.apply()
            }
            4 -> {

                editor.apply{
                    putInt("correctDoor", 4)
                    putInt("Picture4Main", Picture7_ReferenceChoice.getTag().toString().toInt())
                    putInt("Picture2Main", provideDoor())
                    putInt("Picture3Main", provideDoor())
                    putInt("Picture1Main", provideDoor())
                    putInt("Picture5Main", provideDoor())
                    putInt("Picture6Main", provideDoor())
                }.apply()
            }
            5 -> {

                editor.apply{
                    putInt("correctDoor", 5)
                    putInt("Picture5Main", Picture7_ReferenceChoice.getTag().toString().toInt())
                    putInt("Picture2Main", provideDoor())
                    putInt("Picture3Main", provideDoor())
                    putInt("Picture4Main", provideDoor())
                    putInt("Picture1Main", provideDoor())
                    putInt("Picture6Main", provideDoor())
                }.apply()
            }
            6 -> {

                editor.apply{
                    putInt("correctDoor", 6)
                    putInt("Picture6Main", Picture7_ReferenceChoice.getTag().toString().toInt())
                    putInt("Picture2Main", provideDoor())
                    putInt("Picture3Main", provideDoor())
                    putInt("Picture4Main", provideDoor())
                    putInt("Picture5Main", provideDoor())
                    putInt("Picture1Main", provideDoor())
                }.apply()
            }
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
    fun loadImages(){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)


        var savedInt = sharedPreferences.getInt("ChosenReference1", 700015)
        Picture1_gm.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("ChosenReference2", 700015)
        Picture2_gm.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture7", 700015)
        Picture3_gm.setImageResource(savedInt)
    }

}