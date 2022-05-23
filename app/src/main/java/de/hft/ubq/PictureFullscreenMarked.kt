package de.hft.ubq

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.ubq.R

class PictureFullscreenMarked : AppCompatActivity() {
    val shared_Preferences:String = "shared_Preferences"
    private var xDelta = 0
    private var yDelta = 0
    private var mainLayout: ViewGroup? = null
    private var image: ImageView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_fullscreen_marked)


        mainLayout = findViewById<View>(R.id.fullscreenMarked) as RelativeLayout
        image = findViewById<View>(R.id.pointer_marked) as ImageView




        fun returnToMain() {
           finish()
        }


        val pictureFull = findViewById<ImageButton>(R.id.Button_fullscreen_marked)
        var bundle = getIntent().getExtras()

        if (bundle != null) {
            var res_image = bundle.getString("chosenImage")
            val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)


            var savedInt = sharedPreferences.getInt(res_image, 700015)
            pictureFull.setImageResource(savedInt)


            if(res_image=="ChosenReference1"){
                var x = sharedPreferences.getInt("PositionX_ChosenReference1", 100)
                var y = sharedPreferences.getInt("PositionY_ChosenReference1", 100)

                val view = image

                if(x!=100 && y!=100){
                    var pointerRelativeLayout = view!!.layoutParams as RelativeLayout.LayoutParams
                    pointerRelativeLayout.setMargins(x ,y,0,0)
                    image!!.layoutParams = pointerRelativeLayout
                }

            }


            if(res_image=="ChosenReference2"){
                var x = sharedPreferences.getInt("PositionX_ChosenReference2", 100)
                var y = sharedPreferences.getInt("PositionY_ChosenReference2", 100)

                val view = image

                if(x!=100 && y!=100){
                    var pointerRelativeLayout = view!!.layoutParams as RelativeLayout.LayoutParams
                    pointerRelativeLayout.setMargins(x ,y,0,0)
                    image!!.layoutParams = pointerRelativeLayout
                }
            }
        }

        pictureFull.setOnClickListener(View.OnClickListener {
            returnToMain()

        })

    }
}



