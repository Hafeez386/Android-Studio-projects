package com.example.oel1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            startActivity(Intent(this, RegisterStudentActivity::class.java))
        }
        findViewById<Button>(R.id.btnView).setOnClickListener {
            startActivity(Intent(this, StudentListActivity::class.java))
        }
    }
}