package com.example.exloginregis

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_regiz.*

class RegisActivity: AppCompatActivity() {
    private lateinit var db: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    db = FirebaseDatabase.getInstance().getReference("Users")

        sign_up_ek1.setOnClickListener {
            SingupUsersdata()
        }
    }

    private fun SingupUsersdata() {
        val email = enter_your_email_ek1.text.toString()
        val pass = password_ek1.text.toString()
        //day du lieu
        val iduser = db.push().key!!
        val user = UserModel(iduser, email, pass)
        //check
        if (email.isEmpty()) {
            enter_your_email_ek1.error = "Email is required"
            return
        }
        if (pass.isEmpty()) {
            password_ek1.error = "Password is required"
            return
        }
        db.child(iduser).setValue(user)
            .addOnCanceledListener {
                Toast.makeText(this, "Regis thanh cong", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                enter_your_email_ek1.setText("")
                password_ek1.setText("")
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "${err.message}", Toast.LENGTH_SHORT).show()
            }
    }
}