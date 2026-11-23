package com.example.question1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnStartTracking = findViewById<Button>(R.id.btnStartTracking)
        val progressBarLoading = findViewById<ProgressBar>(R.id.progressBarLoading)
        val progressBarSteps = findViewById<ProgressBar>(R.id.progressBarSteps)
        val tvStepCount = findViewById<TextView>(R.id.tvStepCount)

        btnStartTracking.setOnClickListener {
            // Show the loading progress bar when tracking starts
            progressBarLoading.visibility = View.VISIBLE
            btnStartTracking.text = "Tracking Activity..."
            btnStartTracking.isEnabled = false

            // Update step progress
            progressBarSteps.progress = 8000
            tvStepCount.text = "8,000 / 10,000 Steps (80%)"

            Toast.makeText(this, "Activity Tracking Started!", Toast.LENGTH_SHORT).show()
        }
    }
}