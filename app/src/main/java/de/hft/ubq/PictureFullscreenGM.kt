package de.hft.ubq

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.ubq.R
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_gm_reference_choice.*

class PictureFullscreenGM : AppCompatActivity() {
    val shared_Preferences:String = "shared_Preferences"
    private var xDelta = 0
    private var yDelta = 0
    private var mainLayout: ViewGroup? = null
    private var image: ImageView? = null
    private lateinit var database : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_fullscreen_gm)
        supportActionBar?.hide()

        database = Firebase.database.reference

        mainLayout = findViewById<View>(R.id.fullscreenGM) as RelativeLayout
        image = findViewById<View>(R.id.pointer) as ImageView
        image!!.setOnTouchListener(onTouchListener())



        val confirm = findViewById<Button>(R.id.confirm_fullscreen_arrow)
        val pictureFull = findViewById<ImageButton>(R.id.Button_fullscreen)
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
            finish()

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
        var pointerRelativeLayout = image!!.layoutParams as RelativeLayout.LayoutParams

        editor.apply{
            putInt("PositionX_"+res_image, pointerRelativeLayout.leftMargin)
            putInt("PositionY_"+res_image, pointerRelativeLayout.topMargin)

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

    fun getdataInt(dbchild : String, dbchild2 : String) : Int {
        var zahl = 0
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                zahl = snapshot.child(dbchild).child(dbchild2).getValue() as Int
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
        return zahl
    }

    fun setData(dbchild2 : String, saveValue : String, datatype : String) {
        when(datatype){
            "Int"->{
                database.child("$dbchild2").setValue(saveValue.toInt())
            }
            "String"->{
                database.child("$dbchild2").setValue("$saveValue")
            }
            "Boolean"->{
                if (saveValue == "true"){
                    database.child("$dbchild2").setValue(true)
                }
                if (saveValue == "false"){
                    database.child("$dbchild2").setValue(false)
                }
            }
        }
    }



}