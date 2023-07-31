package com.niranjankhatri.kotlinapp.composetut

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.composetut.ui.theme.KotlinAppTheme



@Composable
fun Greeting(name: String) {
    Text(text = "Hello Compose $name!")
}

@Composable
fun MyTextDisplay(myState: MyState){
    Text(text = myState.text, color = myState.color)
}

// TextField
data class MyTextFieldState(
    var text : String = ""
)

@Composable
fun MyComposableTextField(){
    var state by remember{
        mutableStateOf(MyTextFieldState())
    }
    TextField(
        value = state.text,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { value ->
            state = state.copy(value)
        }
    )
}
data class MyState(
    val text: String,
    val color: Color
)

@Composable
fun MyScreen(){
    Column {
        Text(text = "My Static Text")
        TextField(value = "My Text Field", onValueChange = {})
        Button(onClick = {}) {}
        Icon(painter = painterResource(id = R.drawable.ic_electric_car),
            contentDescription = stringResource(id = com.google.android.material.R.string.icon_content_description)
        )
    }
}

// toast
@Composable
fun MyToast(){
    val context = LocalContext.current
    Button(
        onClick = {
            // call toast
            Toast.makeText(
                context,
                "Toast WQoooow!",
                Toast.LENGTH_LONG
            ).show()
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Click me to Toast!")
    }
}

@Composable
fun MyList(mItems: List<String>){
    Column {
        mItems.forEach { item -> Text(text = item) }
    }
}

@Composable
fun MyUnknownList(mItems: List<String>){
    LazyColumn(
        modifier = Modifier.padding(
            top = 5.dp, bottom = 5.dp,
            start = 10.dp, end = 10.dp
        )
    ){
        item { Text(text = "Header") }
        items(mItems){ item -> Text(text = item) }
        item { Text(text = "Footer") }
    }
    LazyRow{
        // for horizontal scolling
    }
}

@Composable
fun FirstComposeScreen(mItems: List<String>){
    LazyColumn{
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = stringResource(id = R.string.enter_number))
                TextField(value = "",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {})
                Button(onClick = {}) {
                    Text(text = stringResource(id = R.string.click_me))
                }
            }
        }
        items(mItems){ item ->
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = item)
            }
        }
    }
}