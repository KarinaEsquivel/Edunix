package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etEdad: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etEdad = findViewById(R.id.etEdad)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        sharedPreferences = getSharedPreferences("Usuarios", Context.MODE_PRIVATE)

        btnRegistrar.setOnClickListener { registrarUsuario() }
    }

    private fun registrarUsuario() {
        val nombre = etNombre.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val edadStr = etEdad.text.toString().trim()

        if (nombre.isEmpty() || correo.isEmpty() || edadStr.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Correo no válido", Toast.LENGTH_SHORT).show()
            return
        }

        val edad = edadStr.toIntOrNull()
        if (edad == null || edad <= 0) {
            Toast.makeText(this, "Edad no válida", Toast.LENGTH_SHORT).show()
            return
        }

        val editor = sharedPreferences.edit()
        editor.putString("nombre", nombre)
        editor.putString("correo", correo)
        editor.putInt("edad", edad)
        editor.putBoolean("primerInicio$correo", true)
        editor.apply()

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
