package com.example.lab4.task4_images

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import java.io.File

class Task4_images {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Display(nav:NavController, context:Context=LocalContext.current){
        var readPermission = requestPermission(context = context, "android.permission.READ_EXTERNAL_STORAGE")
        var writePermission = requestPermission(context = context, "android.permission.WRITE_EXTERNAL_STORAGE")

        Scaffold(
            topBar = { CenterAlignedTopAppBar(title = { Text(text = "Gallery") })}
        ) {
            Column(
                Modifier.padding(it)
            ) {
                if (readPermission && writePermission){
                    var files = getFiles(context)
                    if (files != null) {
                        if (files.isNotEmpty()){
                            LazyVerticalGrid(columns = GridCells.Fixed(4), content = {
                                items(files.size){
                                    Image(
                                        bitmap = BitmapFactory.decodeFile(files[it].path).asImageBitmap(),
                                        contentDescription = "Image",
                                        modifier = Modifier
                                            .size(100.dp)
                                            .padding(5.dp)
                                            .clickable {
                                                DataPassing.file = files[it]
                                                nav.navigate("/display-image")
                                            },
                                        contentScale = ContentScale.FillBounds,

                                        )
                                }
                            })
                        }else{
                            showErrorScreen(msg = "No File Found\nPut some images in ${getFolder(context).path}")
                        }
                    }else{
                        showErrorScreen(msg = "Any Unexpected error occurred")
                    }
                }else{
                    Column {
                        Text(text = "Looking for Permission\nIf Permission Given\nTry Restarting the App")
                    }
                }
            }
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

    @Composable
    fun requestPermission(context:Context, permission:String):Boolean{
        if(ContextCompat.checkSelfPermission(context, permission)
            == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }
        var isPermissionGranted by remember {
            mutableStateOf(false)
        };
        val request = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()) {
                isGranted ->
            isPermissionGranted = isGranted
        }
        LaunchedEffect(Unit) {
            request.launch(permission)
        }
        return isPermissionGranted
    }

    private fun getFolder(context:Context):File{
        var folder = File(context.getExternalFilesDir(null), "arimages")
        println("Path: ${folder.path}")
        if(!folder.exists()){
            if(folder.mkdirs()){
                println("Directory Created")
            }else{
                println("Could Not Create Directory")
            }

        }
        return folder
    }
    fun getFiles(context: Context): List<File>? {
        var folder = getFolder(context)
        var files = folder.listFiles()?.toList()
        return files
    }
}