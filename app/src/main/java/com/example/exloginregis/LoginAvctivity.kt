package com.example.exloginregis

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_regiz.*
import kotlinx.android.synthetic.main.activity_regiz.sign_in_ek6
import kotlinx.android.synthetic.main.activity_regiz.sign_up_ek1

class LoginAvctivity : AppCompatActivity() {
    private lateinit var auth: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseDatabase.getInstance().getReference("Users")
        sign_in_ek6.setOnClickListener {
            SigninUser()
        }
        sign_up.setOnClickListener {
            val intent = Intent(this, RegisActivity::class.java)
            startActivity(intent)
        }
    }

    private fun SigninUser(){
        val email = enter_your_email.text.toString()
        val pass = password.text.toString()
        if (email.isEmpty()){
            enter_your_email.error = "Email is required"
            return
        }
        if (pass.isEmpty()){
            password.error = "Password is required"
            return
        }
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Đăng nhập thành công, chuyển đến màn hình chính
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                // Đăng nhập thất bại, hiển thị thông báo lỗi
                val exception = task.exception
                val errorMessage = exception?.message
                Toast.makeText(baseContext, "Authentication failed: $errorMessage", Toast.LENGTH_SHORT).show()
        }

        }
    }


}