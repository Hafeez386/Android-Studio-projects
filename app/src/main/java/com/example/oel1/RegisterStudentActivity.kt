package com.example.oel1

import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oel1.model.Student
import com.google.firebase.database.FirebaseDatabase

class RegisterStudentActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etRegNo: EditText
    private lateinit var etEmail: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var spDepartment: Spinner
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_student)

        etName = findViewById(R.id.etStudentName)
        etRegNo = findViewById(R.id.etRegNo)
        etEmail = findViewById(R.id.etStudentEmail)
        rgGender = findViewById(R.id.rgGender)
        spDepartment = findViewById(R.id.spDepartment)
        btnSave = findViewById(R.id.btnSave)

        val departments = listOf(
            "Select Department",
            "Computer Science",
            "Software Engineering",
            "Electrical Engineering",
            "Mechanical Engineering",
            "Business Administration"
        )
        spDepartment.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, departments
        )

        btnSave.setOnClickListener { saveStudent() }
    }

    private fun saveStudent() {
        val name = etName.text.toString().trim()
        val regNo = etRegNo.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val department = spDepartment.selectedItem.toString()

        // Validation
        if (name.isEmpty()) {
            etName.error = "Name is required"
            return
        }
        if (regNo.isEmpty()) {
            etRegNo.error = "Registration number is required"
            return
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Enter a valid email"
            return
        }
        val selectedId = rgGender.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
            return
        }
        if (spDepartment.selectedItemPosition == 0) {
            Toast.makeText(this, "Please select department", Toast.LENGTH_SHORT).show()
            return
        }

        val gender = findViewById<RadioButton>(selectedId).text.toString()

        val ref = FirebaseDatabase.getInstance().reference.child("Students")
        val id = ref.push().key ?: return
        val student = Student(id, name, regNo, email, gender, department)

        ref.child(id).setValue(student).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Student registered successfully!", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(
                    this, "Failed: ${task.exception?.message}", Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}