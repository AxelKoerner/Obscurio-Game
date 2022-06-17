package de.hft.ubq


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.ubq.R

class MainMenu : AppCompatActivity()  {
    val shared_Preferences:String = "shared_Preferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)


        //Navigation Drawer
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()


        //kümmert sich um die Musik
        BackgroundSoundService.playAudio(this, R.raw.song2)

        val bttNeuSpiel = findViewById<Button>(R.id.ButtonNeuSpiel)
        val bttSpielBeit = findViewById<Button>(R.id.ButtonSpielBeit)
        val bttOpt = findViewById<Button>(R.id.ButtonOpt)
        val bttBeenden = findViewById<Button>(R.id.ButtonEsc)


        bttNeuSpiel.setOnClickListener {
            val intent = Intent(this, Modus::class.java)
            clearPreferences()
            startActivity(intent)
        }

        bttSpielBeit.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        bttOpt.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }

        bttBeenden.setOnClickListener {
            finish()
            System.exit(0)
        }



    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
        super.onBackPressed() }
    }



    fun clearPreferences(){

        val sharedPreferences = getSharedPreferences(shared_Preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.apply{
            putInt("overallVotes", 0)
            putBoolean("PlayerTurn", false)
            putBoolean("online", false)
            putInt("maxRounds", 7)
            putInt("PlayerNumber", 3)
            putInt("PlayerChoice", 700015)
            putBoolean("gmDone", false)
            putInt("correctDoor",0)
            putInt("correctVotes", 0)
            putInt("Lifepoints", 9)
            putInt("Round", 1)
            putString("UsedPicture", "")
            putBoolean("isRunning", false)
            putInt("Picture1", 700015)
            putInt("Picture2", 700015)
            putInt("Picture3", 700015)
            putInt("Picture4", 700015)
            putInt("Picture5", 700015)
            putInt("Picture6", 700015)
            putInt("Picture7", 700015)
            putInt("ChosenReference1", 700015)
            putInt("ChosenReference2", 700015)
            putInt("PositionX_ChosenReference1", 100)
            putInt("PositionY_ChosenReference1", 100)
            putInt("PositionX_ChosenReference2", 100)
            putInt("PositionY_ChosenReference2", 100)
            putInt("Picture1Main", 700015)
            putInt("Picture2Main", 700015)
            putInt("Picture3Main", 700015)
            putInt("Picture4Main", 700015)
            putInt("Picture5Main", 700015)
            putInt("Picture6Main", 700015)
            putString("AvailablePictures", "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20," +
                    "21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40," +
                    "41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60," +
                    "61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76")



        }.apply()
    }
}