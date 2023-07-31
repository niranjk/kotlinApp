package com.niranjankhatri.kotlinapp.composetut.ui.theme

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Title
@Composable
fun TitleText(text: String, color: Color){
    Text(text = text, style = MaterialTheme.typography.h5, color = color)
}

@Composable
fun OnBackgroundTitleText(text: String){
    TitleText(text = text, color = MaterialTheme.colors.onBackground)
}

// Item
@Composable
fun ItemText(text: String, color: Color){
    Text(text = text, style = MaterialTheme.typography.body1, color = color)
}

@Composable
fun OnBackgroundItemText(text: String){
    ItemText(text = text, color = MaterialTheme.colors.onBackground)
}

// Button
@Composable
fun TextButton(text: String, textColor: Color, onClick: ()-> Unit){
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(contentColor = textColor)) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}

@Composable
fun PrimaryTextButton(text: String, onClick: () -> Unit){
    TextButton(text = text, textColor = Color.White, onClick = onClick)
}