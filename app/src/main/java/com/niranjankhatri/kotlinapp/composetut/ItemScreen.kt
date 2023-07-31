package com.niranjankhatri.kotlinapp.composetut

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.composetut.ui.theme.OnBackgroundItemText

data class ItemScreenState(
    val items : List<String> = emptyList()
)

@Composable
fun ItemScreenContent(
    itemScreenState: ItemScreenState
){
    LazyColumn{
        items(itemScreenState.items){ item ->
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                OnBackgroundItemText(text = item)
            }
        }
    }
}

@Composable
fun ItemScreen(itemCount : String){
    ItemScreenContent(itemScreenState = ItemScreenState(
        (1..itemCount.toInt()).toList().map { stringResource(id = R.string.item_format, formatArgs = arrayOf("$it")) }
    ))

}