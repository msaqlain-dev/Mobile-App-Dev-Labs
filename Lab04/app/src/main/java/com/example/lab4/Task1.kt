package com.example.lab4
import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

class Task1 {
    @Composable
    fun display(nav: NavController, context:Context = LocalContext.current){
        Scaffold {
            Column(
                modifier = Modifier.padding(it)
            ) {
                colorPicker()

            }
        }
    }
    @SuppressLint("CommitPrefEdits")
    @Composable
    fun colorPicker(context: Context = LocalContext.current) {
        val sharedPreferences = context.getSharedPreferences("color", Context.MODE_PRIVATE)
        // on below line we are creating a variable for controller
        val controller = rememberColorPickerController()
        var pickedColor = remember {
            mutableStateOf(Color.White)
        }

        // on below line we are creating a column,
        Column(
            // on below line we are adding a modifier to it,
            modifier = Modifier
                .fillMaxSize()
                // on below line we are adding a padding.
                .padding(all = 30.dp)
        ) {
            // on below line we are adding a row.
            Row(
                // on below line we are adding a modifier
                modifier = Modifier.fillMaxWidth(),
                // on below line we are adding horizontal
                // and vertical alignment.
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // on below line we are adding a alpha tile.
                AlphaTile(
                    // on below line we are
                    // adding modifier to it
                    modifier = Modifier
                        .fillMaxWidth()
                        // on below line
                        // we are adding a height.
                        .height(60.dp)
                        // on below line we are adding clip.
                        .clip(RoundedCornerShape(6.dp)),
                    // on below line we are adding controller.
                    controller = controller
                )
            }
            // on below line we are
            // adding horizontal color picker.
            HsvColorPicker(
                // on below line we are
                // adding a modifier to it
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .padding(10.dp),
                // on below line we are
                // adding a controller
                controller = controller,
                // on below line we are
                // adding on color changed.
                onColorChanged = {}
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val color = controller.selectedColor.value
                    val red = color.red
                    val blue = color.blue
                    val green = color.green

                    val editor = sharedPreferences.edit()
                    editor.putFloat("red", red)
                    editor.putFloat("blue", blue)
                    editor.putFloat("green", green)
                    editor.apply()
                    Toast.makeText(context, "Your Color Saved", Toast.LENGTH_SHORT).show()
                }) {
               Text(text = "Save Color")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val red = sharedPreferences.getFloat("red", 0.0F)
                    val blue = sharedPreferences.getFloat("blue", 0.0F)
                    val green = sharedPreferences.getFloat("green", 0.0F)
                    val color = Color(red, green, blue)
                    pickedColor.value = color
                    controller.setWheelColor(color)
                }) {
                Text(text = "Retreve Color")
            }
            BoxWithConstraints(
                modifier = Modifier.background(pickedColor.value).fillMaxWidth().height(50.dp).padding(top = 20.dp)
            ) {

            }
        }
    }
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Task1Preview(){
//    Task1().display()
}