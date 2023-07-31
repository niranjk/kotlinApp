package com.niranjankhatri.kotlinapp.composeapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.composeapp.ui.theme.OnBackgroundItemText


data class GridScreenState(
    val items: List<List<String>> = emptyList()
)

@Composable
fun GridScreenContent(
    gridScreenState: GridScreenState
){
    LazyColumn{
        items(gridScreenState.items){ rowItem ->
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                LazyRow{
                    items(rowItem){ item ->
                        Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                            OnBackgroundItemText(text = item)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GridScreen(rowCount: String, columnCount: String){
    val items = mutableListOf<List<String>>()
    (1..rowCount.toInt()).forEach { rowIndex ->
        items.add((1..columnCount.toInt()).toList().map { columnIndex ->
            stringResource(
                id = R.string.item_format_first_compose_app,
                formatArgs = arrayOf("$rowIndex", "$columnIndex")
            )
        })
    }
    GridScreenContent(gridScreenState = GridScreenState(items=items))
}