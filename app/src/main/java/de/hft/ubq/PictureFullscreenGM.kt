package de.hft.ubq

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ubq.R
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_gm_reference_choice.*

class PictureFullscreenGM : AppCompatActivity() {
    val shared_Preferences:String = "shared_Preferences"
    private var xDelta = 0
    private var yDelta = 0
    private var mainLayout: ViewGroup? = null
    private var image: ImageView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_fullscreen_gm)


        mainLayout = findViewById<View>(R.id.fullscreenGM) as RelativeLayout
        image = findViewById<View>(R.id.Arrow) as ImageView
        image!!.setOnTouchListener(onTouchListener())


        fun returnToMain() {
            val intent = Intent(this, MainActivityGM::class.java)
            startActivity(intent)
        }

        val confirm = findViewById<Button>(R.id.confirm_fullscreen_arrow)
        val pictureFull = findViewById<ImageButton>(R.id.Button_fullscreen)
        var bundle = getIntent().getExtras()

        if (bundle != null) {
            var res_image = bundle.getString("chosenImage")
            val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)


            var savedInt = sharedPreferences.getInt(res_image, 700015)
            pictureFull.setImageResource(savedInt)
        }

        pictureFull.setOnClickListener(View.OnClickListener {
            returnToMain()

        })

        confirm.setOnClickListener(View.OnClickListener {
            savePosition()
        })


    }

    private fun savePosition(){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var bundle = getIntent().getExtras()
        var res_image = bundle?.getString("chosenImage")

        editor.apply{
            putInt("PositionX_"+res_image, xDelta)
            putInt("PositionY_"+res_image, yDelta)

        }.apply()

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
    }

    private fun onTouchListener(): OnTouchListener {
        return OnTouchListener { view, event ->
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    val lParams = view.layoutParams as RelativeLayout.LayoutParams
                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin
                }


                MotionEvent.ACTION_MOVE -> {
                    val layoutParams = view
                        .layoutParams as RelativeLayout.LayoutParams
                    layoutParams.leftMargin = x - xDelta
                    layoutParams.topMargin = y - yDelta
                    layoutParams.rightMargin = 0
                    layoutParams.bottomMargin = 0
                    view.layoutParams = layoutParams
                }
            }
            mainLayout!!.invalidate()
            true
        }
    }



}