package de.hft.ubq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ubq.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    private lateinit var  mAuth: FirebaseAuth

    private lateinit var database : DatabaseReference

    var test = "Axel.de"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mAuth= FirebaseAuth.getInstance()
        database = Firebase.database.reference

        val signbtn = findViewById<Button>(R.id.SignUpButton)

        val signInbtn = findViewById<Button>(R.id.signIn)

        signbtn.setOnClickListener{
            newUser(test)
        }

        signInbtn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }

    private fun signUp() {
        val email = SignUpemail.text.toString().trim()
        val password = signUpPassword.text.toString().trim()

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    finish()
                }
            }
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