package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var tvRegistrar: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Vincular vistas con sus IDs
        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        tvRegistrar = findViewById(R.id.tvRegistrar)

        sharedPreferences = getSharedPreferences("Usuarios", Context.MODE_PRIVATE)

        // Acciones de los botones
        btnIniciarSesion.setOnClickListener { iniciarSesion() }
        tvRegistrar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun iniciarSesion() {
        val nombreIngresado = etNombre.text.toString().trim()
        val correoIngresado = etCorreo.text.toString().trim()

        // Validaciones
        if (nombreIngresado.isEmpty() || correoIngresado.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correoIngresado).matches()) {
            Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show()
            return
        }

        val nombreGuardado = sharedPreferences.getString("nombre", null)
        val correoGuardado = sharedPreferences.getString("correo", null)

        if (nombreIngresado == nombreGuardado && correoIngresado == correoGuardado) {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

            val esPrimerInicio = sharedPreferences.getBoolean("primerInicio$correoIngresado", true)

            if (esPrimerInicio) {
                enviarCorreo(
                    correoIngresado,
                    "Bienvenido a la App de Mini Juegos",
                    "Hola $nombreIngresado,\n\n¡Bienvenido a nuestra aplicación! Esperamos que disfrutes los mini juegos y te diviertas mucho. 😊🎮"
                )
                sharedPreferences.edit().putBoolean("primerInicio$correoIngresado", false).apply()
            } else {
                enviarCorreo(
                    correoIngresado,
                    "¡Nos alegra verte de nuevo!",
                    "Hola $nombreIngresado,\n\n¡Bienvenido de nuevo a nuestra aplicación! Gracias por seguir disfrutando de nuestros mini juegos. 🎮😃"
                )
            }

            startActivity(Intent(this, CategoryActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun enviarCorreo(destinatario: String, asunto: String, mensaje: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$destinatario")
            putExtra(Intent.EXTRA_SUBJECT, asunto)
            putExtra(Intent.EXTRA_TEXT, mensaje)
        }

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "No se encontró una app de correo", Toast.LENGTH_SHORT).show()
        }
    }
}
