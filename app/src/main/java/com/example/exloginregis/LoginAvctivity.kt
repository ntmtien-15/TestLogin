package com.example.exloginregis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_regiz.*
import kotlinx.android.synthetic.main.activity_regiz.sign_in_ek6
import kotlinx.android.synthetic.main.activity_regiz.sign_up_ek1
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginAvctivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = Firebase.auth

//        auth = FirebaseAuth.getInstance()
//        sign_in_ek6.setOnClickListener {
//            SigninUser()
//        }
        val btnSignUp = findViewById<Button>(R.id.sign_up)

        btnSignUp.setOnClickListener {
            val intent = Intent(this, RegisActivity::class.java)
            startActivity(intent)
        }

        val btnSignIn = findViewById<Button>(R.id.sign_in_ek6)
        btnSignIn.setOnClickListener {
            SigninUser()
        }
    }

    private fun SigninUser(){
        val email = enter_your_email.text.toString()
        val pass = password.text.toString()
        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_SHORT).show()
            return
        }
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
            // Tạo đối tượng User
            if (task.isSuccessful) {
                // Đăng nhập thành công, chuyển đến màn hình chính
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                // Đăng nhập thất bại, hiển thị thông báo lỗi
                val exception = task.exception
                val errorMessage = exception?.message
                Toast.makeText(
                    baseContext,
                    "Authentication failed: $errorMessage",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


}