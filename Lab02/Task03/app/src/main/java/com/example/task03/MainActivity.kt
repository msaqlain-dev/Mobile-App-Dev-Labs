package com.example.task03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task03.ui.theme.Task03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task03Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Tiles("Android")
                }
            }
        }
    }
}

@Composable
fun Tiles(name: String, modifier: Modifier = Modifier) {
    Column {
        Card (elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.padding(10.dp)){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.Start
            ){
                Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .clip(RoundedCornerShape((10.dp)))
                        .size(80.dp)
                )
                Column (modifier = Modifier, verticalArrangement = Arrangement.Center){
                    Text(text = "Iron Man", fontSize = 24.sp)
                    Text(text = "Age 23")
                }
            }
        }
        Card (elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.padding(10.dp)){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.Start
            ){
                Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .clip(RoundedCornerShape((10.dp)))
                        .size(80.dp)
                )
                Column (modifier = Modifier, verticalArrangement = Arrangement.Center){
                    Text(text = "Hulk", fontSize = 24.sp)
                    Text(text = "Age 23")
                }
            }
        }
        Card (elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.padding(10.dp)){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.Start
            ){
                Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .clip(RoundedCornerShape((10.dp)))
                        .size(80.dp)
                )
                Column (modifier = Modifier, verticalArrangement = Arrangement.Center){
                    Text(text = "Deadpool", fontSize = 24.sp)
                    Text(text = "Age 23")
                }
            }
        }
        Card (elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.padding(10.dp)){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.Start
            ){
                Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .clip(RoundedCornerShape((10.dp)))
                        .size(80.dp)
                )
                Column (modifier = Modifier, verticalArrangement = Arrangement.Center){
                    Text(text = "Wolverine", fontSize = 24.sp)
                    Text(text = "Age 23")
                }
            }
        }
        Card (elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.padding(10.dp)){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.Start
            ){
                Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .clip(RoundedCornerShape((10.dp)))
                        .size(80.dp)
                )
                Column (modifier = Modifier, verticalArrangement = Arrangement.Center){
                    Text(text = "Black Widow", fontSize = 24.sp)
                    Text(text = "Age 23")
                }
            }
        }
        Card (elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.padding(10.dp)){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.Start
            ){
                Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .clip(RoundedCornerShape((10.dp)))
                        .size(80.dp)
                )
                Column (modifier = Modifier, verticalArrangement = Arrangement.Center){
                    Text(text = "Thor", fontSize = 24.sp)
                    Text(text = "Age 23")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Task03Theme {
        Tiles("Android")
    }
}