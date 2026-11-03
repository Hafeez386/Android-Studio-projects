package com.example.oel1


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        findViewById<TextView>(R.id.tvName).text =
            "Name: ${intent.getStringExtra("name")}"
        findViewById<TextView>(R.id.tvRegNo).text =
            "Reg No: ${intent.getStringExtra("regNo")}"
        findViewById<TextView>(R.id.tvEmail).text =
            "Email: ${intent.getStringExtra("email")}"
        findViewById<TextView>(R.id.tvGender).text =
            "Gender: ${intent.getStringExtra("gender")}"
        findViewById<TextView>(R.id.tvDepartment).text =
            "Department: ${intent.getStringExtra("department")}"
    }
}