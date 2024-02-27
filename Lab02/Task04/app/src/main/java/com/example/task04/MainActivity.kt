package com.example.task04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.task04.ui.theme.Task04Theme
import com.google.firebase.inappmessaging.model.ImageData
import androidx.compose.material3.Text as Text1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Task04Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Grid("Android")
                }
            }
        }
    }
}

@Composable
fun ImageListItem(drawableId: Int, name: String) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(200.dp)
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = null,
            modifier = Modifier
                .height(400.dp)
                .width(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = name,
                color = Color.Black,
                fontSize = 24.sp,
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Grid(name: String, modifier: Modifier = Modifier) {
    val imageList = listOf(
        ImageData(R.drawable.ironman, "Deadpool"),
        ImageData(R.drawable.ironman, "Hulk"),
        ImageData(R.drawable.ironman, "Wolverine"),
        ImageData(R.drawable.ironman, "Black Widow")
    )


    Scaffold (
        topBar = { TopAppBar(title = {
            Text(text = "Lazy Grid Layout")
        })}
    ){
        LazyVerticalGrid(modifier = Modifier.padding(it) ,columns = GridCells.Fixed(2)){
            items(imageList) { imageData ->
                ImageListItem(imageData.drawableId, imageData.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Task04Theme {
        Grid("Android")
    }
}