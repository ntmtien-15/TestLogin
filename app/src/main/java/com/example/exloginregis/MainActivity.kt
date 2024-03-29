package com.example.exloginregis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth

//        text.setText("hello $email")
        val btnlogout = findViewById<Button>(R.id.btnlogout)
        btnlogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginAvctivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}