package com.example.lab4.notes_app

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class ShowNote {
    @Composable
    fun Display(nav:NavController, data:NoteModel?, context:Context = LocalContext.current){
        if (data == null){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "WOOPs!\nNo Note Found", fontWeight = FontWeight.Bold, fontSize = 32.sp, color=Color.Red, textAlign = TextAlign.Center)
            }
        }else{
            Scaffold {
                Column(
                    Modifier
                        .padding(it)
                        .padding(20.dp)
                ) {
                    Text(text = data.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Text(text = data.datetime)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = data.content, fontSize = 20.sp)
                }
            }
        }
    }
}



@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ShowNotePreview(){
    ShowNote().Display(nav= NavController(LocalContext.current), null)
}