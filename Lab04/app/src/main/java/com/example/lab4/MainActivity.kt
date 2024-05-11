package com.example.lab4

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab4.notes_app.AddNote
import com.example.lab4.notes_app.DataPassing as notepassing
import com.example.lab4.notes_app.ShowNote
import com.example.lab4.notes_app.Task3_notes
import com.example.lab4.task4_images.DisplayImage
import com.example.lab4.task4_images.Task4_images
import com.example.lab4.task4_images.DataPassing as imagepassing

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(context: Context = LocalContext.current, nav: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Welcome to Lab 4") },
                actions = {
                    Button(onClick = {


                    }) {
                        Text(text = "Test Button")
                    }
                }
                )
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(), onClick = {
                    nav.navigate("/store_fav_color")
                }) {
                Text(text = "TASK 1. Store Favourite Color Application")
            }
            Button(
                modifier = Modifier.fillMaxWidth(), onClick = {
                    nav.navigate("/counter")
                }) {
                Text(text = "TASK 2. Counter Application")
            }
            Button(
                modifier = Modifier.fillMaxWidth(), onClick = {
                    nav.navigate("/notes")
                }) {
                Text(text = "TASK 3. Keep Notes Application")
            }
            Button(
                modifier = Modifier.fillMaxWidth(), onClick = {
                    nav.navigate("/show-images")
                }) {
                Text(text = "TASK 4. Show Images App")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Main(context: Context = LocalContext.current) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "/") {
        composable("/") {
            MainScreen(nav = navController)
        }
        composable("/store_fav_color") {
            Task1().display(navController)
        }
        composable("/counter") {
            Task2_Counter().Display(navController)
        }
        composable("/notes") {
            Task3_notes().Display(navController)
        }
        composable("/add-note") {
            AddNote().Display(navController)
        }
        composable("/show-note") {
            ShowNote().Display(navController, notepassing.note)
        }
        composable("/show-images") {
            Task4_images().Display(nav = navController)
        }

        composable("/display-image") {
            DisplayImage().Display(nav = navController, imagepassing.file)
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Main()
}