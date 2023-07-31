package com.niranjankhatri.kotlinapp.composeapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.composeapp.ui.theme.OnBackgroundBodyTextField
import com.niranjankhatri.kotlinapp.composeapp.ui.theme.OnBackgroundTitleText
import com.niranjankhatri.kotlinapp.composeapp.ui.theme.PrimaryTextButton


data class InsertColumnScreenState(
    val columnCount: String = ""
)

@Composable
fun InsertColumnScreenContent(
    insertColumnScreenState: InsertColumnScreenState,
    onItemCountChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    Column {
        OnBackgroundTitleText(text = stringResource(id = R.string.insert_number_columns))
        OnBackgroundBodyTextField(
            value = insertColumnScreenState.columnCount,
            onValueChange = onItemCountChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        PrimaryTextButton(
            text = stringResource(id = R.string.done),
            onClick = onButtonClick
        )
    }
}

@Composable
fun InsertColumnScreen(onButtonClick: (String) -> Unit) {
    var state by remember {
        mutableStateOf(InsertColumnScreenState())
    }
    InsertColumnScreenContent(insertColumnScreenState = state, onItemCountChange = {
        state = state.copy(columnCount = it)
    }, onButtonClick = {
        onButtonClick(state.columnCount)
    })
}