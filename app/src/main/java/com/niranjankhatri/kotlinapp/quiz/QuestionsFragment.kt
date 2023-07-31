package com.niranjankhatri.kotlinapp.quiz

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.niranjankhatri.kotlinapp.R

interface QuestionAnswerListener{
    fun onSelected(questionId: Int)
}

class QuestionsFragment : Fragment(), View.OnClickListener{

    private lateinit var questionAnswerListener: QuestionAnswerListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is QuestionAnswerListener){
            questionAnswerListener = context
        } else {
            throw java.lang.RuntimeException("Must implement QuestionAnswerListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questions = listOf<View>(
            view.findViewById(R.id.most_moons),
            view.findViewById(R.id.largest_planet),
            view.findViewById(R.id.side_spinning)
        )
        questions.forEach {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        v?.let { question ->
            questionAnswerListener.onSelected(questionId = question.id)
        }
    }

}