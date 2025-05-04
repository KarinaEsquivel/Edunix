package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class Portada : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Oculta la ActionBar si está presente
        supportActionBar?.hide()

        // Establece el layout de portada
        setContentView(R.layout.portada)

        // Temporizador para ir al LoginActivity después de 5 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Finaliza esta actividad para que no se pueda volver con el botón atrás
        }, 5000)
    }
}
