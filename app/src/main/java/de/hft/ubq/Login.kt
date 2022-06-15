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
import java.util.*

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
        val registerbtn = findViewById<Button>(R.id.registerbutton)

        loginbtn.setOnClickListener{
            login()
        }

        backButton.setOnClickListener {
            finish()
        }

        registerbtn.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        val email = emailTextLogin.text.toString()

        val pass = passwordTextLogin.text.toString()

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                newUser(email)
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }

    fun getdata(dbchild : String, dbchild2 : String) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //TODO change as String to right datatype
                var test = snapshot.child(dbchild).child(dbchild2).getValue()
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })
    }

    fun newUser(username : String){
        var st = StringTokenizer(username, ".")
        var user = st.nextToken()
        database.child(user).child("overallVotes").setValue(0)
        database.child(user).child("PlayerTurn").setValue(false)
        database.child(user).child("online").setValue(true)
        database.child(user).child("maxRounds").setValue(7)
        database.child(user).child("PlayerNumber").setValue(3)
        database.child(user).child("PlayerChoice").setValue(700015)
        database.child(user).child("gmDone").setValue(false)
        database.child(user).child("correctDoor").setValue(0)
        database.child(user).child("correctVotes").setValue(0)
        database.child(user).child("Lifepoints").setValue(9)
        database.child(user).child("Round").setValue(1)
        database.child(user).child("UsedPicture").setValue("")
        database.child(user).child("isRunning").setValue(false)
        database.child(user).child("Picture1").setValue(700015)
        database.child(user).child("Picture2").setValue(700015)
        database.child(user).child("Picture3").setValue(700015)
        database.child(user).child("Picture4").setValue(700015)
        database.child(user).child("Picture5").setValue(700015)
        database.child(user).child("Picture6").setValue(700015)
        database.child(user).child("Picture7").setValue(700015)
        database.child(user).child("ChosenReference1").setValue(700015)
        database.child(user).child("ChosenReference2").setValue(700015)
        database.child(user).child("PositionX_ChosenReference1").setValue(100)
        database.child(user).child("PositionY_ChosenReference1").setValue(100)
        database.child(user).child("PositionX_ChosenReference2").setValue(100)
        database.child(user).child("PositionX_ChosenReference2").setValue(100)
        database.child(user).child("Picture1Main").setValue(700015)
        database.child(user).child("Picture2Main").setValue(700015)
        database.child(user).child("Picture3Main").setValue(700015)
        database.child(user).child("Picture4Main").setValue(700015)
        database.child(user).child("Picture5Main").setValue(700015)
        database.child(user).child("Picture6Main").setValue(700015)
    }
}