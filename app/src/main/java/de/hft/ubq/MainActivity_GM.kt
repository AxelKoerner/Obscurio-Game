package com.example.ubq

import android.content.Intent
import android.graphics.*
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import de.hft.ubq.PictureFullscreen
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity_GM : AppCompatActivity() {

    lateinit var countDownTimer: CountDownTimer
    private var countDownProgress: Int = 60000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gm_main)

        val mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer?.start()
        mediaPlayer?.isLooping = true
        countdown()




        //-----------------------------onClick Listener------------------------------//

        val button1 = findViewById<ImageButton>(R.id.Picture1_gm)
        val button2 = findViewById<ImageButton>(R.id.Picture2_gm)
        val button3 = findViewById<ImageButton>(R.id.Picture3_gm)





        button1.setOnClickListener{
            //pictureFull.setBackgroundResource(R.drawable.pot)
            openPictureFullscreen()
        }

        button2.setOnClickListener {
           openPictureFullscreen()
        }

        button3.setOnClickListener {
            openPictureFullscreen()
        }
    }

    fun openPictureFullscreen() {
        val intent = Intent(this, PictureFullscreen::class.java)
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

    fun toRoundCorner(bitmap: Bitmap, pixels: Int): Bitmap? {
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        val roundPx = pixels.toFloat()
        paint.setAntiAlias(true)
        canvas.drawARGB(0, 0, 0, 0)
        paint.setColor(color)
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }



}