package com.example.geoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.geoquiz.ui.theme.GeoQuizTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

        private lateinit var tvQuestion: TextView
        private lateinit var btnTrue: Button
        private lateinit var btnFalse: Button
        private lateinit var tvCorrectCount: TextView
        private lateinit var tvIncorrectCount: TextView

        // counters
        private var correctCount = 0
        private var incorrectCount = 0
        var currentIndex: Int = Random.nextInt(0, 5)


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            tvQuestion = findViewById(R.id.tv_question)
            btnTrue = findViewById(R.id.btn_true)
            btnFalse = findViewById(R.id.btn_false)
            tvCorrectCount = findViewById(R.id.tv_correct_count)
            tvIncorrectCount = findViewById(R.id.tv_incorrect_count)


            currentIndex = Random.nextInt(0, 5)

            val questions = arrayOf(
                Question(R.string.question_australia, true),
                Question(R.string.question_oceans, true),
                Question(R.string.question_mideast, false),
                Question(R.string.question_africa, false),
                Question(R.string.question_americas, true),
                Question(R.string.question_asia, true)
            )

            tvQuestion.text = getString(questions[currentIndex].questionTextId)
            tvCorrectCount.text = getString(R.string.correct_count_label, correctCount)
            tvIncorrectCount.text = getString(R.string.incorrect_count_label, incorrectCount)

            btnTrue.setOnClickListener {
                checkAnswer(questions[currentIndex])
            }

            btnFalse.setOnClickListener {
                checkAnswer(questions[currentIndex])
            }
        }

        private fun checkAnswer(question: Question) {
            if (question.answer.toString() == "true") {
                correctCount++
                tvCorrectCount.text = getString(R.string.correct_count_label, correctCount)
                currentIndex = Random.nextInt(0, 5)
                tvQuestion.text = getString(question.questionTextId)
            } else {
                incorrectCount++
                tvIncorrectCount.text = getString(R.string.incorrect_count_label, incorrectCount)
                currentIndex = Random.nextInt(0, 5)
                tvQuestion.text = getString(question.questionTextId)
            }
        }
    }
