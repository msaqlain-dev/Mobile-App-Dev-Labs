package com.example.lab4.notes_app

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

class Task3_notes {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Display(nav: NavController, context: Context = LocalContext.current){
        var notes = Functions().getAllFiles(context).toMutableList()
        var doShowDeleteDialog by remember{
                mutableStateOf(false)
        }

        var showOptions by remember{
            mutableStateOf(false)
        }
        var selectedIndex = 0

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Keep Notes") },
                    actions = {
                        Button(
                            onClick = { showOptions = !showOptions },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if(showOptions){
                                    Color.Blue
                                }else{
                                    Color.Transparent
                                },
                                contentColor = if(showOptions){
                                    Color.White
                                }else{
                                    Color.Blue
                                },
                            )
                        ) {
                            Text(text = "Show Options")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    nav.navigate("/add-note")
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                    LazyColumn(content = {
                        items(notes.size){
                            index ->
                            ListItem(
                                headlineContent = { Text(text = notes[index].title) },
                                trailingContent = {
                                                 if(showOptions){
                                                     Icon(
                                                         imageVector = Icons.Default.Delete,
                                                         contentDescription = "Delete",
                                                         tint = Color.Red,
                                                         modifier=Modifier.clickable {
                                                             selectedIndex = index
                                                             doShowDeleteDialog = true
                                                         }
                                                     )
                                                 }
                                },
                                modifier = Modifier
                                    .background(Color.Blue)
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onTap = { offset ->
                                                DataPassing.note = notes[index]
                                                nav.navigate("/show-note")
                                            },
                                        )
                                    }
                            )
                        }
                    })
            }
            if(doShowDeleteDialog){
                showDialog(

                    onConfirm = {
                            if(Functions().deleteNote(context, notes[selectedIndex].id)){
                                Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show()
                            }else{
                                Toast.makeText(context, "Could Not Delete", Toast.LENGTH_LONG).show()
                            }
                            notes.removeAt(selectedIndex)
                        doShowDeleteDialog = false
                                },
                    onDismiss = { doShowDeleteDialog = false },
                    title = "Delete",
                    message = "Do you want to delete: ${notes[selectedIndex].title}"
                )
            }
        }
    }

    @Composable
    fun showDialog(
        onConfirm : ()->Unit,
        onDismiss: ()->Unit,
        title:String,
        message:String,
    ){
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },

            confirmButton = {
                Button(onClick = { onConfirm() }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                Text(text = "Delete")
            } },
            dismissButton = {
                            Button(onClick = { onDismiss() }) {
                                Text(text = "Cancel")
                            }
            },

            title = { Text(text = title, fontWeight = FontWeight.Bold) },
            text = { Text(text = message) },

        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Task3_notesPreview(){
    Task3_notes().Display(nav = NavController(LocalContext.current))
}