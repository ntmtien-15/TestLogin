package com.example.exloginregis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_regiz.*

class RegisActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regiz)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val btnRegister = findViewById<Button>(R.id.sign_in_ek6)

        btnRegister.setOnClickListener {
            SingupUsersdata()
        }
        sign_in_ek6.setOnClickListener {
            SingupUsersdata()
        }
    }

    private fun SingupUsersdata() {
        val email = enter_your_email_ek1.text.toString()
        val pass = password_ek1.text.toString()
        val passagain = again_password.text.toString()


        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass != passagain) {
            again_password.error = "Passwords don't match"
            return
        }
        //day du lieu
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                Toast.makeText(baseContext, "Authentication success.", Toast.LENGTH_SHORT,).show()
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText( baseContext, "Authentication failed.", Toast.LENGTH_SHORT, ).show()
            }
        }.addOnFailureListener {
                Toast.makeText( baseContext, "Authentication failed. ${it.localizedMessage}", Toast.LENGTH_SHORT, ).show()
            }
    }
}