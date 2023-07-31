package com.niranjankhatri.kotlinapp.sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.niranjankhatri.kotlinapp.R

class MySportsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_sports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.football)?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.nav_football)
        }
        view.findViewById<Button>(R.id.basketball)?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.nav_basketball)
        }
        view.findViewById<Button>(R.id.hockey)?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.nav_hockey)
        }
    }
}