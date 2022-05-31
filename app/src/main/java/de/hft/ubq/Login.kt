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

class Login : AppCompatActivity() {

    private lateinit var  mAuth: FirebaseAuth

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth= FirebaseAuth.getInstance()
        database = Firebase.database.reference
        database.child("Users").child("a").setValue("b")

        val loginbtn = findViewById<Button>(R.id.loginButton)
        val backButton = findViewById<Button>(R.id.BackLogin)

        loginbtn.setOnClickListener{
            signUp()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        backButton.setOnClickListener {
            finish()
        }
    }

    private fun signUp() {
        var email = emailText.text.toString().trim()
        var password = passwordText.text.toString().trim()

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener{
                val firebaseUser = mAuth.currentUser
                val email = firebaseUser!!.email
            }
    }
}