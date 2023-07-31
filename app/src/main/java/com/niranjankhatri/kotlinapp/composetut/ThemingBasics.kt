package com.niranjankhatri.kotlinapp.composetut

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun ParagraphText(text: String, color: Color){
    Text(text = text, style = MaterialTheme.typography.body2, color = color)
}

@Composable
fun OnBackgroundParagraphText(text: String){
    ParagraphText(text = text, color = MaterialTheme.colors.onBackground)
}

@Composable
fun MyUseScreen(){
    OnBackgroundParagraphText(text = "My text")
}

