package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MathGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_game)

        val btnStartGame: ImageButton = findViewById(R.id.flame) // Asegúrate de que este ID coincide con el del XML

        btnStartGame.setOnClickListener {
            val intent = Intent(this, Mathgame1::class.java)
            startActivity(intent)
        }
    }
}

