package com.example.exloginregis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_regiz.*
import kotlinx.android.synthetic.main.activity_regiz.sign_in_ek6
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

        val btnSignUp = findViewById<TextView>(R.id.sign_up)
        btnSignUp.setOnClickListener {
            val intent = Intent(this, RegisActivity::class.java)
            startActivity(intent)
        }

        val btnSignIn = findViewById<Button>(R.id.sign_in_ek6)
        btnSignIn.setOnClickListener {
            SigninUser()
        }
        val btnforgot = findViewById<TextView>(R.id.fogot_password)
        btnforgot.setOnClickListener {
            val intent = Intent(this, ResetpassActivity::class.java)
            startActivity(intent)
        }
        val check_save = findViewById<CheckBox>(R.id.check_save)
    }

    override fun onResume() {
        super.onResume()
        val sharePreference = getSharedPreferences("Login", MODE_PRIVATE)
        val email = sharePreference.getString("email", null)
        val pass = sharePreference.getString("password", null)
        val save = sharePreference.getBoolean("save", false)
        if(save){
            enter_your_email.setText(email)
            password.setText(pass)
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
                //lưu thông tin đăng nhập
                val sharePreference = getSharedPreferences("Login", MODE_PRIVATE)
                val editor = sharePreference.edit()
                //lưu theo dạng phân rã
                editor.putString("email", email)
                editor.putString("password", pass)
                editor.putBoolean("save", check_save.isChecked)
                editor.apply()

                Toast.makeText(this, "đã lưu thông tin đn", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity()::class.java))
                finish()
            } else {
                val exception = task.exception
                val errorMessage = exception?.message
                Toast.makeText(baseContext,"Authentication failed: $errorMessage",Toast.LENGTH_SHORT).show()
            }
        }
    }


}