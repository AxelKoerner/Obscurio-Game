package de.hft.ubq

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
    private var countDownProgress: Int = 180000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        fun openPictureFullscreen() {
            val intent = Intent(this, PictureFullscreenGM::class.java)
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


        button1.setOnClickListener{
            openPictureFullscreen()
        }

        button2.setOnClickListener {
           openPictureFullscreen()
        }

        button3.setOnClickListener {
            openPictureFullscreen()
        }

        button4.setOnClickListener {
            openPictureFullscreen()
        }

        button5.setOnClickListener {
            openPictureFullscreen()
        }

        button6.setOnClickListener {
            openPictureFullscreen()
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