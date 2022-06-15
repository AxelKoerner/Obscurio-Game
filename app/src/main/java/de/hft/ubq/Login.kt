package de.hft.ubq

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ubq.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var  mAuth: FirebaseAuth

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth= FirebaseAuth.getInstance()
        database = Firebase.database.reference

        val loginbtn = findViewById<Button>(R.id.loginButton)
        val backButton = findViewById<Button>(R.id.BackLogin)

        loginbtn.setOnClickListener{
            login()
        }

        backButton.setOnClickListener {
            newUser("Son")
        }
    }

    private fun login() {
        val email = emailText.text.toString()

        val pass = passwordText.text.toString()

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
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

    fun newUser(username : String){
        database.child("$username").child("overallVotes").setValue(0)
        database.child("$username").child("PlayerTurn").setValue(false)
        database.child("$username").child("online").setValue(true)
        database.child("$username").child("maxRounds").setValue(7)
        database.child("$username").child("PlayerNumber").setValue(3)
        database.child("$username").child("PlayerChoice").setValue(700015)
        database.child("$username").child("gmDone").setValue(false)
        database.child("$username").child("correctDoor").setValue(0)
        database.child("$username").child("correctVotes").setValue(0)
        database.child("$username").child("Lifepoints").setValue(9)
        database.child("$username").child("Round").setValue(1)
        database.child("$username").child("UsedPicture").setValue("")
        database.child("$username").child("isRunning").setValue(false)
        database.child("$username").child("Picture1").setValue(700015)
        database.child("$username").child("Picture2").setValue(700015)
        database.child("$username").child("Picture3").setValue(700015)
        database.child("$username").child("Picture4").setValue(700015)
        database.child("$username").child("Picture5").setValue(700015)
        database.child("$username").child("Picture6").setValue(700015)
        database.child("$username").child("Picture7").setValue(700015)
        database.child("$username").child("ChosenReference1").setValue(700015)
        database.child("$username").child("ChosenReference2").setValue(700015)
        database.child("$username").child("PositionX_ChosenReference1").setValue(100)
        database.child("$username").child("PositionY_ChosenReference1").setValue(100)
        database.child("$username").child("PositionX_ChosenReference2").setValue(100)
        database.child("$username").child("PositionX_ChosenReference2").setValue(100)
        database.child("$username").child("Picture1Main").setValue(700015)
        database.child("$username").child("Picture2Main").setValue(700015)
        database.child("$username").child("Picture3Main").setValue(700015)
        database.child("$username").child("Picture4Main").setValue(700015)
        database.child("$username").child("Picture5Main").setValue(700015)
        database.child("$username").child("Picture6Main").setValue(700015)
    }
}