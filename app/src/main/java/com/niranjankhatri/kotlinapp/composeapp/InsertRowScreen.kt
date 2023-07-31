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


data class InsertRowScreenState(
    val rowCount: String = ""
)

@Composable
fun InsertRowScreenContent(
    insertRowScreenState: InsertRowScreenState,
    onItemCountChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    Column {
        OnBackgroundTitleText(text = stringResource(id = R.string.insert_number_rows))
        OnBackgroundBodyTextField(
            value = insertRowScreenState.rowCount,
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
fun InsertRowScreen(onButtonClick: (String) -> Unit) {
    var state by remember {
        mutableStateOf(InsertRowScreenState())
    }
    InsertRowScreenContent(insertRowScreenState = state, onItemCountChange = {
        state = state.copy(rowCount = it)
    }, onButtonClick = {
        onButtonClick(state.rowCount)
    })
}