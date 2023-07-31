package com.niranjankhatri.kotlinapp.composetut.nativecompose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.niranjankhatri.kotlinapp.R


class MyComposeFragment : Fragment() {
    

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_compose, container).apply { 
            findViewById<ComposeView>(R.id.compose_view).apply { 
                setViewCompositionStrategy(
                    ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
                )
                setContent { 
                    MaterialTheme {
                        Text(text = "My Compose Fragment Text!")
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Click Me Right now !!")
                        }
                    }
                }
            }
        }
    }
    
}