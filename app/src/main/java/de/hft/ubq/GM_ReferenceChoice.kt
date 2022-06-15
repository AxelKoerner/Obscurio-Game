package de.hft.ubq

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ubq.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_gm_reference_choice.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class GM_ReferenceChoice : AppCompatActivity() {
    var isRunning = false
    val shared_Preferences:String = "shared_Preferences"
    var availablePictures = IntArray(76)
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gm_reference_choice)
        supportActionBar?.hide()
        val mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer?.isLooping = true
        database = Firebase.database.reference




        //-----------------------------onClick Listener------------------------------//

        val button1 = findViewById<ImageButton>(R.id.Picture1_ReferenceChoice)
        val button2 = findViewById<ImageButton>(R.id.Picture2_ReferenceChoice)
        val button3 = findViewById<ImageButton>(R.id.Picture3_ReferenceChoice)
        val button4 = findViewById<ImageButton>(R.id.Picture4_ReferenceChoice)
        val button5 = findViewById<ImageButton>(R.id.Picture5_ReferenceChoice)
        val button6 = findViewById<ImageButton>(R.id.Picture6_ReferenceChoice)
        val button7 = findViewById<ImageButton>(R.id.Picture7_ReferenceChoice)
        val confirm = findViewById<Button>(R.id.confirm_ReferenceChoice)
        val lifepoints = findViewById<TextView>(R.id.lebensanzeige_GM)

        updatelifes(lifepoints)

        loadGame()


        if(isRunning==true) {
            loadData()
        }

        if(!isRunning){

            val game = Game()
            shuffleDoors()

            game.matchdoor(Picture1_ReferenceChoice,provideDoor())
            game.matchdoor(Picture2_ReferenceChoice,provideDoor())
            game.matchdoor(Picture3_ReferenceChoice,provideDoor())
            game.matchdoor(Picture4_ReferenceChoice,provideDoor())
            game.matchdoor(Picture5_ReferenceChoice,provideDoor())
            game.matchdoor(Picture6_ReferenceChoice,provideDoor())
            game.matchdoor(Picture7_ReferenceChoice,provideDoor())

            isRunning = true
            saveData()
        }



        fun openMainActivityGM() {
            val intent = Intent(this, MainActivityGM::class.java)
            startActivity(intent)
        }




        fun openPictureFullscreen() {
            val intent = Intent(this, PictureFullscreen::class.java)
            var resource = "Picture7"

            intent.putExtra("chosenImage", resource)
            startActivity(intent)
        }

        fun openPictureFullscreenReferenceChoice(button: ImageButton){
            val intent = Intent(this, PictureFullscreenReferenceChoice::class.java)
            var resource = "Picture0"
            if(button==button1){
                resource = "Picture1"
            }
            if(button==button2){
                resource = "Picture2"
            }
            if(button==button3){
                resource = "Picture3"
            }
            if(button==button4){
                resource = "Picture4"
            }
            if(button==button5){
                resource = "Picture5"
            }
            if(button==button6){
                resource = "Picture6"
            }


            intent.putExtra("chosenImage", resource)
            startActivity(intent)
        }


        button1.setOnClickListener{
            openPictureFullscreenReferenceChoice(button1)
        }

        button2.setOnClickListener {
            openPictureFullscreenReferenceChoice(button2)
        }

        button3.setOnClickListener {
            openPictureFullscreenReferenceChoice(button3)
        }

        button4.setOnClickListener {
            openPictureFullscreenReferenceChoice(button4)
        }

        button5.setOnClickListener {
            openPictureFullscreenReferenceChoice(button5)
        }

        button6.setOnClickListener {
            openPictureFullscreenReferenceChoice(button6)
        }

        button7.setOnClickListener {
            openPictureFullscreen()
        }
        confirm.setOnClickListener{
            val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
            var savedInt =sharedPreferences.getInt("ChosenReference1", 700015)
            var chosenReference1 = savedInt
            savedInt=sharedPreferences.getInt("ChosenReference2", 700015)
            var chosenReference2 = savedInt

            if(chosenReference1 != 700015 && chosenReference2 != 700015){
                openMainActivityGM()
            }
            else{
                Toast.makeText(this, "Chose 2 References to Continue", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed() }
    }
    fun updatelifes(textView: TextView){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        textView.setText("Leben: " + sharedPreferences.getInt("Lifepoints", 9))
    }

    fun shuffleDoors(){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        var savedString = sharedPreferences.getString("AvailablePictures", "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20," +
                "21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40," +
                "41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60," +
                "61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76")
        var st = StringTokenizer(savedString, ",")

        for(i in 1..76){
            availablePictures[i-1] = Integer.parseInt(st.nextToken())
        }
        availablePictures.shuffle()

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.chat -> Toast.makeText(this,"Chat", Toast.LENGTH_SHORT).show()
            R.id.leave -> finishAffinity()
            R.id.option -> setContentView(R.layout.activity_settings)

        }

        return super.onOptionsItemSelected(item)
    }

    fun saveData(){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var str = StringBuilder();
        for( i in 1..availablePictures.size){
            str.append(availablePictures[i-1]).append(",")
        }

        editor.apply{
            putBoolean("isRunning", isRunning)
            putInt("Picture1", Picture1_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture2", Picture2_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture3", Picture3_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture4", Picture4_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture5", Picture5_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture6", Picture6_ReferenceChoice.getTag().toString().toInt())
            putInt("Picture7", Picture7_ReferenceChoice.getTag().toString().toInt())
            putString("AvailablePictures",str.toString())
        }.apply()

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()

    }

    fun loadData(){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)


        var savedInt = sharedPreferences.getInt("Picture1", 700015)
        Picture1_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture2", 700015)
        Picture2_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture3", 700015)
        Picture3_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture4", 700015)
        Picture4_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture5", 700015)
        Picture5_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture6", 700015)
        Picture6_ReferenceChoice.setImageResource(savedInt)

        savedInt = sharedPreferences.getInt("Picture7", 700015)
        Picture7_ReferenceChoice.setImageResource(savedInt)

    }

    fun loadGame(){
        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        var savedBoolean = sharedPreferences.getBoolean("isRunning", false)
        isRunning = savedBoolean
    }

    fun getdataInt(dbchild : String, dbchild2 : String) : Int {
        var zahl = 0
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //TODO change as String to right datatype
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