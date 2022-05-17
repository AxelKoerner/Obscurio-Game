package de.hft.ubq

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ubq.R
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.DecelerateInterpolator
import kotlinx.android.synthetic.main.activity_gm_main.*


/*                                   Global Variables                             */



class MainActivityGM : AppCompatActivity() {

    val shared_Preferences:String = "shared_Preferences"
    lateinit var countDownTimer: CountDownTimer
    private var countDownProgress: Int = 60000



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gm_main)

        val mediaPlayer = MediaPlayer.create(this, R.raw.music)
        //mediaPlayer?.start()
        mediaPlayer?.isLooping = true
        //countdown()   <-- crashed die app





        //-----------------------------onClick Listener------------------------------//

        val button1 = findViewById<ImageButton>(R.id.Picture1_gm)
        val button2 = findViewById<ImageButton>(R.id.Picture2_gm)
        val button3 = findViewById<ImageButton>(R.id.Picture3_gm)

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
                timer.text = countDownProgress.toString()
            }

            override fun onFinish() {
                timer.setText("done!")
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