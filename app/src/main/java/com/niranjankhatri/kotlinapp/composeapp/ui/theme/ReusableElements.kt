package com.niranjankhatri.kotlinapp.composeapp.ui.theme

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


// reusable compose elements

// titles
@Composable
fun TitleText(text: String, color: Color) {
    Text(text = text, style = MaterialTheme.typography.h5, color = color)
}

@Composable
fun OnBackgroundTitleText(text: String) {
    TitleText(text = text, color = MaterialTheme.colors.onBackground)
}

// text fields
@Composable
fun BodyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    textColor: Color
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(textColor = textColor)
    )
}

@Composable
fun OnBackgroundBodyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions
){
    BodyTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        textColor = MaterialTheme.colors.onBackground
    )
}

// Grid Item Text
@Composable
fun ItemText(text: String, color: Color){
    Text(text = text, style = MaterialTheme.typography.body1, color= color)
}

@Composable
fun OnBackgroundItemText(text: String){
    ItemText(text = text, color = MaterialTheme.colors.onBackground)
}

// Buttons
@Composable
fun TextButton(
    text: String,
    textColor: Color,
    onClick: () -> Unit
){
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(contentColor = textColor)) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}

@Composable
fun PrimaryTextButton(text: String, onClick: () -> Unit){
    TextButton(text = text, textColor = Color.White, onClick= onClick)
}

