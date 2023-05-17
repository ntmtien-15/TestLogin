package com.example.exloginregis

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class ResetpassActivity:AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpass)

        auth = Firebase.auth
        val etemail = findViewById<EditText>(R.id.reset_email)
        val btnforgotpass = findViewById<TextView>(R.id.btnreset)
        btnforgotpass.setOnClickListener {
            val pass = etemail.text.toString()
            auth.sendPasswordResetEmail(pass)
                .addOnCompleteListener {
                    Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginAvctivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }
}