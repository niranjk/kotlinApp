package com.niranjankhatri.kotlinapp.composetut

import android.widget.TextView
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


@Composable
fun MyCustomisedAndroidElement(text: String){
    AndroidView(factory = { context ->
        TextView(context).apply {
            this.text = text
        }
    })
}

@Composable
fun MyViewModelScreen(viewModel: ViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    Text(text = viewModel.viewModelScope.toString())
}

