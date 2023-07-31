package com.niranjankhatri.kotlinapp.composetut

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.composetut.ui.theme.OnBackgroundTitleText
import com.niranjankhatri.kotlinapp.composetut.ui.theme.PrimaryTextButton


data class ItemCountScreenState(
    val itemCount: String = ""
)

@Composable
fun ItemCountScreenContent(
    itemCountScreenState: ItemCountScreenState,
    onItemCountChange: (String) -> Unit,
    onButtonClick: () -> Unit
){
    Column {
        OnBackgroundTitleText(text = stringResource(id = R.string.enter_number))
        TextField(value = itemCountScreenState.itemCount, onValueChange = onItemCountChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        PrimaryTextButton(text = stringResource(id = R.string.click_me), onClick = onButtonClick)
        MyCustomisedAndroidElement(text = "Android Element Rolling")
    }
}

@Composable
fun ItemCountScreen(
    onButtonClick: (String) -> Unit
){
    var state by remember {
        mutableStateOf(ItemCountScreenState())
    }
    ItemCountScreenContent(itemCountScreenState = state, onItemCountChange = {
        state = state.copy(itemCount = it)
    }, {
        onButtonClick(state.itemCount)
    })
}