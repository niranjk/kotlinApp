package com.niranjankhatri.kotlinapp.composetut

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.composetut.ui.theme.KotlinAppTheme
import com.niranjankhatri.kotlinapp.composetut.ui.theme.OnBackgroundTitleText


class MyComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinAppTheme {
                val navHostController = rememberNavController()
                MyNavigationApp(navHostController = navHostController)
            }
        }
    }
}

@Composable
fun Screen1(onButtonClick: ()-> Unit){
    Button(
        modifier = Modifier.padding(20.dp),
        onClick = onButtonClick) {
        Text(text = "Screen One")
    }
}

@Composable
fun Screen2(input1: String, input2: String){
    Text(modifier = Modifier.padding(24.dp), text = "Screen Two Value : $input1 and $input2")
}

@Composable
fun MyNavigationApp(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "screenOne") {
        composable("screenOne") {
            Screen1 {
                navHostController.navigate("screenTwo/Value1?input2=Value2")
            }
        }
        composable(
            route = "screenTwo/{input1}?input2={input2}",
            arguments = listOf(
                navArgument("input1"){type = NavType.StringType},
                navArgument("input2"){type = NavType.StringType},
            )
        ) {
            Screen2(
                input1 = it.arguments?.getString("input1").orEmpty(),
                input2 = it.arguments?.getString("input2").orEmpty()
            )
        }
    }
}

@Composable
fun MyApp(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "itemCountScreen") {
        composable("itemCountScreen") {
            ItemCountScreen { value ->
                navHostController.navigate("itemScreen/?itemCount=$value")
            }
        }
        composable(
            route = "itemScreen/?itemCount={itemCount}",
            arguments = listOf(navArgument("itemCount") {
                type = NavType.StringType
            })
        ) {
            ItemScreen(itemCount = it.arguments?.getString("itemCount").orEmpty())
        }
    }
}


/*
KotlinAppTheme {
                /*
                val items = (1..30).map {
                    stringResource(id = R.string.item_format, formatArgs = arrayOf("$it"))
                }
                FirstComposeScreen(mItems = items)

                 */
                // MyComposeScreen()
                Surface(color = MaterialTheme.colors.background) {
                    val navHostController = rememberNavController()
                    Column(modifier = Modifier.padding(16.dp)) {
                        MyApp(navHostController = navHostController)
                    }
                }
            }
 */

@Composable
fun HorizontalScreen(items: List<String>) {
    LazyRow(modifier = Modifier.padding(16.dp)) {
        items(items) {
            Text(text = it)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KotlinAppTheme {
        Text(text = "Hello Compose")
    }
}