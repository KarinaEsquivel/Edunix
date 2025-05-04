package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val btnMath = findViewById<Button>(R.id.btnMath)
        btnMath.setOnClickListener {
            val intent = Intent(this, MathGameActivity::class.java)
            startActivity(intent)
        }

        val btnLiterature = findViewById<Button>(R.id.btnLiterature)
        btnLiterature.setOnClickListener {
            val intent = Intent(this, LiteratureGameActivity::class.java)
            startActivity(intent)
        }

        val btnLogic = findViewById<Button>(R.id.btnLogic)
        btnLogic.setOnClickListener {
            val intent = Intent(this, LogicGameActivity::class.java)
            startActivity(intent)
        }
    }
}
