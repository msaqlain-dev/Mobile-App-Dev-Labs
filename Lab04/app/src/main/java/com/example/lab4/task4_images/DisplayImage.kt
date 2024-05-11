package com.example.lab4.task4_images

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.io.File

class DisplayImage {

    @Composable
    fun Display(nav:NavController, file: File?, context: Context = LocalContext.current){
        if (file != null){
            Scaffold(
                Modifier.background(Color.Black)
            ) {
                Column(
                    Modifier
                        .padding(it)
                        .fillMaxSize()
                        .background(Color.Black)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        bitmap = BitmapFactory.decodeFile(file.path).asImageBitmap(),
                        contentDescription = "image",
                    )
                }
            }
        }else{
            showErrorScreen(msg = "Unexpected error occurred!")
        }
    }

    @Composable
    fun showErrorScreen(msg:String){
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = msg, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        }
    }
}