package com.example.layout23

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

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

        // 1. LinearLayout Demo Card
        findViewById<MaterialCardView>(R.id.cardLinearLayout).setOnClickListener {
            startActivity(Intent(this, LinearLayoutActivity::class.java))
        }

        // 2. RelativeLayout Demo Card
        findViewById<MaterialCardView>(R.id.cardRelativeLayout).setOnClickListener {
            startActivity(Intent(this, RelativeLayoutActivity::class.java))
        }

        // 3. ConstraintLayout Demo Card
        findViewById<MaterialCardView>(R.id.cardConstraintLayout).setOnClickListener {
            startActivity(Intent(this, ConstraintLayoutActivity::class.java))
        }

        // 4. FrameLayout Demo Card
        findViewById<MaterialCardView>(R.id.cardFrameLayout).setOnClickListener {
            startActivity(Intent(this, FrameLayoutActivity::class.java))
        }

        // 5. TableLayout Demo Card
        findViewById<MaterialCardView>(R.id.cardTableLayout).setOnClickListener {
            startActivity(Intent(this, TableLayoutActivity::class.java))
        }

        // 6. GridLayout Demo Card
        findViewById<MaterialCardView>(R.id.cardGridLayout).setOnClickListener {
            startActivity(Intent(this, GridLayoutActivity::class.java))
        }
    }
}