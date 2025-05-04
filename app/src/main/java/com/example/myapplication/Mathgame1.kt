package com.example.myapplication
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class Mathgame1 : AppCompatActivity() {

    private lateinit var tvQuestion: TextView
    private lateinit var etAnswer: EditText
    private lateinit var btnSubmit: Button
    private lateinit var tvScore: TextView
    private lateinit var tvTimer: TextView

    private var num1 = 0
    private var num2 = 0
    private var score = 0
    private var timeLeft = 60L
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_game1) // Usar el nuevo layout específico para este juego

        tvQuestion = findViewById(R.id.tvQuestion)
        etAnswer = findViewById(R.id.etAnswer)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvScore = findViewById(R.id.tvScore)
        tvTimer = findViewById(R.id.tvTimer)

        startNewGame()

        btnSubmit.setOnClickListener {
            checkAnswer()
        }
    }

    private fun startNewGame() {
        score = 0
        timeLeft = 60
        updateScore()
        generateNewQuestion()
        startTimer()
    }

    @SuppressLint("SetTextI18n")
    private fun generateNewQuestion() {
        num1 = Random.nextInt(1, 20)
        num2 = Random.nextInt(1, 20)
        tvQuestion.text = "¿Cuánto es $num1 + $num2?"
    }

    private fun checkAnswer() {
        val userAnswer = etAnswer.text.toString().toIntOrNull()
        if (userAnswer == num1 + num2) {
            score += 10
            updateScore()
        }
        etAnswer.text.clear()
        generateNewQuestion()
    }

    @SuppressLint("SetTextI18n")
    private fun updateScore() {
        tvScore.text = "Puntuación: $score"
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeft * 1000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished / 1000
                tvTimer.text = "Tiempo: ${timeLeft}s"
            }

            override fun onFinish() {
                "¡Tiempo agotado!".also { tvTimer.text = it }
                btnSubmit.isEnabled = false
            }
        }
        timer.start()
    }
}
