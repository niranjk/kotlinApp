package com.niranjankhatri.kotlinapp.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.niranjankhatri.kotlinapp.composeapp.ui.theme.KotlinAppTheme

class ComposeMatrixActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navHostController = rememberNavController()
                    MyMatrixApp(navHostController = navHostController)
                }
            }
        }
    }
}


@Composable
fun MyMatrixApp(navHostController: NavHostController){
    var rowCount by rememberSaveable {
        mutableStateOf("")
    }
    NavHost(navController = navHostController, startDestination = "insertRowsScreen"){
        // first screen - insert row screen
        composable("insertRowsScreen"){
            InsertRowScreen{
                rowCount = it
                navHostController.navigate("insertColumnsScreen")
            }
        }
        // second screen - insert column screen
        composable("insertColumnsScreen"){
            InsertColumnScreen{
                navHostController.navigate("gridScreen/?rowCount=$rowCount&columnCount=$it")
            }
        }
        // third screen - your grid screen
        composable(
            route = "gridScreen/?rowCount={rowCount}&columnCount={columnCount}",
            arguments = listOf(
                navArgument("rowCount"){type = NavType.StringType},
                navArgument("columnCount"){type = NavType.StringType}
            )
        ){
            GridScreen(
                rowCount = it.arguments?.getString("rowCount").orEmpty(),
                columnCount = it.arguments?.getString("columnCount").orEmpty())
        }
    }
}
@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    KotlinAppTheme {
        Greeting2("Android")
    }
}