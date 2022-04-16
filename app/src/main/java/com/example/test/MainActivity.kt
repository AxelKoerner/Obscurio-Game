package com.example.test

import android.graphics.*
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var countDownTimer: CountDownTimer
    private var countDownProgress: Int = 60000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer?.start()
        mediaPlayer?.isLooping = true
        countdown()



        //-----------------------------onClick Listener------------------------------//
        var firstClick = true

        val button1 = findViewById<ImageButton>(R.id.Button1)
        button1.setOnClickListener(View.OnClickListener {
            if(firstClick) {
                Toast.makeText(applicationContext, "Picture was enlarged", Toast.LENGTH_SHORT).show()
                button1.layoutParams.height = 1000
                button1.layoutParams.width = 1000
                button1.setImageResource(R.drawable.pot)
                //button1.elevation(e)
                button1.requestLayout()
                firstClick = false


            }
            else {
                button1.layoutParams.height = 320
                button1.layoutParams.width = 390
                button1.requestLayout()
                firstClick = true
            }
        }
        )


        val button2 = findViewById<ImageButton>(R.id.Button2)
        button2.setOnClickListener(View.OnClickListener {
            if(firstClick) {
                Toast.makeText(applicationContext, "Picture was enlarged", Toast.LENGTH_SHORT).show()
                button2.layoutParams.height = 1000
                button2.layoutParams.width = 1000
                button2.requestLayout()
                firstClick = false

            }
            else {
                button2.layoutParams.height = 320
                button2.layoutParams.width = 390
                button2.requestLayout()
                firstClick = true
            }
        }
        )

        val button3 = findViewById<ImageButton>(R.id.Button3)
        button3.setOnClickListener(View.OnClickListener {
            if(firstClick) {
                Toast.makeText(applicationContext, "Picture was enlarged", Toast.LENGTH_SHORT).show()
                button3.layoutParams.height = 1000
                button3.layoutParams.width = 1000
                button3.requestLayout()
                firstClick = false

            }
            else {
                button3.layoutParams.height = 320
                button3.layoutParams.width = 390
                button3.requestLayout()
                firstClick = true
            }
        }
        )

        val button4 = findViewById<ImageButton>(R.id.Button4)
        button4.setOnClickListener(View.OnClickListener {
            if(firstClick) {
                Toast.makeText(applicationContext, "Picture was enlarged", Toast.LENGTH_SHORT).show()
                button4.layoutParams.height = 1000
                button4.layoutParams.width = 1000
                button4.requestLayout()
                firstClick = false

            }
            else {
                button4.layoutParams.height = 320
                button4.layoutParams.width = 390
                button4.requestLayout()
                firstClick = true
            }
        }
        )

        val button5 = findViewById<ImageButton>(R.id.Button5)
        button5.setOnClickListener(View.OnClickListener {
            if(firstClick) {
                Toast.makeText(applicationContext, "Picture was enlarged", Toast.LENGTH_SHORT).show()
                button5.layoutParams.height = 1000
                button5.layoutParams.width = 1000
                button5.requestLayout()
                firstClick = false

            }
            else {
                button5.layoutParams.height = 320
                button5.layoutParams.width = 390
                button5.requestLayout()
                firstClick = true
            }
        }
        )

        val button6 = findViewById<ImageButton>(R.id.Button6)
        button6.setOnClickListener(View.OnClickListener {
            if(firstClick) {
                Toast.makeText(applicationContext, "Picture was enlarged", Toast.LENGTH_SHORT).show()
                button6.layoutParams.height = 1000
                button6.layoutParams.width = 1000
                button6.requestLayout()
                firstClick = false

            }
            else {
                button6.layoutParams.height = 320
                button6.layoutParams.width = 390
                button6.requestLayout()
                firstClick = true
            }
        }
        )


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