package com.niranjankhatri.kotlinapp.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.niranjankhatri.kotlinapp.R

class QuizActivity : AppCompatActivity(), QuestionAnswerListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        if (savedInstanceState == null){
            findViewById<FragmentContainerView>(R.id.fragment_container)?.let { frameLayout ->
                val questionFragment = QuestionsFragment()
                supportFragmentManager.beginTransaction().add(
                    frameLayout.id, questionFragment
                ).commit()
            }
        }
    }

    override fun onSelected(questionId: Int) {
        findViewById<FragmentContainerView>(R.id.fragment_container)?.let { frameLayout ->
            val answersFragment = AnswersFragment.newInstance(questionId)
            supportFragmentManager.beginTransaction()
                .replace(frameLayout.id, answersFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}