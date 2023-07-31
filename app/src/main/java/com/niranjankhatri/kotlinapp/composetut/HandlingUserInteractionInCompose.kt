package com.niranjankhatri.kotlinapp.composetut

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.niranjankhatri.kotlinapp.R

// 1. Create Screen State
data class MyComposeScreenState(
    val itemCount : String = "",
    val items: List<String> = emptyList()
)

// Pattern called State Hoisting

// 1. Stateless Class : Contents
@Composable
fun MyComposeScreenContent(
    myComposeScreenState: MyComposeScreenState,
    onItemCountChange: (String) -> Unit,
    onButtonClick: () -> Unit
){
    LazyColumn{
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = stringResource(id = R.string.enter_number))
                TextField(value = myComposeScreenState.itemCount,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = onItemCountChange)
                Button(onClick = onButtonClick) {
                    Text(text = stringResource(id = R.string.click_me))
                }
            }
        }
        items(myComposeScreenState.items){ item ->
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(text = item)
            }
        }
    }
}

// 2. Statefull Class : State management
@Composable
fun MyComposeScreen(){
    var state by remember {
        mutableStateOf(MyComposeScreenState())
    }
    val context = LocalContext.current
    MyComposeScreenContent(
        myComposeScreenState = state,
        onItemCountChange = {
            state = state.copy(it)
        },
        onButtonClick = {
            state = state.copy(items = (1..state.itemCount.toInt()).toList().map {
                context.getString(R.string.item_format, "$it")
            })
        }
    )
}