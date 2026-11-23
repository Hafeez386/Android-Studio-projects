package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var tvSignUpLink: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        etEmail = findViewById(R.id.etLoginEmail)
        etPassword = findViewById(R.id.etLoginPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignUpLink = findViewById(R.id.tvSignUpLink)
        progressBar = findViewById(R.id.loginProgressBar)

        btnLogin.setOnClickListener { loginUser() }
        tvSignUpLink.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun loginUser() {
        val email = etEmail.text?.toString()?.trim().orEmpty()
        val password = etPassword.text?.toString()?.trim().orEmpty()

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Enter a valid email"
            return
        }
        if (password.isEmpty()) {
            etPassword.error = "Password is required"
            return
        }

        showLoading(true)

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                showLoading(false)
                if (task.isSuccessful) {
                    Toast.makeText(this, "Logged in successfully!", Toast.LENGTH_SHORT).show()
                    // Navigate to main/home screen if needed
                } else {
                    Toast.makeText(
                        this,
                        "Login failed: ${task.exception?.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        btnLogin.isEnabled = !isLoading
    }
}
